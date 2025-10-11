package org.firstinspires.ftc.teamcode.own.camera;

import static org.opencv.core.CvType.CV_64F;

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
    float centerOfSquare = 0;
    double a, b;
    float x = 0f, y = 0f, z = 0f;
    double otn;
    float rectSizeOnCamera;
    double square;
    float c = (float) Math.sqrt(Math.pow(3.58, 2) + Math.pow(2.02, 2));
    float razmer = 49f;
    float f = 4f;
    float convers = c / 960;
    float h;
    Mat K = new Mat(3, 3, CV_64F);
    List<MatOfPoint> contours = new ArrayList<>();
    Mat openingImage = new Mat();
    Mat closingOutput = new Mat();
    Mat hierarchy = new Mat();
    Mat blurredImage = new Mat();
    Mat hsvImage = new Mat();
    Mat mask = new Mat();
    Mat morphOutput = new Mat();
    Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(dilateElementWidth, dilateElementHeight));
    Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(erodeElementWidth, erodeElementHeight));
    public static int hLow = 7, sLow = 70, vLow = 60,
            hHigh = 40, sHigh = 255, vHigh = 255,
            widthBlur = 1, heightBlur = 1, dilateElementWidth = 50, dilateElementHeight = 50,
            erodeElementWidth = 30, erodeElementHeight = 30;
    Scalar minValues = new Scalar(hLow, sLow, vLow);
    Scalar maxValues = new Scalar(hHigh, sHigh, vHigh);
    MatOfPoint2f[] contoursPoly;
    Telemetry telemetry;
    Rect[] rects;
    public static int minSquare = 0;

    public ArtifactProcessor(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public void init(int width, int height, CameraCalibration calibration) {
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
        Imgproc.blur(frame, blurredImage, new Size(widthBlur, heightBlur));
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
                square = (h * h) * Math.pow(rectSizeOnCamera, 2);
                if (square >= minSquare && (otn < 1.1) && (otn > 0.95) && rectSizeOnCamera <= 3600) {
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
                    x = (float) (rectSizeOnCamera
                            * Math.cos(calculateAngle((float) (rects[idx].tl().x + centerOfSquare), (float) (rects[idx].tl().y + centerOfSquare))[0])
                            * Math.cos(calculateAngle((float) (rects[idx].tl().x + centerOfSquare), (float) (rects[idx].tl().y + centerOfSquare))[1]));
                    y = (float) (rectSizeOnCamera
                            * Math.cos(calculateAngle((float) (rects[idx].tl().x + centerOfSquare), (float) (rects[idx].tl().y + centerOfSquare))[0])
                            * Math.sin(calculateAngle((float) (rects[idx].tl().x + centerOfSquare), (float) (rects[idx].tl().y + centerOfSquare))[1]));
                    z = (float) (rectSizeOnCamera
                            * Math.sin(calculateAngle((float) (rects[idx].tl().x + centerOfSquare), (float) (rects[idx].tl().y + centerOfSquare))[0]));
                    @SuppressLint("DefaultLocale")
                    String text = String.format("dist: %.1f, x: %.1f, y: %.1f, z: %.1f", rectSizeOnCamera, x, y, z);
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
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
//        telemetry.addData("bnmToPx", pxToMM);


    }

}
