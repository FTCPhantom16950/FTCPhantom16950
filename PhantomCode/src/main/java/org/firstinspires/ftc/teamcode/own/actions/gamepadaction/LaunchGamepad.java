package org.firstinspires.ftc.teamcode.own.actions.gamepadaction;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.AngleState;
import org.firstinspires.ftc.teamcode.own.utils.states.LauncherState;
import org.firstinspires.ftc.teamcode.own.utils.states.RotateState;
import org.firstinspires.ftc.teamcode.own.utils.states.UpperState;

public class LaunchGamepad implements Action {
    Gamepad gamepad1, gamepad2;
    AngleState angleState;
    LauncherState launcherState;
    RotateState rotateState;
    UpperState upperState;


    @Override
    public void execute() throws InterruptedException {
        gamepad1 = Robot.INSTANCE.getRobotData("Gamepad1", Gamepad.class);
        gamepad2 = Robot.INSTANCE.getRobotData("Gamepad2", Gamepad.class);
        while (!Thread.currentThread().isInterrupted()) {
            angleState = Robot.INSTANCE.getRobotData("AngleState", AngleState.class);
            launcherState = Robot.INSTANCE.getRobotData("LauncherState", LauncherState.class);
            rotateState = Robot.INSTANCE.getRobotData("RotateState", RotateState.class);
            upperState = Robot.INSTANCE.getRobotData("UpperState", UpperState.class);
            switch (angleState) {
                case UP -> {
                    if (gamepad1.right_bumper) {
                        Robot.INSTANCE.addData("AngleState", AngleState.DOWN);
                        sleep(300);
                        if (!Robot.INSTANCE.queueCurrent.contains("predel_ugl_dlin")) {
                            Robot.INSTANCE.queueCurrent.add("predel_ugl_dlin");
                        }
                    }
                }
                case DOWN -> {
                    if (gamepad1.right_bumper) {
                        Robot.INSTANCE.addData("AngleState", AngleState.UP);
                        sleep(300);
                    }
                }
            }
            switch (launcherState) {
                case LAUNCH -> {
                    if (gamepad1.b) {
                        Robot.INSTANCE.addData("LauncherState", LauncherState.STOP);
                        sleep(300);
                    }
                }
                case STOP -> {
                    if (gamepad1.b) {
                        Robot.INSTANCE.addData("LauncherState", LauncherState.LAUNCH);
                        sleep(300);
                    }
                }
            }
            if (gamepad1.dpad_left) {
                Robot.INSTANCE.addData("RotateState", RotateState.LEFT);
            } else if (gamepad1.dpad_right) {
                Robot.INSTANCE.addData("RotateState", RotateState.RIGHT);
            } else {
                Robot.INSTANCE.addData("RotateState", RotateState.STOP);
            }
            if (gamepad1.left_bumper) {
                if (upperState == UpperState.UP) {
                    Robot.INSTANCE.addData("UpperState", UpperState.DOWN);
                } else if (upperState == UpperState.DOWN) {
                    Robot.INSTANCE.addData("UpperState", UpperState.UP);
                }
                sleep(300);
            }
        }

    }
}
