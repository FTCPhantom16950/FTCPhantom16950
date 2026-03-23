package org.firstinspires.ftc.teamcode.own.actions.auto;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class MoveRobotAutoAction implements Action {
    int time;
    DcMotorEx lf, lb, rf, rb;
    double x, y, rot;
    double botHeading, rotX, rotY, denominator, frontLeftPower, backLeftPower, frontRightPower, backRightPower;

    public MoveRobotAutoAction(double x, double y, double rot, int time) {
        this.x = x;
        this.y = y;
        this.rot = rot;
        this.time = time;
    }

    @Override
    public void execute() throws InterruptedException {
        lf = Robot.INSTANCE.getRobotDevice("lf", DcMotorEx.class);
        lb = Robot.INSTANCE.getRobotDevice("lb", DcMotorEx.class);
        rf = Robot.INSTANCE.getRobotDevice("rf", DcMotorEx.class);
        rb = Robot.INSTANCE.getRobotDevice("rb", DcMotorEx.class);
        if (!Thread.currentThread().isInterrupted()) {
            denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rot), 1);
            frontLeftPower = (y + x + rot) / denominator;
            backLeftPower = (y - x + rot) / denominator;
            frontRightPower = (y - x - rot) / denominator;
            backRightPower = (y + x - rot) / denominator;
            lf.setPower(frontLeftPower);
            lb.setPower(backLeftPower);
            rf.setPower(frontRightPower);
            rb.setPower(backRightPower);
            sleep(time);
            lf.setPower(0.01);
            lb.setPower(0.01);
            rf.setPower(0.01);
            rb.setPower(0.01);
            sleep(10);
        }
    }
}
