package org.firstinspires.ftc.teamcode.own.camera;

//import io.github.ftcphantom16950.phantomlib.camera.ArtifactProcessor;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.opencv.core.Size;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;

@Config
@Autonomous
public class CameraOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

    }
//    public static int minR1 = 150, minG1 = 100, minB1 = 100;
//    public static int maxR1 = 200, maxG1 = 200, maxB1 = 200;
//    public static int minR2 = 0, minG2 = 0, minB2 = 0;
//    public static int maxR2 = 0, maxG2 = 0, maxB2 = 0;
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        ArtifactProcessor artifactProcessor1 = ArtifactProcessor.newBuilder()
//                .setDistProperties(true,20,3000)
//                .setOtnProperties(true, 0.85f, 1.15f)
//                .setCameraMatrix(3.58F, 2.02F)
//                .setSize(127f)
//                .setF(4f)
//                .setBlurSize(new Size(3, 3))
//                .setPixelCameraHeight(960)
//                .setCameraPos(0, 0, 0)
//                .setCameraRot(0, 0, 0)
//                .setMinValues(minR1, minG1, minB1)
//                .setMaxValues(maxR1, maxG1, maxB1)
//                .build();
//        ArtifactProcessor artifactProcessor2 = ArtifactProcessor.newBuilder()
//                .setDistProperties(true,20,3000)
//                .setOtnProperties(true, 0.85f, 1.15f)
//                .setCameraMatrix(3.58F, 2.02F)
//                .setSize(127f)
//                .setF(4f)
//                .setBlurSize(new Size(3, 3))
//                .setPixelCameraHeight(960)
//                .setCameraPos(0, 0, 0)
//                .setCameraRot(0, 0, 0)
//                .setMinValues(minR2, minG2, minB2)
//                .setMaxValues(maxR2, maxG2, maxB2)
//                .build();
//        VisionPortal visionPortal = new VisionPortal.Builder()
//                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
//                .addProcessors(artifactProcessor1, artifactProcessor2)
//                .build();
//        FtcDashboard.getInstance().startCameraStream(visionPortal, 0);
//        waitForStart();
//    }
}
