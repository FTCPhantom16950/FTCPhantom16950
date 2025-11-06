package org.firstinspires.ftc.teamcode.own.Actions.Test;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

public class MaxSpeedGetter extends Action {

    private static DcMotorEx lf, lb, rf, rb;
    private double MAX_RF_SPEED = 0,MAX_LF_SPEED = 0, MAX_RB_SPEED = 0, MAX_lB_SPEED = 0;
    @Override
    public void execute() {
        lf = Robot.get("mkL", DcMotorEx.class);
        lb = Robot.get("mkR", DcMotorEx.class);
//        rb = Robot.rb;
//        lb = Robot.lb;
//        rf = Robot.rf;
//        lf = Robot.lf;
//        double ticks_to_mm = Math.PI * 8 / 384.5;
        if (opMode.opModeIsActive()) {
//            lf.setPower(0.4);
//            lb.setPower(0.4);
//            rf.setPower(0.4);
//            rb.setPower(0.4);
//            opMode.sleep(1000);
//            lf.setPower(0);
//            lb.setPower(0);
//            rf.setPower(0);
//            rb.setPower(0);
//            MAX_RF_SPEED = rf.getCurrentPosition() * ticks_to_mm / 1 / 0.4;
//            MAX_LF_SPEED = lf.getCurrentPosition() * ticks_to_mm / 1 / 0.4;
//            MAX_RB_SPEED = rb.getCurrentPosition() * ticks_to_mm / 1 / 0.4;
//            MAX_lB_SPEED = lb.getCurrentPosition() * ticks_to_mm / 1 / 0.4;
//            PhantomOpMode.addData("MAX_RF_SPEED", MAX_RF_SPEED);
//            PhantomOpMode.addData("MAX_LF_SPEED", MAX_LF_SPEED);
//            PhantomOpMode.addData("MAX_RB_SPEED", MAX_RB_SPEED);
//            PhantomOpMode.addData("MAX_lB_SPEED", MAX_lB_SPEED);
//
//            PhantomOpMode.addData("rf", rf.getCurrentPosition());
//            PhantomOpMode.addData("lf", lf.getCurrentPosition());
//            PhantomOpMode.addData("rb", rb.getCurrentPosition());
//            PhantomOpMode.addData("lb()", lb.getCurrentPosition());
//            opMode.sleep(1000000);
        }
        while (opMode.opModeIsActive()){
            PhantomOpMode.addData("lb()", lf.getCurrentPosition());
            PhantomOpMode.addData("lb()", lb.getCurrentPosition());

        }
    }
}
