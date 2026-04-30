package org.firstinspires.ftc.teamcode.kirill;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Autonomous
public class Forward extends OpMode {
    private IMU degress;
    private DcMotorEx rf;
    private DcMotorEx rb;
    private DcMotorEx lf;
    private DcMotorEx lb;

    @Override
    public void init() {
        degress = hardwareMap.get(IMU.class, "imu");
        rf = hardwareMap.get(DcMotorEx.class, "rf");
        rb = hardwareMap.get(DcMotorEx.class, "rb");
        lf = hardwareMap.get(DcMotorEx.class, "lf");
        lb = hardwareMap.get(DcMotorEx.class, "lb");

        degress.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP)));
        degress.resetYaw();

        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rf.setDirection(DcMotorSimple.Direction.REVERSE);

        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rb.setDirection(DcMotorSimple.Direction.REVERSE);

        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rf.setPower(0);
        rb.setPower(0);
        lf.setPower(0);
        lb.setPower(0);
    }

    @Override
    public void loop() {
        move(0, 0.3, 0, 2000);
        rotate(0.5, -90);
        move(0, 0.3, 0, 2000);
        rotate(0.5, -180);
        move(0, 0.3, 0, 2000);
        rotate(0.5, 90);
        move(0, 0.3, 0, 2000);
        rotate(0.5, 0);
    }

    public void rotate(double speed, double rotate) {
        double botHeading, rotX, rotY, frontLeftPower, backLeftPower, frontRightPower, backRightPower;
        frontLeftPower = speed;
        backLeftPower = speed;
        frontRightPower = -speed;
        backRightPower = -speed;

        rf.setPower(frontRightPower);
        rb.setPower(backRightPower);
        lf.setPower(frontLeftPower);
        lb.setPower(backLeftPower);

        double currentRotate = degress.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

        while ((currentRotate <= -1 + rotate || currentRotate >= 1 + rotate) && !Thread.currentThread().isInterrupted()) {
            telemetry.addData("soul", currentRotate);
            telemetry.update();
            currentRotate = degress.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        }
        rf.setPower(0);
        rb.setPower(0);
        lf.setPower(0);
        lb.setPower(0);
    }

    public void move(double x, double y, double rot, double sleep) {
        double botHeading, rotX, rotY, denominator, frontLeftPower, backLeftPower, frontRightPower, backRightPower;
        denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rot), 1);
        frontLeftPower = (y + x + rot) / denominator;
        backLeftPower = (y - x + rot) / denominator;
        frontRightPower = (y - x - rot) / denominator;
        backRightPower = (y + x - rot) / denominator;

        rf.setPower(frontRightPower);
        rb.setPower(backRightPower);
        lf.setPower(frontLeftPower);
        lb.setPower(backLeftPower);

        ElapsedTime time = new ElapsedTime();
        time.reset();

        while ((time.milliseconds() < sleep) && !Thread.currentThread().isInterrupted()) {

        }
        rf.setPower(0);
        rb.setPower(0);
        lf.setPower(0);
        lb.setPower(0);
    } /* я дибил */
}
