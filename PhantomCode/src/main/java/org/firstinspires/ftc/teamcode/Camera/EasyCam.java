package org.firstinspires.ftc.teamcode.Camera;

import static org.firstinspires.ftc.vision.VisionPortal.makeMultiPortalView;

import android.content.Context;
import android.widget.Toast;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Camera.Basement.Camera;
import org.firstinspires.ftc.vision.VisionPortal;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvWebcam;

public class EasyCam extends Camera {
    WebcamName firstWebcamName, secondWebcamName;
    OpenCvInternalCamera.CameraDirection rotation;
    boolean UsingCamera;
    boolean IsOpenCvTrue;
    boolean IsAprilTagTrue;
    boolean IsTensorFlowTrue;
    int valLeft, valRight;
    public OpenCvWebcam camera;
    public OpenCvInternalCamera phonecam;
    int cameraHeight = 640;
    int cameraWidth = 480;
    int[] viewPort = makeMultiPortalView(2, VisionPortal.MultiPortalLayout.HORIZONTAL);

    Thread valGetter = new Thread(() -> {
        while (opModeInInit()){
            while (true){
                valLeft = getValLeft();
                valRight = getValRight();
            }
        }
    });

    /**
     * конструктор для вашей камеры
     * constructor for your camera
     * @param firstWebcamName название ващей первой камеры; name of your first camera
     * @param secondWebcamName название ващей второй камеры; name of your second camera
     * @param usingCamera используете ли вы внешнюю камеру; Do you use external webcam
     * @param isOpenCvTrue используете ли вы опенсв; Do you use OpenCV pipelines
     * @param isAprilTagTrue используете ли вы април тэги; Do you use AprilTag
     * @param isTensorFlowTrue используете ли вы тэнсор флоу; Do you use TensorFlow
     */
    public EasyCam(WebcamName firstWebcamName, WebcamName secondWebcamName, boolean usingCamera, boolean isOpenCvTrue, boolean isAprilTagTrue, boolean isTensorFlowTrue) {
        this.firstWebcamName = firstWebcamName;
        this.secondWebcamName = secondWebcamName;
        UsingCamera = usingCamera;
        IsOpenCvTrue = isOpenCvTrue;
        IsAprilTagTrue = isAprilTagTrue;
        IsTensorFlowTrue = isTensorFlowTrue;
    }

    /**
     * конструктор для вашей камеры
     * constructor for your camera
     * @param firstWebcamName название ващей первой камеры; name of your first camera
     * @param usingCamera используете ли вы внешнюю камеру; Do you use external webcam
     * @param isOpenCvTrue используете ли вы опенсв; Do you use OpenCV pipelines
     * @param isAprilTagTrue используете ли вы април тэги; Do you use AprilTag
     * @param isTensorFlowTrue используете ли вы тэнсор флоу; Do you use TensorFlow
     */
    public EasyCam(WebcamName firstWebcamName, boolean usingCamera, boolean isOpenCvTrue, boolean isAprilTagTrue, boolean isTensorFlowTrue) {
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
    public EasyCam(OpenCvInternalCamera.CameraDirection rotation, boolean usingCamera, boolean isOpenCvTrue, boolean isAprilTagTrue, boolean isTensorFlowTrue) {
        this.rotation = rotation;
        UsingCamera = usingCamera;
        IsOpenCvTrue = isOpenCvTrue;
        IsAprilTagTrue = isAprilTagTrue;
        IsTensorFlowTrue = isTensorFlowTrue;
    }

    /**
     * async listener for your external webcam
     * ассинхронный слушатель для нашей камеры
     */
    OpenCvCamera.AsyncCameraOpenListener external = new OpenCvCamera.AsyncCameraOpenListener() {
        @Override
        public void onOpened() {
            if (UsingCamera){
                camera = OpenCvCameraFactory.getInstance().createWebcam(firstWebcamName, viewPort[1]);
                camera.setPipeline(new OpenCvOld.StageSwitchingPipeline());
                camera.startStreaming(cameraWidth, cameraHeight);
            }
        }

        @Override
        public void onError(int errorCode) {
            FtcRobotControllerActivity activity = new FtcRobotControllerActivity();
            Toast toast = new Toast(activity);
            toast.setText("Камера не может запуститься");
        }
    };
    OpenCvCamera.AsyncCameraOpenListener internal = new OpenCvCamera.AsyncCameraOpenListener() {
        @Override
        public void onOpened() {
            if (!UsingCamera){
                phonecam = OpenCvCameraFactory.getInstance().createInternalCamera(rotation, viewPort[1]);
                phonecam.setPipeline(new OpenCvOld.StageSwitchingPipeline());
                phonecam.startStreaming(cameraWidth, cameraHeight);
            }
        }

        @Override
        public void onError(int errorCode) {
            FtcRobotControllerActivity activity = new FtcRobotControllerActivity();
            Toast toast = new Toast(activity);
            toast.setText("Камера не может запуститься");
        }
    };

    /**
     * старт вашей камеры
     */
    public void startEasyCam(){
        if (IsOpenCvTrue) {
            if (UsingCamera) {
                camera.openCameraDeviceAsync(external);
                valGetter.start();
            } else if (!UsingCamera) {
                phonecam.openCameraDeviceAsync(internal);
                valGetter.start();
            }
        }
        if (IsAprilTagTrue){
            if (UsingCamera){
                if (IsOpenCvTrue){
                   if(secondWebcamName != null){
                       visionPortal = new VisionPortal.Builder()
                               .addProcessor(aprilTagProcessor)
                               .setCamera(secondWebcamName)
                               .setLiveViewContainerId(viewPort[0])
                               .build();
                   } else {
                       visionPortal = new VisionPortal.Builder()
                               .addProcessor(aprilTagProcessor)
                               .setCamera(firstWebcamName)
                               .setLiveViewContainerId(viewPort[0])
                               .build();
                   }
                }  else {
                    visionPortal = new VisionPortal.Builder()
                            .addProcessor(aprilTagProcessor)
                            .setCamera(secondWebcamName)
                            .setLiveViewContainerId(viewPort[1])
                            .build();
                }
            } else if (!UsingCamera) {
                if (IsOpenCvTrue) {
                    visionPortal = new VisionPortal.Builder()
                            .addProcessor(aprilTagProcessor)
                            .setCamera(BuiltinCameraDirection.BACK)
                            .setLiveViewContainerId(viewPort[0])
                            .build();
                } else {
                    visionPortal = new VisionPortal.Builder()
                            .addProcessor(aprilTagProcessor)
                            .setCamera(BuiltinCameraDirection.BACK)
                            .setLiveViewContainerId(viewPort[1])
                            .build();
                }
            }
        } else if (IsTensorFlowTrue) {
            if (UsingCamera){
                if (IsOpenCvTrue){
                    if(secondWebcamName != null){
                        visionPortal = new VisionPortal.Builder()
                                .addProcessor(tfod)
                                .setCamera(secondWebcamName)
                                .setLiveViewContainerId(viewPort[0])
                                .build();
                    } else {
                        visionPortal = new VisionPortal.Builder()
                                .addProcessor(tfod)
                                .setCamera(firstWebcamName)
                                .setLiveViewContainerId(viewPort[0])
                                .build();
                    }
                }  else {
                    visionPortal = new VisionPortal.Builder()
                            .addProcessor(tfod)
                            .setCamera(secondWebcamName)
                            .setLiveViewContainerId(viewPort[1])
                            .build();
                }
            } else if (!UsingCamera) {
                if (IsOpenCvTrue) {
                    visionPortal = new VisionPortal.Builder()
                            .addProcessor(tfod)
                            .setCamera(BuiltinCameraDirection.BACK)
                            .setLiveViewContainerId(viewPort[0])
                            .build();
                } else {
                    visionPortal = new VisionPortal.Builder()
                            .addProcessor(tfod)
                            .setCamera(BuiltinCameraDirection.BACK)
                            .setLiveViewContainerId(viewPort[1])
                            .build();
                }
            }
        } else if (IsAprilTagTrue && IsTensorFlowTrue) {
            if (UsingCamera){
                if (IsOpenCvTrue){
                    if(secondWebcamName != null){
                        visionPortal = new VisionPortal.Builder()
                                .addProcessors(aprilTagProcessor, tfod)
                                .setCamera(secondWebcamName)
                                .setLiveViewContainerId(viewPort[0])
                                .build();
                    } else {
                        visionPortal = new VisionPortal.Builder()
                                .addProcessors(aprilTagProcessor, tfod)
                                .setCamera(firstWebcamName)
                                .setLiveViewContainerId(viewPort[0])
                                .build();
                    }
                }  else {
                    visionPortal = new VisionPortal.Builder()
                            .addProcessors(aprilTagProcessor, tfod)
                            .setCamera(secondWebcamName)
                            .setLiveViewContainerId(viewPort[1])
                            .build();
                }
            } else if (!UsingCamera) {
                if (IsOpenCvTrue) {
                    visionPortal = new VisionPortal.Builder()
                            .addProcessors(aprilTagProcessor, tfod)
                            .setCamera(BuiltinCameraDirection.BACK)
                            .setLiveViewContainerId(viewPort[0])
                            .build();
                } else {
                    visionPortal = new VisionPortal.Builder()
                            .addProcessors(aprilTagProcessor, tfod)
                            .setCamera(BuiltinCameraDirection.BACK)
                            .setLiveViewContainerId(viewPort[1])
                            .build();
                }
            }
        }
    }
}
