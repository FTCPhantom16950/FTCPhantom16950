package org.firstinspires.ftc.teamcode.FORTEST.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
@Disabled
@Autonomous
public class AutonomTester extends LinearOpMode {
    LinearOpMode opMode = new AutonomTester();
    ElapsedTime timer = new ElapsedTime();
    WheelBase wheelBase = new WheelBase(this);


    @Override
    public void runOpMode() throws InterruptedException {
        timer.reset();
        waitForStart();
        while (opModeIsActive()){
        }
    }
}
