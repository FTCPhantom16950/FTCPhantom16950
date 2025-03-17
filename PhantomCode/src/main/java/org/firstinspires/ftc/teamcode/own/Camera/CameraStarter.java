package org.firstinspires.ftc.teamcode.own.Camera;

import android.graphics.Color;
import android.util.Size;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
@Config
public class CameraStarter {
    public static double left = 0,
    right = 1,
    top = 0.2,
    bottom = -1;
    LinearOpMode opMode;
    AprilTagProcessor aprilTagProcessor;
    ColorBlobLocatorProcessor colorLocatorYellow, colorLocatorRed, colorLocatorBlue;
    VisionPortal visionPortal;
    List<AprilTagDetection> currentDetections;
    List<ColorBlobLocatorProcessor.Blob> blobsYellow, blobRed, blobsBlue;
    public CameraStarter(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public void init(){
        HardwareMap hardwareMap = opMode.hardwareMap;
         colorLocatorBlue = new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(ColorRange.BLUE).setRoi(ImageRegion.asUnityCenterCoordinates(left,top,right,bottom))
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
                .setDrawContours(true)
                .setBlurSize(5)
                .setDilateSize(3)
                .setErodeSize(4)
                .setContourColor(Color.BLUE)
                .setBoxFitColor(Color.BLUE)
                .build();
         colorLocatorRed = new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(ColorRange.RED)
                .setRoi(ImageRegion.asUnityCenterCoordinates(left,top,right,bottom))
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
                .setDrawContours(true)
                .setBlurSize(5)
                .setDilateSize(3)
                .setErodeSize(4)
                .setContourColor(Color.RED)
                .setBoxFitColor(Color.RED)
                .build();
         colorLocatorYellow = new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(ColorRange.YELLOW)
                 .setRoi(ImageRegion.asUnityCenterCoordinates(left,top,right,bottom))
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
                .setDrawContours(true)
                .setBlurSize(5)
                .setDilateSize(3)
                .setErodeSize(4)
                .setContourColor(Color.YELLOW)
                .setBoxFitColor(Color.YELLOW)
                .build();
        aprilTagProcessor = new AprilTagProcessor.Builder()
                .setDrawCubeProjection(true)
                .setDrawAxes(true)
                .setDrawTagID(true)
                .setCameraPose(new Position(DistanceUnit.MM,0,0,0,0), new YawPitchRollAngles(AngleUnit.DEGREES,90,90,0,0))
                .build();
        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "WebCam"))
                .setShowStatsOverlay(true)
                .setCameraResolution(new Size(640, 480))
                .addProcessors(aprilTagProcessor,colorLocatorBlue,colorLocatorRed,colorLocatorYellow)
                .build();
    }
    public void play(){
        currentDetections = aprilTagProcessor.getDetections();
        blobsBlue = colorLocatorBlue.getBlobs();
        blobRed = colorLocatorRed.getBlobs();
        blobsYellow = colorLocatorYellow.getBlobs();

        ColorBlobLocatorProcessor.Util.filterByArea(400, 8000, blobsBlue);            ColorBlobLocatorProcessor.Util.filterByDensity(0.78,1,blobsBlue);
        ColorBlobLocatorProcessor.Util.filterByDensity(0.78,1,blobsBlue);
        ColorBlobLocatorProcessor.Util.filterByArea(400, 8000, blobRed);
        ColorBlobLocatorProcessor.Util.filterByDensity(0.78,1,blobRed);
        ColorBlobLocatorProcessor.Util.filterByArea(400, 8000, blobsYellow);
        ColorBlobLocatorProcessor.Util.filterByDensity(0.78,1,blobsYellow);
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
//                telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
//                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)",
//                        detection.robotPose.getPosition().x,
//                        detection.robotPose.getPosition().y,
//                        detection.robotPose.getPosition().z));
//                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)",
//                        detection.robotPose.getOrientation().getPitch(AngleUnit.DEGREES),
//                        detection.robotPose.getOrientation().getRoll(AngleUnit.DEGREES),
//                        detection.robotPose.getOrientation().getYaw(AngleUnit.DEGREES)));
            } else {
//                telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
//                telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
            for(ColorBlobLocatorProcessor.Blob b : blobsBlue)
            {
                RotatedRect boxFit = b.getBoxFit();
//                telemetry.addLine(String.format("%5d  %4.2f   %5.2f  (%3d,%3d), blue",
//                        b.getContourArea(), b.getDensity(), b.getAspectRatio(), (int) boxFit.center.x, (int) boxFit.center.y, (int) boxFit.angle));
            }
            for(ColorBlobLocatorProcessor.Blob b : blobRed)
            {
                RotatedRect boxFit = b.getBoxFit();
//                telemetry.addLine(String.format("%5d  %4.2f   %5.2f  (%3d,%3d), red",
//                        b.getContourArea(), b.getDensity(), b.getAspectRatio(), (int) boxFit.center.x, (int) boxFit.center.y, (int) boxFit.angle));
            }
//            for(ColorBlobLocatorProcessor.Blob b : blobsYellow)
//            {
//                RotatedRect boxFit = b.getBoxFit();
////                telemetry.addLine(String.format("%5d  %4.2f   %5.2f  (%3d,%3d), yellow",
////                        b.getContourArea(), b.getDensity(), b.getAspectRatio(), (int) boxFit.center.x, (int) boxFit.center.y, (int) boxFit.angle));
//            }
        }   // end for() loop

    }
}
