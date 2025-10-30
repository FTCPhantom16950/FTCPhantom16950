package org.firstinspires.ftc.teamcode.own.Actions;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;
import static org.firstinspires.ftc.teamcode.own.Utils.PhantomMath.makeLinearToCubic;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.lb;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.lf;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.rb;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.rf;


import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;


public class DriveAction extends Action {
    public DriveAction() {}
    double backRightPower, frontRightPower, backLeftPower, frontLeftPower, denominator;
    private static double x, y, rot;
    Thread thread = new Thread(() -> {
        while (opMode.opModeIsActive()) {
            x = 1.1 * gamepadDriver.left_stick_x + 1.1 * 0.6 * gamepadDriver.right_stick_x;
            y = -gamepadDriver.left_stick_y - 0.6 * gamepadDriver.right_stick_y;
            rot = gamepadDriver.left_trigger - gamepadDriver.right_trigger;
            x = makeLinearToCubic(x);
            y = makeLinearToCubic(y);
            rot = makeLinearToCubic(rot);
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
            lf.setPower(makeLinearToCubic(frontLeftPower));
            rf.setPower(makeLinearToCubic(frontRightPower));
            rb.setPower(makeLinearToCubic(backRightPower));
            lb.setPower(makeLinearToCubic(backLeftPower));
        }
    });
    Thread encoders = new Thread(()->{
        while (opMode.opModeIsActive()){

        }
    });
    @Override
    public void execute() {
        while (opMode.opModeIsActive()) {
            if (!thread.isAlive()) {
                thread.start();
            }
            denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rot), 1);
            frontLeftPower = (y + x + rot) / denominator;
            backLeftPower = (y - x + rot) / denominator;
            frontRightPower = (y - x - rot) / denominator;
            backRightPower = (y + x - rot) / denominator;
            if (!motorPower.isAlive()) {
                motorPower.start();
            }
            if (!encoders.isAlive()) {
                encoders.start();
            }
        }
    }
}
