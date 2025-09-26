package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class PidKal implements Mechanism {
    public PidKal(PhantomOpMode opMode) {
        this.opMode = opMode;
    }

    @Override
    public boolean init() {
        xyn = opMode.hardwareMap.get(DcMotorEx.class, "motorPid");
        xyn.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        xyn.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        return true;
    }
    public static DcMotorEx xyn;
    private PhantomOpMode opMode;
}
