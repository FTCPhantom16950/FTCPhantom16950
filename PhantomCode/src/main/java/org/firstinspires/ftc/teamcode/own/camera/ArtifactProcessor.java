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
    List<MatOfPoint> contours = new ArrayList<>();
    Mat hierarchy = new Mat();
    Mat blurredImage = new Mat();
    Mat hsvImage = new Mat();
    Mat mask = new Mat();
    Mat morphOutput = new Mat();
    Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(dilateElementWidth, dilateElementHeight));
    Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(erodeElementWidth, erodeElementHeight));
    public static int hLow = 170,sLow = 50,vLow = 50,
    hHigh = 180, sHigh = 110, vHigh = 200,
    widthBlur = 1, heightBlur = 1, dilateElementWidth = 30, dilateElementHeight = 30,
            erodeElementWidth = 20,erodeElementHeight = 20;
    Scalar minValues = new Scalar(hLow, sLow, vLow);
    Scalar maxValues  = new Scalar(hHigh, sHigh,vHigh);
    Mat output;

    @Override
    public void init(int width, int height, CameraCalibration calibration) {

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
        if (hierarchy.size().height > 0 && hierarchy.size().width > 0)
        {
            // for each contour, display it in blue
            for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0])
            {
                Imgproc.drawContours(frame, contours, idx, new Scalar(250, 0, 0));
            }
        }
        contours.clear();
        hierarchy.empty();
        morphOutput.empty();
        mask.empty();
//        morphOutput = frame;
        return null;
    }


    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {

    }
}
