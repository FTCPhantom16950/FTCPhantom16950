package org.firstinspires.ftc.teamcode.Own.Mechanism;

import static org.firstinspires.ftc.teamcode.Own.Utils.Robot.lb;
import static org.firstinspires.ftc.teamcode.Own.Utils.Robot.lf;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import static org.firstinspires.ftc.teamcode.Own.Utils.Robot.*;

import org.firstinspires.ftc.teamcode.Own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.Own.Utils.Robot;

public class WheelBase implements Mechanism {
    HardwareMap hw;
    private static DcMotorEx leftOdo, rightOdo;
    public WheelBase() {

    }

    @Override
    public boolean init() {
        hw = Robot.hw;
//        leftOdo = hw.get(DcMotorEx.class,"mkL");
//        leftOdo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftOdo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        rightOdo = hw.get(DcMotorEx.class,"mkL");
//        rightOdo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightOdo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rb = hw.get(DcMotorEx.class, "rb");
        rf = hw.get(DcMotorEx.class, "rf");
        lf = hw.get(DcMotorEx.class, "lf");
        lb = hw.get(DcMotorEx.class, "lb");
        rf.setDirection(DcMotorSimple.Direction.REVERSE);
        rb.setDirection(DcMotorSimple.Direction.REVERSE);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        Robot.addOrUpdate("mkL", leftOdo);
//        Robot.addOrUpdate("mkR", rightOdo);
        return true;
    }
}
