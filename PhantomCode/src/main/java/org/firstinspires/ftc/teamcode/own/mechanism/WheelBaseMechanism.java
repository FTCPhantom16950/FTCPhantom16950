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
    public static boolean reverseLeftSide = false, reverseRightSide = true, reverseLeftOdo = false,
            reverseRightOdo = true, reverseBackOdo = true;
    SfMotor rb, lb, rf, lf;
    SfEncoder leftOdo, rightOdo, backOdo;

    @Override
    public void init() throws InterruptedException {
        rb = new SfMotor(Robot.INSTANCE.hw.get(DcMotorEx.class, "rb"), 28);
        lb = new SfMotor(Robot.INSTANCE.hw.get(DcMotorEx.class, "lb"), 28);
        rf = new SfMotor(Robot.INSTANCE.hw.get(DcMotorEx.class, "rf"), 28);
        lf = new SfMotor(Robot.INSTANCE.hw.get(DcMotorEx.class, "lf"), 28);

        leftOdo = new SfEncoder(Robot.INSTANCE.hw.get(DcMotorEx.class, "lb"), 2000);
        rightOdo = new SfEncoder(Robot.INSTANCE.hw.get(DcMotorEx.class, "rb"), 2000);
        backOdo = new SfEncoder(Robot.INSTANCE.hw.get(DcMotorEx.class, "lf"), 2000);
        if (reverseLeftSide) {
            lf.setDirection(DcMotor.Direction.REVERSE);
            lb.setDirection(DcMotor.Direction.REVERSE);
        }
        if (reverseRightSide) {
            rf.setDirection(DcMotor.Direction.REVERSE);
            rb.setDirection(DcMotor.Direction.REVERSE);
        }
        if (reverseLeftOdo) {
            leftOdo.setDirection(DcMotor.Direction.REVERSE);
        }
        if (reverseRightOdo) {
            rightOdo.setDirection(DcMotor.Direction.REVERSE);
        }
        if (reverseBackOdo) {
            backOdo.setDirection(DcMotor.Direction.REVERSE);
        }
        rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftOdo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightOdo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backOdo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rb.resetEncoder();
        lb.resetEncoder();
        rf.resetEncoder();
        lf.resetEncoder();

        leftOdo.resetEncoder();
        rightOdo.resetEncoder();
        backOdo.resetEncoder();
        Robot.INSTANCE.addOrUpdate(rb, "rb");
        Robot.INSTANCE.addOrUpdate(lb, "lb");
        Robot.INSTANCE.addOrUpdate(rf, "rf");
        Robot.INSTANCE.addOrUpdate(lf, "lf");

        Robot.INSTANCE.addOrUpdate(leftOdo, "leftOdo");
        Robot.INSTANCE.addOrUpdate(rightOdo, "rightOdo");
        Robot.INSTANCE.addOrUpdate(backOdo, "backOdo");
    }

    @Override
    public void read() {
        Mechanism.super.read();
        Robot.addData("leftOdo", leftOdo.getCurrentPosition());
        Robot.addData("rightOdo", rightOdo.getCurrentPosition());
        Robot.addData("backOdo", backOdo.getCurrentPosition());
        Robot.addData("leftOdo speed", PhantomMath.convertToRPM(leftOdo.getVelocity(), 2000));
        Robot.addData("rightOdo speed", PhantomMath.convertToRPM(rightOdo.getVelocity(), 2000));
        Robot.addData("backOdo speed", PhantomMath.convertToRPM(backOdo.getVelocity(), 2000));
    }
}
