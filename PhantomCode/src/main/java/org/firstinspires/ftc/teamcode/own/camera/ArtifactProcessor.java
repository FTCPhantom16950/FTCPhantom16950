package org.firstinspires.ftc.teamcode.own.camera;

import android.graphics.Canvas;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class ArtifactProcessor implements VisionProcessor {
    Telemetry telemetry;
    public static double thr_value = 84.0, max = 255;
    public static int type = 1;
    Mat output, gray, hsv, blur;
    public static int top, bottom, left, right;

    public ArtifactProcessor(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    Mat mat;

    @Override
    public void init(int width, int height, CameraCalibration calibration) {
        width = 1280;
        height = 720;
//        output = new Mat();
        gray = new Mat();
        hsv = new Mat();
        blur = new Mat();
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        output = frame;
//        Imgproc.cvtColor(frame, output, Imgproc.COLOR_BGR2HSV);
//        Core.inRange(output, low, high, output);
        Imgproc.GaussianBlur( frame, blur, new Size(7, 7), 0, 0, Core.BORDER_DEFAULT );
//        Core.copyMakeBorder(frame, output, (int) (0.05 * frame.rows()), (int) (0.05 * frame.rows()), (int) (0.05 * frame.cols()), (int) (0.05 * frame.cols()), Core.BORDER_CONSTANT, new Scalar(255, 255, 255));
//  median - very slow blur norm
//        Imgproc.blur(frame, frame, new Size(5, 5), new Point(-1, -1));
//        Imgproc.stackBlur(frame, output, new Size(21,21));
        Imgproc.cvtColor(blur, gray, Imgproc.COLOR_BGR2GRAY);
//
        Imgproc.threshold(gray, output, thr_value, max, type);
//        Imgproc.adaptiveThreshold(gray,output,100, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 21, 3);
        return frame;
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
        top = (int) (onscreenWidth * scaleCanvasDensity);
        bottom = (int) (onscreenWidth * scaleCanvasDensity);
        left = (int) (onscreenHeight * scaleCanvasDensity);
        right = (int) (onscreenHeight * scaleCanvasDensity);
    }
}
