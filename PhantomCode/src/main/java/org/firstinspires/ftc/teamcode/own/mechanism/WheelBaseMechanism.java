package org.firstinspires.ftc.teamcode.own.mechanism;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;

@Config
@Configurable
public class WheelBaseMechanism implements Mechanism {
    HardwareMap hw;
    DcMotorEx lf,lb,rf,rb;

    public WheelBaseMechanism(HardwareMap hw) {
        this.hw = hw;
    }

    @Override
    public void init() throws InterruptedException {
        lf = hw.get(DcMotorEx.class, "lf");
        lb = hw.get(DcMotorEx.class, "lb");
        rf = hw.get(DcMotorEx.class, "rf");
        rb = hw.get(DcMotorEx.class, "rb");

        rf.setDirection(DcMotorEx.Direction.REVERSE);
        rb.setDirection(DcMotorEx.Direction.REVERSE);

        lf.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        lb.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rb.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        lf.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        lf.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        lb.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rf.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rb.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        Robot.INSTANCE.addRobotDevice("lf", lf);
        Robot.INSTANCE.addRobotDevice("lb", lb);
        Robot.INSTANCE.addRobotDevice("rf", rf);
        Robot.INSTANCE.addRobotDevice("rb", rb);
    }
}
