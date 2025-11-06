package org.firstinspires.ftc.teamcode.own.Actions.Test;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

public class TestDrive extends Action {
    private static final double MAX_SPEED_FORWARD = 0, MAX_SPEED_SIDE = 0, MAX_SPEED_SPIN = 0,
            DISTANCE_BETWEEN_WHEELS_ONE_BOARD = 287, DISTANCE_BETWEEN_WHEELS_DIFFERENT_BOARDS = 335,
            MAX_RF_SPEED = -140,MAX_LF_SPEED = 140, MAX_RB_SPEED = 140, MAX_lB_SPEED = 140;
    private static DcMotorEx lb, lf, rb, rf;
    private static double lbSpeed, lfSpeed, rbSpeed, rfSpeed,
            lbPower, lfPower, rbPower, rfPower,
    vxTarget, vyTarget, vRotTarget;

    @Override
    public void execute() {
        lb = Robot.lb;
        lf = Robot.lf;
        rb = Robot.rb;
        rf = Robot.rf;
        while (opMode.opModeIsActive()) {
            vxTarget = MAX_SPEED_SIDE * (gamepadDriver.left_stick_x +
                    0.8 * gamepadDriver.right_stick_x);
            vxTarget = Math.pow(vx, 3);
            vyTarget = MAX_SPEED_FORWARD * (-gamepadDriver.left_stick_y * 1.1 -
                    0.8 * gamepadDriver.right_stick_y);
            vyTarget = Math.pow(vy, 3);
            vRotTarget = MAX_SPEED_SPIN * (-gamepadDriver.right_trigger + gamepadDriver.left_trigger);
            vRotTarget = Math.pow(vRot, 3);
            lfSpeed = Range.clip(vx - vy - (DISTANCE_BETWEEN_WHEELS_DIFFERENT_BOARDS +
                    DISTANCE_BETWEEN_WHEELS_ONE_BOARD) * vRot, -1, 1);
            lbSpeed = Range.clip(vx + vy - (DISTANCE_BETWEEN_WHEELS_DIFFERENT_BOARDS +
                    DISTANCE_BETWEEN_WHEELS_ONE_BOARD) * vRot, -1, 1);
            rfSpeed = Range.clip(vx - vy + (DISTANCE_BETWEEN_WHEELS_DIFFERENT_BOARDS +
                    DISTANCE_BETWEEN_WHEELS_ONE_BOARD) * vRot, -1, 1);
            rbSpeed = Range.clip(vx + vy + (DISTANCE_BETWEEN_WHEELS_DIFFERENT_BOARDS +
                    DISTANCE_BETWEEN_WHEELS_ONE_BOARD) * vRot, -1, 1);
            rfPower = lbSpeed / MAX_RF_SPEED;
            lfPower = lbSpeed / MAX_LF_SPEED;
            rbPower = lbSpeed / MAX_RB_SPEED;
            lbPower = lbSpeed / MAX_lB_SPEED;
            lb.setPower(lbPower);
            lf.setPower(lfPower);
            rb.setPower(rbPower);
            rf.setPower(rfPower);
        }
    }
}
