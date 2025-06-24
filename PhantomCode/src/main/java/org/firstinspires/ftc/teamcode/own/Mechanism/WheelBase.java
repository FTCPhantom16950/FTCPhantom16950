package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;

public class WheelBase implements Mechanism {
    public static DcMotorEx rf, rb, lf, lb;
    private HardwareMap hw;

    public WheelBase(HardwareMap hw) {
        this.hw = hw;
    }

    @Override
    public boolean init() {
        rf = hw.get(DcMotorEx.class, "rf");
        rb = hw.get(DcMotorEx.class, "rb");
        lf = hw.get(DcMotorEx.class, "lf");
        lb = hw.get(DcMotorEx.class, "lb");
        lb.setDirection(DcMotorSimple.Direction.REVERSE);
        lf.setDirection(DcMotorSimple.Direction.REVERSE);
        return true;
    }
}
