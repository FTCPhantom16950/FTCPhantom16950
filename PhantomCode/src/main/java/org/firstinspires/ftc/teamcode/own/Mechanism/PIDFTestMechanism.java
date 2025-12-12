package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

public class PIDFTestMechanism implements Mechanism {
    @Override
    public boolean init() throws InterruptedException {
        DcMotorEx dcMotorEx = Robot.hw.get(DcMotorEx.class, "test");
        dcMotorEx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dcMotorEx.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Robot.addOrUpdate("test", dcMotorEx);
        return true;
    }
}
