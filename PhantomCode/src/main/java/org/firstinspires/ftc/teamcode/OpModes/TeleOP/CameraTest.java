package org.firstinspires.ftc.teamcode.OpModes.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.teamcode.Camera.Basement.PhantomProcessor;
import org.firstinspires.ftc.teamcode.Utils.PhantomMath;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp(name = "CamTest", group =  "TEST")
public class CameraTest extends OpMode {
    boolean poseLeft, poseRight;
    PhantomMath math = new PhantomMath();
    VisionPortal visionPortal;
    AprilTagProcessor aprilTagProcessor;
    PhantomProcessor cameraReworked = new PhantomProcessor();
    @Override
    public void init() {
        aprilTagProcessor = new AprilTagProcessor.Builder().build();
        visionPortal = new VisionPortal.Builder()
                .addProcessors(aprilTagProcessor, cameraReworked)
                .setCamera(hardwareMap.get(CameraName.class, "Webcam 1"))
                .setLiveViewContainerId(0)
                .enableLiveView(true)
                .build();
        visionPortal.resumeStreaming();
        Thread thread = new Thread(() ->{
            math.pipeLine(cameraReworked);
            poseLeft = math.leftPose;
            poseRight = math.rightPose;
            telemetry.addData("ValLeft", poseLeft);
            telemetry.addData("ValRight", poseRight);
            telemetry.update();
        });
        thread.start();

    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {
        visionPortal.stopStreaming();
    }

    @Override
    public void loop() {
        visionPortal.stopStreaming();
    }

    @Override
    public void stop() {

    }
}
