package org.firstinspires.ftc.teamcode.own.Test;

import com.bylazar.panels.Panels;
import com.bylazar.panels.json.PanelsWidget;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamClient;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamServer;
import org.firstinspires.ftc.robotcore.internal.hardware.android.FakeAndroidBoard;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous
public class TestLightOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addLine(Integer.toString(Panels.INSTANCE.getServer().getListeningPort()));
        telemetry.update();
        waitForStart();
    }
}
