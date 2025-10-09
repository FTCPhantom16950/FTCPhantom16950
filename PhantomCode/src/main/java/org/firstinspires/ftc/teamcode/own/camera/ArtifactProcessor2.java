package org.firstinspires.ftc.teamcode.own.camera;

import android.graphics.Canvas;

import com.acmerobotics.dashboard.config.Config;

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

@Config
public class ArtifactProcessor2 implements VisionProcessor {
    Mat gray = new Mat();
    Mat circles = new Mat();
    Mat output = new Mat();
    @Override
    public void init(int width, int height, CameraCalibration calibration) {

    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.medianBlur(gray, gray, 5);
        Imgproc.HoughCircles(gray,
                circles,
                Imgproc.HOUGH_GRADIENT,
                1.0,
                (double)gray.rows()/16,
                100.0,
                30.0);
        return null;
    }


    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
//        if (!circles.empty()){
//            for (int x = 0; x < circles.cols(); x++) {
//                double[] c = circles.get(0, x);
//                Point center = new Point(Math.round(c[0]), Math.round(c[1]));
//                Imgproc.circle(output, center, 1, new Scalar(0,100,100), 3, 8, 0 );
//                int radius = (int) Math.round(c[2]);
//                Imgproc.circle(output, center, radius, new Scalar(255,0,255), 3, 8, 0 );
//            }
//        }
    }
}
