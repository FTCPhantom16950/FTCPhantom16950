package org.firstinspires.ftc.teamcode.own.Actions;

import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.lb;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.lf;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.rb;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.rf;
import static org.firstinspires.ftc.teamcode.own.Utils.GamepadControl.gamepadDriver;



import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;


public class DriveAction extends Action {
    PhantomOpMode opMode;

    public DriveAction(PhantomOpMode OpMode) {
        super(OpMode);
        this.opMode = OpMode;
    }

    double backRightPower, frontRightPower, backLeftPower, frontLeftPower, denominator;
    private static double x, y, rot;
    Thread thread = new Thread(() -> {
        while (opMode.opModeIsActive()) {
            x = 1.1 * gamepadDriver.left_stick_x + 1.1 * 0.6 * gamepadDriver.right_stick_x;
            y = -gamepadDriver.left_stick_y - 0.6 * gamepadDriver.right_stick_y;
            rot = gamepadDriver.left_trigger - gamepadDriver.right_trigger;
            x = PhantomMath.makeLinearToCubic(x);
            y = PhantomMath.makeLinearToCubic(y);
            rot = PhantomMath.makeLinearToCubic(rot);
            if (-0.1 < x && x < 0.1) {
                x = 0;
            }
            if (-0.1 < y && y < 0.1) {
                x = 0;
            }
            if (-0.1 < rot && rot < 0.1) {
                x = 0;
            }
            if (gamepadDriver.right_bumper) {
                rot = -0.3;
            } else if (gamepadDriver.left_bumper) {
                rot = -0.3;
            }
        }
    });
    Thread motorPower = new Thread(() -> {
        while (opMode.opModeIsActive()) {
            lf.setPower(frontLeftPower);
            rf.setPower(frontRightPower);
            rb.setPower(backRightPower);
            lb.setPower(backLeftPower);
        }
    });

    @Override
    public void execute() {
        while (opMode.opModeIsActive()){
            if (!thread.isAlive()) {
                thread.start();
            }
            denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rot), 1);
            frontLeftPower = (y + x + rot) / denominator;
            backLeftPower = (y - x + rot) / denominator;
            frontRightPower = (y - x - rot) / denominator;
            backRightPower = (y + x - rot) / denominator;
            if (!motorPower.isAlive()){
                motorPower.start();
            }
        }
    }
}
