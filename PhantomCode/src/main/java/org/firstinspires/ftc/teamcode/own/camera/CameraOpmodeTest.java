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
        VisionPortal visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "C:/Users/glebe/OneDrive/Desktop/ftc test/PXL_20250913_144831667.jpg"))
                .addProcessor(new TestProcessor(this.telemetry))
                .setCameraResolution(new Size(640, 480))

                .build();

        waitForStart();
    }
}
