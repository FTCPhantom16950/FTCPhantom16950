package org.firstinspires.ftc.teamcode.own.camera;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.CvType;
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
import java.util.Random;

public class TestProcessor implements VisionProcessor {
    Telemetry telemetry;
    private Random rng = new Random(12345);

    public TestProcessor(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    float scale = 1;

    Mat gray, blur, detection, dest, blueMask, purpelMask, hsv, output, maskedImage, circles;
    List<MatOfPoint> contours = new ArrayList<>();
    Mat hierarchy = new Mat();
    Scalar blueHigh = new Scalar(100, 250, 250),
            blueLow = new Scalar(40, 150, 150),
            purpleLow = new Scalar(50, 50, 50),
            purpleHigh = new Scalar(200, 200, 200);
    Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(9 * scale, 9 * scale));
    Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(2.5 * scale, 2.5 * scale));
    Mat drawing;
    public static double threshold = 100, thr2 = 150;

    @Override
    public void init(int width, int height, CameraCalibration calibration) {
        output = new Mat(); // Keep this for now, but will be re-initialized in processFrame
        gray = new Mat();
        blur = new Mat();
        detection = new Mat();
        dest = new Mat();
        hsv = new Mat();
        purpelMask = new Mat();
        blueMask = new Mat();
        maskedImage = new Mat();
        drawing = new Mat();
        circles = new Mat();
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        // The original `drawing = frame.clone();` was commented out. If you intend to use the original frame as a base for drawing, uncomment it.
//        drawing = frame.clone();
//        Imgproc.GaussianBlur(gray, blur, new Size(3, 3), 0, 0);
        Imgproc.cvtColor(frame, hsv, Imgproc.COLOR_RGB2HSV);
        Core.inRange(hsv, blueLow, blueHigh, blueMask);
        Core.inRange(hsv, purpleLow, purpleHigh, frame );
//        Core.add(blueMask, purpelMask, hsv);
//        Imgproc.erode(hsv, maskedImage, erodeElement);
//        Imgproc.dilate(maskedImage, frame, dilateElement);
//
//        Imgproc.findContours(maskedImage, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);

//
//        drawing = Mat.zeros(frame.size(), frame.channels()); // Initialize drawing as a black canvas
//        MatOfPoint2f[] contoursPoly = new MatOfPoint2f[contours.size()];
//        Rect[] boundRect = new Rect[contours.size()];
//
//        for (int i = 0; i < contours.size(); i++) {
//            contoursPoly[i] = new MatOfPoint2f();
//            Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), contoursPoly[i], 3, true);
//            boundRect[i] = Imgproc.boundingRect(new MatOfPoint(contoursPoly[i].toArray()));
//
//        }
//        List<MatOfPoint> contoursPolyList = new ArrayList<>(contoursPoly.length);
//        for (MatOfPoint2f poly : contoursPoly) {
//            contoursPolyList.add(new MatOfPoint(poly.toArray()));
//        }
//        for (int i = 0; i < contours.size(); i++) {
////            telemetry.addData("RATION", (contoursPolyList.get(i).height() / contoursPolyList.get(i).width()));
////            telemetry.update();
//            if (((double) contoursPolyList.get(i).width() / contoursPolyList.get(i).height()) <= 0.25) {
//                Scalar color = new Scalar(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
//                Imgproc.drawContours(drawing, contoursPolyList, i, color);
//                Imgproc.circle(frame, centers[i], (int) radius[i][0], color, 2);
//
//            }
//            for (MatOfPoint2f poly : contoursPoly) {
//                poly.release();
//            }
//        }
        // Removed output.copySize(frame); and drawing.copySize(frame); as they are no longer needed.
        drawing.convertTo(drawing, CvType.CV_32F);
        output.release(); // Keep this for now, but will be re-initialized in processFrame
        gray.release();
        blur.release();
        detection.release();
        dest.release();
        hsv.release();
        purpelMask.release();
        blueMask.release();
        maskedImage.release();
        drawing.release();


//        output.convertTo(output,CvType.CV_32F);

//        Core.scaleAdd(frame, 1, drawing, frame);

//        dest = frame;
//        Imgproc.cvtColor(dest, dest, Imgproc.COLOR_BGR2GRAY);
//        Imgproc.threshold(dest,dest, -1,0,1);
//        frame.copyTo(dest, detection);

        return captureTimeNanos;
    }

    private android.graphics.Rect makeGraphicsRect(Rect rect, float scaleBmpPxToCanvasPx) {
        int left = Math.round(rect.x * scaleBmpPxToCanvasPx);
        int top = Math.round(rect.y * scaleBmpPxToCanvasPx);
        int right = left + Math.round(rect.width * scaleBmpPxToCanvasPx);
        int bottom = top + Math.round(rect.height * scaleBmpPxToCanvasPx);
        return new android.graphics.Rect(left, top, right, bottom);
    }


    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight,
                            float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
        scale = scaleBmpPxToCanvasPx;


    }
}