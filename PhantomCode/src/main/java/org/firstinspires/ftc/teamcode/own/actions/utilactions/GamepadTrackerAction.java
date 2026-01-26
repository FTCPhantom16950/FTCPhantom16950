package org.firstinspires.ftc.teamcode.own.actions.utilactions;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class GamepadTrackerAction implements Action {
    @Override
    public void execute() throws InterruptedException {
        Gamepad gamepad1 = Robot.INSTANCE.gamepadDriver, gamepad2 = Robot.INSTANCE.gamepadOperator;
        while (Robot.INSTANCE.opMode.opModeIsActive()){
            Robot.addData("Gx", PhantomMath.makeLinearToCubic(gamepad1.left_stick_x + gamepad1.right_stick_x * 0.8));
            Robot.addData("Gy", PhantomMath.makeLinearToCubic(-gamepad1.left_stick_y - gamepad1.right_stick_y * 0.8));
            Robot.addData("Gr", PhantomMath.makeLinearToCubic(gamepad1.right_trigger - gamepad1.left_trigger));
        }
    }
}
