package org.firstinspires.ftc.teamcode.own.camera;

import android.graphics.Canvas;


import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

@Config
public class ArtifactProcessor implements VisionProcessor {
    Telemetry telemetry;
    Mat hierachy, output;
    public static double trashold1 = 0,
    trashhold2 = 0;
    List<MatOfPoint> contours = new ArrayList<>();
    MatOfPoint2f[] contoursPoly;
    Rect[] boundRect;
    List<MatOfPoint> contoursPolyList;
    Scalar color = new Scalar(100,100,100);
    public ArtifactProcessor(Telemetry telemetry) {
        this.telemetry = telemetry;
    }



    @Override
    public void init(int width, int height, CameraCalibration calibration) {
    hierachy = new Mat();
    output = new Mat();
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        output = frame.clone();
        Imgproc.cvtColor(frame, output, Imgproc.COLOR_RGB2GRAY);
        Imgproc.Canny(output,output, trashold1, trashhold2);
        Imgproc.findContours(output, contours, hierachy, Imgproc.RETR_TREE,Imgproc.CHAIN_APPROX_SIMPLE);
        contoursPoly = new MatOfPoint2f[contours.size()];
        boundRect = new Rect[contours.size()];
        contoursPolyList = new ArrayList<>(contoursPoly.length);
        for (MatOfPoint2f poly: contoursPoly){
            if (poly!= null){
                contoursPolyList.add(new MatOfPoint(poly.toArray()));
            }

        }
        for(int i =0; i < contours.size();i++){
            Imgproc.drawContours(frame, contoursPolyList, i, color);
            Imgproc.rectangle(frame,boundRect[i].tl(), boundRect[i].br(),color,2);
        }
        return frame;
    }


    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
        if (boundRect != null && contoursPoly != null) {
            for(int i = 0; i < contours.size(); i++){
                contoursPoly[i] = new MatOfPoint2f();
                Imgproc.approxPolyDP(new MatOfPoint2f(
                        contours.get(i).toArray()
                ), contoursPoly[i], 3, true);
                boundRect[i] = Imgproc.boundingRect(new MatOfPoint(contoursPoly[i].toArray()));
            }
        }
    }
}
