package org.firstinspires.ftc.teamcode.Own.Mechanism;

import static org.firstinspires.ftc.teamcode.Own.Utils.Robot.hw;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.Own.Utils.Robot;

public class ParkovkaMechanism implements Mechanism {
    private static DcMotorEx motorEx;
    @Override
    public boolean init() {
        motorEx = hw.get(DcMotorEx.class, "podem");
        motorEx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorEx.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorEx.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Robot.addOrUpdate("podem", motorEx);
        return true;
    }
}
