package org.firstinspires.ftc.teamcode.own.Camera;

import android.graphics.Color;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ImageRegion;
import org.opencv.core.RotatedRect;

import java.util.List;

@TeleOp(name = "CameraOpmode", group = "Phantom")
public class CameraOpmode extends LinearOpMode {
    ElapsedTime time = new ElapsedTime();@Override
    public void runOpMode() throws InterruptedException {
        CameraStarter cameraStarter = new CameraStarter(this);
        cameraStarter.init();
        waitForStart();
        time.reset();
        while (opModeIsActive()) {
        cameraStarter.play();

//            ColorBlobLocatorProcessor.Util.filterByAspectRatio(2, 4, blobsBlue);
//            ColorBlobLocatorProcessor.Util.filterByAspectRatio(2, 4, blobRed);
//            ColorBlobLocatorProcessor.Util.filterByAspectRatio(2, 4, blobsYellow);
            // Step through the list of detections and display info for each one.


            // Add "key" information to telemetry

            telemetry.update();
            sleep(50);
        }
        if(isStopRequested()){
//            visionPortal.close();
        }
        }
}
