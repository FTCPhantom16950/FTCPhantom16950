package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;

public class TestMechanism implements Mechanism {
    public static DcMotorEx pod;
    HardwareMap hw;

    public TestMechanism(HardwareMap hw) {
        this.hw = hw;
    }

    @Override
    public boolean init() {
        pod = hw.get(DcMotorEx.class, "rf");
        return true;
    }
}
