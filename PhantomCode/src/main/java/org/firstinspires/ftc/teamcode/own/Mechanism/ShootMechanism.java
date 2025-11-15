package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

public class ShootMechanism implements Mechanism {
    @Override
    public boolean init() {

        DcMotorEx shootMotor = Robot.hw.get(DcMotorEx.class, "shoot");
        shootMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shootMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shootMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shootMotor.setPower(0);
        Robot.addOrUpdate("shoot", shootMotor);

        CRServo vrash = Robot.hw.get(CRServo.class, "vrash");
        vrash.setPower(0);
        Robot.addOrUpdate("vrash", vrash);

        return true;
    }
}
