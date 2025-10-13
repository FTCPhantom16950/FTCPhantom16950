package org.firstinspires.ftc.teamcode.own.camera;

import static org.opencv.core.CvType.CV_64F;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.graphics.Canvas;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImpl;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

@Config
public class ArtifactProcessor implements VisionProcessor {
    private int minSquare = 0;
    private ArtifactProcessor(){}
    public static Builder newBuilder() {
        return new ArtifactProcessor().newBuilder();
    }
    public class Builder {
        private Builder() {
        }

        public Builder addTelemetry(Telemetry telemetry) {
            ArtifactProcessor.this.telemetry = telemetry;
            return this;
        }

        public ArtifactProcessor createWithDefaults() {
            ArtifactProcessor.this.cameraPos[0] = 0;
            ArtifactProcessor.this.cameraPos[1] = 0;
            ArtifactProcessor.this.cameraPos[2] = 0;
            ArtifactProcessor.this.cameraRot[0] = 0;
            ArtifactProcessor.this.cameraRot[1] = 0;
            ArtifactProcessor.this.cameraRot[2] = 0;
            ArtifactProcessor.this.cameraMatrixX = 3.58F;
            ArtifactProcessor.this.cameraMatrixY = 2.02F;
            ArtifactProcessor.this.razmer = 49f;
            ArtifactProcessor.this.f = 4f;
            ArtifactProcessor.this.pixelCameraHeight = 960;
            ArtifactProcessor.this.dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(30, 30));
            ArtifactProcessor.this.erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(20, 20));
            ArtifactProcessor.this.minValues = new Scalar(7, 70, 60);
            ArtifactProcessor.this.maxValues = new Scalar(40, 255, 255);
            ArtifactProcessor.this.blurSize = new Size(1, 1);
            return ArtifactProcessor.this;
        }

        public ArtifactProcessor build() {
            return ArtifactProcessor.this;
        }
    }

    private float maxDist, minDist;
    private float[] cameraPos = new float[]{0, 0, 0}, cameraRot = new float[]{0, 0, 0};
    private float minOtn, maxOtn, h, centerOfSquare, x, y, z, rectSizeOnCamera, cameraMatrixX, cameraMatrixY, razmer, f, pixelCameraHeight, c, convers;
    private double a, b, square, otn;
    private boolean usingOtn, usingSquare, usingDist;
    float squareOnScreen, minSquareOnScreen, maxSquareOnScreen;
    Mat K = new Mat(3, 3, CV_64F);
    List<MatOfPoint> contours = new ArrayList<>();
    Mat openingImage = new Mat();
    Mat closingOutput = new Mat();
    Mat hierarchy = new Mat();
    Mat blurredImage = new Mat();
    Mat hsvImage = new Mat();
    Mat mask = new Mat();
    Mat morphOutput = new Mat();
    Mat dilateElement;
    Mat erodeElement;
    Scalar minValues;
    Scalar maxValues;
    MatOfPoint2f[] contoursPoly;
    Telemetry telemetry;
    Rect[] rects;
    Size blurSize;

    @Override
    public void init(int width, int height, CameraCalibration calibration) {
        telemetry.addData("hui v rot", f);
        telemetry.update();
        K.put(0, 0, calibration.focalLengthX);
        K.put(0, 1, 0);
        K.put(0, 2, calibration.principalPointX);
        K.put(1, 0, 0);
        K.put(1, 1, calibration.focalLengthY);
        K.put(1, 2, calibration.principalPointY);
        K.put(2, 0, 0);
        K.put(2, 1, 0);
        K.put(2, 2, 1);
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        Imgproc.blur(frame, blurredImage, blurSize);
        Imgproc.cvtColor(blurredImage, hsvImage, Imgproc.COLOR_BGR2HSV);
        Core.inRange(hsvImage, minValues, maxValues, mask);

        Imgproc.erode(mask, openingImage, erodeElement);
        Imgproc.dilate(openingImage, morphOutput, dilateElement);

        Imgproc.dilate(mask, closingOutput, dilateElement);
        Imgproc.erode(closingOutput, morphOutput, erodeElement);

        Imgproc.findContours(morphOutput, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
        contoursPoly = new MatOfPoint2f[contours.size()];
        rects = new Rect[contours.size()];
        for (int idx = 0; idx < contours.size(); idx++) {
            if (contours.get(idx).toArray() != null) {
                contoursPoly[idx] = new MatOfPoint2f();
                Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(idx).toArray()), contoursPoly[idx], 3, true);
                rects[idx] = Imgproc.boundingRect(new MatOfPoint(contoursPoly[idx].toArray()));
                a = (-rects[idx].tl().x + rects[idx].br().x);
                b = (-rects[idx].tl().y + rects[idx].br().y);
                otn = a / b;
                h = (float) (a * convers);
                rectSizeOnCamera = ((f * razmer) / h);
                squareOnScreen = (float) (a * a);
                square = (h * h) * Math.pow(rectSizeOnCamera, 2);
                if (square >= minSquare && (otn >= minOtn && otn <= maxOtn) && (rectSizeOnCamera <= maxDist && rectSizeOnCamera >= minDist)) {
                    centerOfSquare = (float) (a / 2);
                    telemetry.addData("h", h);
                    telemetry.addData("rectSizeOnCamera", rectSizeOnCamera);
                    telemetry.addData("otn", otn);
                    telemetry.addData("square", square);
                    telemetry.addData("focal", f);
                    telemetry.addData("size on camera", convers);
                    telemetry.addData("a", a);
                    telemetry.addData("b", b);
                    Imgproc.rectangle(frame, rects[idx].tl(), rects[idx].br(), new Scalar(255, 0, 0), 2);
                    Point centerPoint = new Point(rects[idx].tl().x + centerOfSquare, rects[idx].tl().y + centerOfSquare);
                    float[] angles = calculateAngle((float) centerPoint.x, (float) centerPoint.y);
                    float D = rectSizeOnCamera;
                    float phi = angles[1];
                    float theta = angles[0];
                    float xc = (float) (D * Math.cos(theta) * Math.cos(phi));
                    float yc = (float) (D * Math.cos(theta) * Math.sin(phi));
                    float zc = (float) (D * Math.sin(theta));
                    float[] Pc_coords = {xc, yc, zc};
                    Pc_coords = convertCameraToRobot(cameraPos, cameraRot, Pc_coords);
                    x = Pc_coords[0];
                    y = Pc_coords[1];
                    z = Pc_coords[2];
                    @SuppressLint("DefaultLocale")
                    String text = String.format("dist: %.1f, x: %.1f, y: %.1f, z: %.1f", D, x, y, z);
                    Imgproc.putText(frame, text, rects[idx].tl(), 1, 1, new Scalar(255, 255, 0));
                    Imgproc.drawMarker(frame, new Point(rects[idx].tl().x + centerOfSquare, rects[idx].tl().y + centerOfSquare), new Scalar(255, 0, 0));
                }
            }
            contoursPoly[idx].release();
        }
        Imgproc.drawMarker(frame, new Point(K.get(0, 2)[0], K.get(1, 2)[0]), new Scalar(255, 0, 255));
//        morphOutput = frame;
        telemetry.update();
        blurredImage.release();
        hsvImage.release();
        mask.release();
        erodeElement.release();
        dilateElement.release();
        contours.clear();
        hierarchy.release();
        return null;
    }

    private float[] convertCameraToRobot(float[] cameraCord, float[] angeles, float[] objectPos) {
        Mat T = new Mat(3, 1, CV_64F);
        T.put(0, 0, cameraCord[0]);
        T.put(1, 0, cameraCord[1]);
        T.put(2, 0, cameraCord[2]);
        Mat Pc = new Mat(3, 1, CV_64F);
        Pc.put(0, 0, objectPos[0]);
        Pc.put(1, 0, objectPos[1]);
        Pc.put(2, 0, objectPos[2]);
        angeles[0] = (float) Math.toRadians(angeles[0]);
        angeles[1] = (float) Math.toRadians(angeles[1]);
        angeles[2] = (float) Math.toRadians(angeles[2]);
        Mat Rx = Mat.eye(3, 3, CV_64F);
        Rx.put(1, 1, Math.cos(angeles[0]));
        Rx.put(1, 2, -Math.sin(angeles[0]));
        Rx.put(2, 2, Math.cos(angeles[0]));
        Mat Ry = Mat.eye(3, 3, CV_64F);
        Ry.put(1, 1, Math.cos(angeles[1]));
        Ry.put(1, 2, Math.sin(angeles[1]));
        Ry.put(2, 2, Math.cos(angeles[1]));
        Mat Rz = Mat.eye(3, 3, CV_64F);
        Rz.put(1, 1, Math.cos(angeles[2]));
        Rz.put(1, 2, -Math.sin(angeles[2]));
        Rz.put(2, 2, Math.cos(angeles[2]));

        Mat R_temp = new Mat();
        Core.gemm(Rz, Ry, 1.0, new Mat(), 0.0, R_temp);
        Mat R_c_to_r = new Mat();
        Core.gemm(R_temp, Rx, 1.0, new Mat(), 0.0, R_c_to_r);

        Mat RPc = new Mat();
        Core.gemm(R_c_to_r, Pc, 1.0, new Mat(), 0.0, RPc);

        Mat Pr = new Mat();
        Core.add(RPc, T, Pr);

        float x_r = (float) Pr.get(0, 0)[0];
        float y_r = (float) Pr.get(1, 0)[0];
        float z_r = (float) Pr.get(2, 0)[0];
        T.release();
        Pc.release();
        Rx.release();
        Ry.release();
        Rz.release();
        R_temp.release();
        R_c_to_r.release();
        RPc.release();
        Pr.release();
        return new float[]{x_r, y_r, z_r};
    }

    private float[] calculateAngle(float x, float y) {
        double fx = K.get(0, 0)[0];
        double fy = K.get(1, 1)[0];
        double cx = K.get(0, 2)[0];
        double cy = K.get(1, 2)[0];
        double x_norm = (x - cx) / fx;
        double y_norm = (y - cy) / fy;
        double phi_rad = Math.atan(x_norm);
        double theta_rad = Math.atan(y_norm / Math.sqrt(1.0 + x_norm * x_norm));
        return new float[]{(float) theta_rad, (float) phi_rad};
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {    }

}
