package org.firstinspires.ftc.teamcode.own.actions.util;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class TicksToDegreeAction implements Action {
    double coef = 0;
    double targetAngle = 180;
    DcMotorEx rotate;

    @Override
    public void execute() throws InterruptedException {
        rotate = Robot.INSTANCE.getRobotDevice("rotate", DcMotorEx.class);
        while (!Thread.currentThread().isInterrupted()){
            coef = targetAngle / rotate.getCurrentPosition();
            Robot.INSTANCE.addTelemetryData("rotate.getCurrentPosition();", rotate.getCurrentPosition());
            Robot.INSTANCE.addTelemetryData("coef", coef);
        }
    }
}
