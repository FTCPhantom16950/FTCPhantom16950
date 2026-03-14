package org.firstinspires.ftc.teamcode.own.actions.util;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class RotateOtnTest implements Action {
    DcMotorEx rotate;
    @Override
    public void execute() throws InterruptedException {
        rotate = Robot.INSTANCE.getRobotDevice("rotate", DcMotorEx.class);
        while (!Thread.currentThread().isInterrupted()){
            Robot.INSTANCE.addTelemetryData("Otnbetween degree and rotate", 90 / rotate.getCurrentPosition());
        }
    }
}
