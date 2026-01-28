package org.firstinspires.ftc.teamcode.own.mechanism;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfEncoder;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;

@Configurable
@Config
public class WheelBaseMechanism implements Mechanism {
    private double MAX_SPEED_SIDE = 0,
            MAX_SPEED_FRONT = 0,
            MAX_SPEED_SPIN = 0;

    public static boolean reverseLeftSide = false, reverseRightSide = true;
    SfMotor rb, lb, rf, lf;

    @Override
    public void init() throws InterruptedException {
        rb = new SfMotor(Robot.INSTANCE.hw.get(DcMotorEx.class, "rb"), 28);
        lb = new SfMotor(Robot.INSTANCE.hw.get(DcMotorEx.class, "lb"), 28);
        rf = new SfMotor(Robot.INSTANCE.hw.get(DcMotorEx.class, "rf"), 28);
        lf = new SfMotor(Robot.INSTANCE.hw.get(DcMotorEx.class, "lf"), 28);


        if (reverseLeftSide) {
            lf.setDirection(DcMotor.Direction.REVERSE);
            lb.setDirection(DcMotor.Direction.REVERSE);
        }
        if (reverseRightSide) {
            rf.setDirection(DcMotor.Direction.REVERSE);
            rb.setDirection(DcMotor.Direction.REVERSE);
        }
        rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



        rb.resetEncoder();
        lb.resetEncoder();
        rf.resetEncoder();
        lf.resetEncoder();


        Robot.INSTANCE.addOrUpdate(rb, "rb");
        Robot.INSTANCE.addOrUpdate(lb, "lb");
        Robot.INSTANCE.addOrUpdate(rf, "rf");
        Robot.INSTANCE.addOrUpdate(lf, "lf");
    }

    @Override
    public void read() {
        Mechanism.super.read();
        Robot.INSTANCE.addData("MAXSIDESPEEDSIDE", MAX_SPEED_SIDE);
        Robot.INSTANCE.addData("MAXSIDESPEEDSPIN", MAX_SPEED_SPIN);
        Robot.INSTANCE.addData("MAXSIDESPEEDFRONT", MAX_SPEED_FRONT);

    }
}
