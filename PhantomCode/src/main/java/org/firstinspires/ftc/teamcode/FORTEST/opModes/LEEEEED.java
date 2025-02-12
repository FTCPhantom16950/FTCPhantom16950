package org.firstinspires.ftc.teamcode.FORTEST.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.DigitalChannelImpl;

@Autonomous
public class LEEEEED extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DigitalChannelImpl dg = hardwareMap.get(DigitalChannelImpl.class,"dg");
        dg.setMode(DigitalChannelController.Mode.OUTPUT);
        dg.setState(false);
        waitForStart();
        if(opModeIsActive()){
            dg.setState(true);
            sleep(2000);
        }
    }
}
