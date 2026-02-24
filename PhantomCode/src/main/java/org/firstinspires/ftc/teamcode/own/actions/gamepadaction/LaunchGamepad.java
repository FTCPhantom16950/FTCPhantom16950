package org.firstinspires.ftc.teamcode.own.actions.gamepadaction;

import com.qualcomm.robotcore.hardware.Gamepad;

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

    public LaunchGamepad(Gamepad gamepad1, Gamepad gamepad2) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    @Override
    public void execute() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()){
            angleState = Robot.INSTANCE.getRobotData("AngleState", AngleState.class);
            launcherState = Robot.INSTANCE.getRobotData("LauncherState", LauncherState.class);
            rotateState = Robot.INSTANCE.getRobotData("RotateState", RotateState.class);
            upperState = Robot.INSTANCE.getRobotData("UpperState", UpperState.class);
            switch (angleState){
                case UP -> {
                    if (gamepad2.right_bumper){
                        Robot.INSTANCE.addData("AngleState", AngleState.DOWN);
                        sleep(300);
                    }
                }
                case DOWN -> {
                    if (gamepad2.right_bumper){
                        Robot.INSTANCE.addData("AngleState", AngleState.UP);
                        sleep(300);
                    }
                }
            }
            switch (launcherState){
                case LAUNCH -> {
                    if (gamepad2.b){
                        Robot.INSTANCE.addData("LauncherState", LauncherState.STOP);
                        sleep(300);
                    }
                }
                case STOP -> {
                    if (gamepad2.b){
                        Robot.INSTANCE.addData("LauncherState", LauncherState.LAUNCH);
                        sleep(300);
                    }
                }
            }
            switch (rotateState){
                case LEFT -> {
                    if (gamepad2.dpad_left){
                        Robot.INSTANCE.addData("RotateState", RotateState.STOP);
                    } else if (gamepad2.dpad_right) {
                        Robot.INSTANCE.addData("RotateState", RotateState.RIGHT);
                    }

                }
                case STOP -> {
                    if (gamepad2.dpad_left){
                        Robot.INSTANCE.addData("RotateState", RotateState.LEFT);
                    } else if (gamepad2.dpad_right) {
                        Robot.INSTANCE.addData("RotateState", RotateState.RIGHT);
                    }
                }
                case RIGHT -> {
                    if (gamepad2.dpad_left){
                        Robot.INSTANCE.addData("RotateState", RotateState.LEFT);
                    } else if (gamepad2.dpad_right) {
                        Robot.INSTANCE.addData("RotateState", RotateState.STOP);
                    }
                }
            }
            switch (upperState){
                case UP -> {
                    if (gamepad2.left_bumper) {
                        Robot.INSTANCE.addData("UpperState", UpperState.DOWN);
                        sleep(300);
                    }
                }
                case DOWN -> {
                    if (gamepad2.left_bumper) {
                        Robot.INSTANCE.addData("UpperState", UpperState.UP);
                        sleep(300);
                    }
                }
            }
        }

    }
}
