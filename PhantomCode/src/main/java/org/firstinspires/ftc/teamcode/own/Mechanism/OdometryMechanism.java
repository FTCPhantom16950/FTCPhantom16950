package org.firstinspires.ftc.teamcode.Own.Mechanism;

import static org.firstinspires.ftc.teamcode.Own.Utils.Robot.*;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.Own.Utils.Robot;

public class OdometryMechanism implements Mechanism {

    @Override
    public boolean init() {
        DcMotorEx leftOdometry = hw.get(DcMotorEx.class, "lf");
        DcMotorEx rightOdometry = hw.get(DcMotorEx.class, "lb");
        DcMotorEx horizontalOdometry = hw.get(DcMotorEx.class, "rf");
        rightOdometry.setDirection(DcMotorSimple.Direction.REVERSE);
        rightOdometry.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftOdometry.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontalOdometry.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.addOrUpdate("lf", leftOdometry);
        Robot.addOrUpdate("lb", rightOdometry);
        Robot.addOrUpdate("rf", horizontalOdometry);
        return true;
    }
}
