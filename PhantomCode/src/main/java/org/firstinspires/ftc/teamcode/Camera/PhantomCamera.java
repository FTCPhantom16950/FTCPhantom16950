package org.firstinspires.ftc.teamcode.Camera;

import static org.firstinspires.ftc.vision.VisionPortal.makeMultiPortalView;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Utils.PhantomMath;
import org.firstinspires.ftc.teamcode.Camera.Basement.PhantomProcessor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvWebcam;

public class PhantomCamera {
    PhantomMath math = new PhantomMath();
    WebcamName firstWebcamName;
    BuiltinCameraDirection rotation;
    boolean UsingCamera;
    boolean IsOpenCvTrue;
    boolean IsAprilTagTrue;
    boolean IsTensorFlowTrue;
    public OpenCvWebcam camera;
    int cameraHeight = 640;
    int cameraWidth = 480;
    public OpenCvInternalCamera phonecam;
    int[] viewPort = makeMultiPortalView(2, VisionPortal.MultiPortalLayout.HORIZONTAL);

    private VisionPortal visionPortal;
    private AprilTagProcessor aprilTagProcessor = new AprilTagProcessor.Builder()
            .setDrawAxes(true)
            .setDrawTagID(true)
            .setDrawCubeProjection(true)
            .build();
    public PhantomProcessor phantomProcessor;
    TfodProcessor tfod = new TfodProcessor.Builder().build();
    public boolean lp, rp;

    /**
     * конструктор для вашей камеры
     * constructor for your camera
     * @param firstWebcamName название ващей первой камеры; name of your first camera
     * @param usingCamera используете ли вы внешнюю камеру; Do you use external webcam
     * @param isOpenCvTrue используете ли вы опенсв; Do you use OpenCV pipelines
     * @param isAprilTagTrue используете ли вы април тэги; Do you use AprilTag
     * @param isTensorFlowTrue используете ли вы тэнсор флоу; Do you use TensorFlow
     */
    public PhantomCamera(WebcamName firstWebcamName, boolean usingCamera, boolean isOpenCvTrue, boolean isAprilTagTrue, boolean isTensorFlowTrue) {
        this.firstWebcamName = firstWebcamName;
        UsingCamera = usingCamera;
        IsOpenCvTrue = isOpenCvTrue;
        IsAprilTagTrue = isAprilTagTrue;
        IsTensorFlowTrue = isTensorFlowTrue;
    }

    /**
     * конструктор для вашей камеры
     * constructor for your camera
     * @param rotation какую из встроенных камер вы используете; which of internal camera do you use
     * @param usingCamera используете ли вы внешнюю камеру; Do you use external webcam
     * @param isOpenCvTrue используете ли вы опенсв; Do you use OpenCV pipelines
     * @param isAprilTagTrue используете ли вы април тэги; Do you use AprilTag
     * @param isTensorFlowTrue используете ли вы тэнсор флоу; Do you use TensorFlow
     */
    public PhantomCamera(BuiltinCameraDirection rotation, boolean usingCamera, boolean isOpenCvTrue, boolean isAprilTagTrue, boolean isTensorFlowTrue) {
        this.rotation = rotation;
        UsingCamera = usingCamera;
        IsOpenCvTrue = isOpenCvTrue;
        IsAprilTagTrue = isAprilTagTrue;
        IsTensorFlowTrue = isTensorFlowTrue;
    }

    public void startCameraEasy(int height, int width){


        cameraHeight = height;
        cameraWidth = width;
        if (IsOpenCvTrue && IsAprilTagTrue && IsTensorFlowTrue){
            if (UsingCamera){
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(aprilTagProcessor, tfod, phantomProcessor)
                        .setCamera(firstWebcamName)
                        .enableLiveView(true)
                        .build();
            } else {
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(aprilTagProcessor, tfod, phantomProcessor)
                        .setCamera(rotation)
                        .enableLiveView(true)
                        .build();
            }
        } else if (IsOpenCvTrue && IsAprilTagTrue){
            if (UsingCamera){
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(aprilTagProcessor, phantomProcessor)
                        .setCamera(firstWebcamName)
                        .enableLiveView(true)
                        .build();
            } else {
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(aprilTagProcessor, phantomProcessor)
                        .setCamera(rotation)
                        .enableLiveView(true)
                        .build();
            }
        } else if (IsOpenCvTrue && IsTensorFlowTrue){
            if (UsingCamera){
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(tfod, phantomProcessor)
                        .setCamera(firstWebcamName)
                        .enableLiveView(true)
                        .build();
            } else {
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(tfod, phantomProcessor)
                        .setCamera(rotation)
                        .enableLiveView(true)
                        .build();
            }
        } else if (IsTensorFlowTrue && IsAprilTagTrue) {
            if (UsingCamera) {
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(aprilTagProcessor, tfod)
                        .setCamera(firstWebcamName)
                        .enableLiveView(true)
                        .build();
            } else {
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(aprilTagProcessor, tfod)
                        .setCamera(rotation)
                        .enableLiveView(true)
                        .build();
            }
        } else if (IsOpenCvTrue) {
            if (UsingCamera){
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(phantomProcessor)
                        .setCamera(firstWebcamName)
                        .enableLiveView(true)
                        .build();
            } else {
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(phantomProcessor)
                        .setCamera(rotation)
                        .enableLiveView(true)
                        .build();
            }
        } else if (IsAprilTagTrue) {
            if (UsingCamera){
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(aprilTagProcessor)
                        .setCamera(firstWebcamName)
                        .enableLiveView(true)
                        .build();
            } else {
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(aprilTagProcessor)
                        .setCamera(rotation)
                        .enableLiveView(true)
                        .build();
            }
        } else if (IsTensorFlowTrue) {
            if (UsingCamera){
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(tfod)
                        .setCamera(firstWebcamName)
                        .enableLiveView(true)
                        .build();
            } else {
                visionPortal = new VisionPortal.Builder()
                        .addProcessors(tfod)
                        .setCamera(rotation)
                        .enableLiveView(true)
                        .build();

            }
            if(IsOpenCvTrue){
                math.pipeLine(phantomProcessor);
                lp = math.leftPose;
                rp = math.rightPose;
            }
        }


    }
    public void stopCameraEasy(){
        visionPortal.stopStreaming();

    }

}
