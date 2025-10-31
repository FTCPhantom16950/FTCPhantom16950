package org.firstinspires.ftc.teamcode.own.Mechanism;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.lb;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.lf;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

public class WheelBase implements Mechanism {
    private static IMU imu;
    private HardwareMap hw;

    public WheelBase(HardwareMap hw) {
        this.hw = hw;
    }

    @Override
    public boolean init() {
        imu = hw.get(IMU.class, "imu");
        imu.initialize(
                new IMU.Parameters(
                        new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP
                                ,RevHubOrientationOnRobot.UsbFacingDirection.RIGHT)
                )
        );
        imu.resetYaw();

        rb = hw.get(DcMotorEx.class, "rb");
        rf = hw.get(DcMotorEx.class, "rf");
        lf = hw.get(DcMotorEx.class, "lf");
        lb = hw.get(DcMotorEx.class, "lb");
        lb.setDirection(DcMotorSimple.Direction.REVERSE);
        lf.setDirection(DcMotorSimple.Direction.REVERSE);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Robot.addOrUpdate("imu", imu);
        return true;
    }
}
