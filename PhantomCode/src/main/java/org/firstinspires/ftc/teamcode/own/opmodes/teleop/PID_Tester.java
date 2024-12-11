package org.firstinspires.ftc.teamcode.own.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
@TeleOp
public class PID_Tester extends LinearOpMode{
    VerticalSlider verticalSlider = new VerticalSlider(this);
    @Override
    public void runOpMode() throws InterruptedException {
        verticalSlider.init();
        verticalSlider.pidThread.start();
        waitForStart();
        while(opModeIsActive()){
            verticalSlider.test();
        }
    }
}