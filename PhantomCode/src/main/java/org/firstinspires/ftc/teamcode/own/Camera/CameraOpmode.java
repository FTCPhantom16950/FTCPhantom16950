package org.firstinspires.ftc.teamcode.own.Camera;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.own.Camera.Basement.PhantomProcessor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp(name = "CameraOpmode", group = "Phantom")
public class CameraOpmode extends LinearOpMode {
    ElapsedTime time = new ElapsedTime();
    PhantomProcessor phantomProcessor = new PhantomProcessor();
    AprilTagProcessor aprilTagProcessor = new AprilTagProcessor.Builder()
            .setOutputUnits(DistanceUnit.MM, AngleUnit.DEGREES)
            .setDrawCubeProjection(true)
            .setDrawTagID(true)
            .setDrawAxes(true)
            .setDrawTagOutline(true)
            .setCameraPose(new Position(DistanceUnit.MM, 0,0,0, time.nanoseconds() ), new YawPitchRollAngles(AngleUnit.DEGREES, 0,0,0, time.nanoseconds()))
            .build();
    VisionPortal visionPortal;
    double x,y,z, heading;

    @Override
    public void runOpMode() throws InterruptedException {
        phantomProcessor.init(480, 640, null);
        visionPortal = new VisionPortal.Builder()
                .addProcessors(phantomProcessor, aprilTagProcessor)
                .setCamera(hardwareMap.get(WebcamName.class, "/Users/glebe/OneDrive/Documents/photo_2024-11-16_22-19-44.jpg"))
                .setCameraResolution(new Size(480, 640))
                .build();
        telemetry.addData("detections", aprilTagProcessor.getDetections());
        telemetry.update();
        waitForStart();
        while (opModeIsActive()){
            for (AprilTagDetection detection : aprilTagProcessor.getDetections()){
                heading = detection.ftcPose.yaw;
                x = detection.ftcPose.x;
                y = detection.ftcPose.y;
                z = detection.ftcPose.z;
            }
            telemetry.addData("x", x);
            telemetry.addData("y", y);
            telemetry.addData("z", z);
            telemetry.addData("heading", heading);
            telemetry.addData("detections", aprilTagProcessor.getDetections());
            telemetry.addData("left", phantomProcessor.getValLeft());
            telemetry.addData("right", phantomProcessor.getValRight());
            telemetry.update();
        }
    }
}
