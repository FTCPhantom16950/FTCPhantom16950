package org.firstinspires.ftc.teamcode.own.Mechanism;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.hw;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

public class SOSaloMechanism implements Mechanism {
    @Override
    public boolean init() throws InterruptedException {
        DcMotorEx zasos = hw.get(DcMotorEx.class, "zasos");
        zasos.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        zasos.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Robot.addOrUpdate("zasos",zasos);
        return true;

    }
}
