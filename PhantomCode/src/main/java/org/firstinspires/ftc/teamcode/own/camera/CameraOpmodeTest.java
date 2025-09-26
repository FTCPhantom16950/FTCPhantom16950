package org.firstinspires.ftc.teamcode.own.camera;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous
public class CameraOpmodeTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
//        int[] viewIds = VisionPortal.makeMultiPortalView(2, VisionPortal.MultiPortalLayout.VERTICAL);
        VisionPortal visionPortal = new VisionPortal.Builder()
//                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCamera(hardwareMap.get(WebcamName.class, "C:/Users/glebe/OneDrive/Desktop/ftc test/PXL_20250913_144831667.jpg"))
                .addProcessor(new TestProcessor(this.telemetry))
                .setCameraResolution(new Size(640, 480))
//                .setLiveViewContainerId(viewIds[0])
                .build();
//        VisionPortal visionPortal1 = new VisionPortal.Builder()
//                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
//                .addProcessor(new ArtifactProcessor2(this.telemetry))
//                .setLiveViewContainerId(viewIds[1])
//                .build();
        waitForStart();
    }
}
