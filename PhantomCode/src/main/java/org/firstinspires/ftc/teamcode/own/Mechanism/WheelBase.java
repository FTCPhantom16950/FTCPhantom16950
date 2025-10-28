package org.firstinspires.ftc.teamcode.own.Mechanism;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.lb;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.lf;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;

public class WheelBase implements  Mechanism {

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
