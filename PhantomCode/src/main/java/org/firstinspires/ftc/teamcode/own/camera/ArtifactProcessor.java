package org.firstinspires.ftc.teamcode.own.camera;

import android.graphics.Canvas;


import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

@Config
public class ArtifactProcessor implements VisionProcessor {
    float c = 4f;
    float razmer = 51f;
    float f;
    float convers;
    float h;
    List<MatOfPoint> contours = new ArrayList<>();
    Mat hierarchy = new Mat();
    Mat blurredImage = new Mat();
    Mat hsvImage = new Mat();
    Mat mask = new Mat();
    Mat morphOutput = new Mat();
    Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(dilateElementWidth, dilateElementHeight));
    Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(erodeElementWidth, erodeElementHeight));
    public static int hLow = 7, sLow = 80, vLow = 80,
            hHigh = 10, sHigh = 255, vHigh = 255,
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
        f = calibration.focalLengthX;
        convers = c / 960;
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        Imgproc.blur(frame, blurredImage, new Size(widthBlur, heightBlur));
        Imgproc.cvtColor(blurredImage, hsvImage, Imgproc.COLOR_BGR2HSV);
        Core.inRange(hsvImage, minValues, maxValues, mask);

        Imgproc.erode(mask, morphOutput, erodeElement);
        Imgproc.erode(mask, morphOutput, erodeElement);

        Imgproc.dilate(mask, morphOutput, dilateElement);
        Imgproc.dilate(mask, morphOutput, dilateElement);
        Imgproc.findContours(morphOutput, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
        contoursPoly = new MatOfPoint2f[contours.size()];
        rects = new Rect[contours.size()];
        for (int idx = 0; idx < contours.size(); idx++) {
            if (contours.get(idx).toArray() != null) {
                contoursPoly[idx] = new MatOfPoint2f();
                Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(idx).toArray()), contoursPoly[idx], 3, true);
                rects[idx] = Imgproc.boundingRect(new MatOfPoint(contoursPoly[idx].toArray()));
                double a = (-rects[idx].tl().x + rects[idx].br().x),
                        b = (-rects[idx].tl().y + rects[idx].br().y);
                double otn = a / b;
                double square = a * b;
                if (square >= minSquare && (otn < 1.1) && (otn > 0.95)) {
                    h = (float) (b * convers);
                    float rectSizeOnCamera = (float) ((f * razmer) / h);

                    telemetry.addData("rectSizeOnCamera", rectSizeOnCamera);

                    telemetry.addData("otn", otn);
                    telemetry.addData("square", square);
                    telemetry.addData("focal", f);
                    telemetry.addData("size on camera", convers);
                    telemetry.addData("a", a);
                    telemetry.addData("b", b);
                    Imgproc.rectangle(frame, rects[idx].tl(), rects[idx].br(), new Scalar(255, 0, 0), 2);
                }
            }
        }
        telemetry.update();
        contours.clear();
        hierarchy.empty();
        morphOutput.empty();
        mask.empty();
//        morphOutput = frame;
        return null;
    }


    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
//        telemetry.addData("bnmToPx", pxToMM);


    }

}
