package org.firstinspires.ftc.teamcode.own.camera;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
@Autonomous
public class CameraOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ArtifactProcessor artifactProcessor = new ArtifactProcessor(this.telemetry);
        VisionPortal visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .addProcessors(artifactProcessor)
                .build();
        waitForStart();
    }
}
