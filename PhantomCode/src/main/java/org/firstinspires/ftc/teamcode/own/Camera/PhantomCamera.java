package org.firstinspires.ftc.teamcode.own.Camera;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.Camera.Basement.PhantomProcessor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;


public class PhantomCamera {
    WebcamName firstWebcamName;
    BuiltinCameraDirection rotation;
    boolean UsingCamera;
    boolean IsOpenCvTrue;
    boolean IsAprilTagTrue;
    double x, y, z, heading;
    int cameraHeight;
    int cameraWidth;



    private VisionPortal visionPortal;
    public final AprilTagProcessor aprilTagProcessor = new AprilTagProcessor.Builder()
            .setOutputUnits(DistanceUnit.MM, AngleUnit.DEGREES)
            .build();

    public PhantomProcessor phantomProcessor = new PhantomProcessor();
    public boolean lp, rp;
    List<AprilTagDetection> detections;
    private double left_rect, right_rect;


    /**
     * конструктор для вашей камеры
     * constructor for your camera
     * @param firstWebcamName название ващей первой камеры; name of your first camera
     * @param usingCamera используете ли вы внешнюю камеру; Do you use external webcam
     * @param isOpenCvTrue используете ли вы опенсв; Do you use OpenCV pipelines
     * @param isAprilTagTrue используете ли вы април тэги; Do you use AprilTag

     */
    public PhantomCamera(WebcamName firstWebcamName, boolean usingCamera, boolean isOpenCvTrue, boolean isAprilTagTrue) {
        this.firstWebcamName = firstWebcamName;
        UsingCamera = usingCamera;
        IsOpenCvTrue = isOpenCvTrue;
        IsAprilTagTrue = isAprilTagTrue;

    }

    /**
     * конструктор для вашей камеры
     * constructor for your camera
     * @param rotation какую из встроенных камер вы используете; which of internal camera do you use
     * @param usingCamera используете ли вы внешнюю камеру; Do you use external webcam
     * @param isOpenCvTrue используете ли вы опенсв; Do you use OpenCV pipelines
     * @param isAprilTagTrue используете ли вы април тэги; Do you use AprilTag

     */
    public PhantomCamera(BuiltinCameraDirection rotation, boolean usingCamera, boolean isOpenCvTrue, boolean isAprilTagTrue) {
        this.rotation = rotation;
        UsingCamera = usingCamera;
        IsOpenCvTrue = isOpenCvTrue;
        IsAprilTagTrue = isAprilTagTrue;

    }

    /**
     * запуск камеры 
     * @param height высота камеры
     * @param width ширина камеры
     */
    public void startCameraEasy(int height, int width) {
        cameraHeight = height;
        cameraWidth = width;
        if (IsAprilTagTrue){
            aprilTagProcessor.setDecimation(2);
        }
        if (IsOpenCvTrue){
            phantomProcessor.init(cameraWidth, cameraHeight, null);
        }
        if (IsOpenCvTrue && IsAprilTagTrue){
            if (UsingCamera){
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(aprilTagProcessor, phantomProcessor)
                        .setCamera(firstWebcamName)
                        .enableLiveView(true)
                        .setAutoStopLiveView(true)
                        .build();
            } else {
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(aprilTagProcessor, phantomProcessor)
                        .setCamera(rotation)
                        .enableLiveView(true)
                        .setAutoStopLiveView(true)
                        .build();
            }

        } else if (IsOpenCvTrue) {

            if (UsingCamera){
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(phantomProcessor)
                        .setCamera(firstWebcamName)
                        .enableLiveView(true)
                        .setAutoStopLiveView(true)
                        .build();
            } else {
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(phantomProcessor)
                        .setCamera(rotation)
                        .enableLiveView(true)
                        .setAutoStopLiveView(true)
                        .build();
            }
        } else if (IsAprilTagTrue) {

            if (UsingCamera){
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(aprilTagProcessor)
                        .setCamera(firstWebcamName)
                        .enableLiveView(true)
                        .setAutoStopLiveView(true)
                        .build();
            } else {
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(aprilTagProcessor)
                        .setCamera(rotation)
                        .enableLiveView(true)
                        .setAutoStopLiveView(true)
                        .build();


            }
            if(IsOpenCvTrue){
                left_rect = phantomProcessor.getValLeft();
                right_rect = phantomProcessor.getValRight();
                if (left_rect >= 122){
                    lp = true;
                } else {
                    lp = false;
                }
                if (right_rect >= 122){
                    rp = true;
                } else {
                    rp = false;
                }
            }
        }
    }
    public void aprilTagDetection(){
        if (IsAprilTagTrue){
            detections = aprilTagProcessor.getDetections();
            for (AprilTagDetection detection : detections) {
                x = detection.ftcPose.x;
                y = detection.ftcPose.y;
                z = detection.ftcPose.z;
                heading = detection.ftcPose.yaw;
            }
        }
    }
    public void stopCameraEasy(){
        visionPortal.stopStreaming();
    }



}
