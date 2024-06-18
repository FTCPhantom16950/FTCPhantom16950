package org.firstinspires.ftc.teamcode.eocvsim;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp(name = "CamTest", group =  "TEST")
public class CameraTest extends OpMode {
    VisionPortal visionPortal;
    AprilTagProcessor aprilTagProcessor;
    CameraReworked processor = new CameraReworked();
    @Override
    public void init() {
        aprilTagProcessor = new AprilTagProcessor.Builder().build();
        visionPortal = new VisionPortal.Builder()
                .addProcessors(aprilTagProcessor)
                .setCamera(hardwareMap.get(CameraName.class, "Webcam 1"))
                .setLiveViewContainerId(0)
                .enableLiveView(true)
                .build();
        visionPortal.resumeStreaming();
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        visionPortal.stopStreaming();
    }

    @Override
    public void stop() {

    }
}
