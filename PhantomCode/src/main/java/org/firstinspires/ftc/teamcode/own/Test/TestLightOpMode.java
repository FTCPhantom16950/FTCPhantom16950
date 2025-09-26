package org.firstinspires.ftc.teamcode.own.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcore.internal.hardware.android.FakeAndroidBoard;
@Autonomous
public class TestLightOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DigitalChannel digit = hardwareMap.get(DigitalChannel.class, "light");
        DigitalChannel digit1 = hardwareMap.get(DigitalChannel.class, "light1");
        digit.setMode(DigitalChannel.Mode.OUTPUT);
        digit1.setMode(DigitalChannel.Mode.OUTPUT);
       while (opModeInInit()){
           digit.setState(true);
           digit1.setState(true);
           telemetry.addLine("on");
           telemetry.update();
           sleep(2000);
           digit.setState(false);
           digit1.setState(false);
           telemetry.addLine("off");
           telemetry.update();
           sleep(2000);
       }


        waitForStart();
    }
}
