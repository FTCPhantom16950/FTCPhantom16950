package org.firstinspires.ftc.teamcode.own.actions.gamepadaction;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class WheelBaseAction implements Action {
    DcMotorEx lf, lb, rf, rb;
    Gamepad gamepad1, gamepad2;
    IMU imu;
    double x, y, rot;
    double botHeading, rotX, rotY, denominator, frontLeftPower, backLeftPower, frontRightPower, backRightPower;

    @Override
    public void execute() throws InterruptedException {
        gamepad1 = Robot.INSTANCE.getRobotData("Gamepad1", Gamepad.class);
        lf = Robot.INSTANCE.getRobotDevice("lf", DcMotorEx.class);
        lb = Robot.INSTANCE.getRobotDevice("lb", DcMotorEx.class);
        rf = Robot.INSTANCE.getRobotDevice("rf", DcMotorEx.class);
        rb = Robot.INSTANCE.getRobotDevice("rb", DcMotorEx.class);
        imu = Robot.INSTANCE.getRobotDevice("imu", IMU.class);
        while (!Thread.currentThread().isInterrupted()) {
            x = Math.pow(gamepad1.left_stick_x + gamepad1.right_stick_x * 0.8, 3);
            y = -Math.pow(gamepad1.left_stick_y + gamepad1.right_stick_y * 0.8, 3);
            rot = gamepad1.right_trigger - gamepad1.left_trigger;
            botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
//            rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
//            rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
            denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rot), 1);
            frontLeftPower = (y + x + rot) / denominator;
            backLeftPower = (y - x + rot) / denominator;
            frontRightPower = (y - x - rot) / denominator;
            backRightPower = (y + x - rot) / denominator;
            lf.setPower(frontLeftPower);
            lb.setPower(backLeftPower);
            rf.setPower(frontRightPower);
            rb.setPower(backRightPower);
            sleep(10);
        }
    }
}
