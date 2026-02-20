package org.firstinspires.ftc.teamcode.own.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfEncoder;


public class OdometryMechanism implements Mechanism {
    public static boolean reverseLeftOdo = false,
            reverseRightOdo = false, reverseBackOdo = false;
    SfEncoder leftOdo;
    SfEncoder rightOdo;
    SfEncoder backOdo;

    @Override
    public void init() throws InterruptedException {
        leftOdo = new SfEncoder(Robot.INSTANCE.hw.get(DcMotorEx.class, "lb"), 2000);
        rightOdo = new SfEncoder(Robot.INSTANCE.hw.get(DcMotorEx.class, "rb"), 2000);
        backOdo = new SfEncoder(Robot.INSTANCE.hw.get(DcMotorEx.class, "lf"), 2000);
        if (reverseLeftOdo) {
            leftOdo.setDirection(DcMotor.Direction.REVERSE);
        }
        if (reverseRightOdo) {
            rightOdo.setDirection(DcMotor.Direction.REVERSE);
        }
        if (reverseBackOdo) {
            backOdo.setDirection(DcMotor.Direction.REVERSE);
        }

        leftOdo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightOdo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backOdo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftOdo.resetEncoder();
        rightOdo.resetEncoder();
        backOdo.resetEncoder();
        Robot.INSTANCE.addOrUpdate(leftOdo, "leftOdo");
        Robot.INSTANCE.addOrUpdate(rightOdo, "rightOdo");
        Robot.INSTANCE.addOrUpdate(backOdo, "backOdo");
    }

    @Override
    public void read() throws InterruptedException {
        Mechanism.super.read();
        Robot.INSTANCE.addData("leftOdo", leftOdo.getCurrentPosition());
        Robot.INSTANCE.addData("rightodo", rightOdo.getCurrentPosition());
        Robot.INSTANCE.addData("backOdo", backOdo.getCurrentPosition());
    }
}
