package org.firstinspires.ftc.teamcode.own.actions.gamepadaction;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.CapturingState;
import org.firstinspires.ftc.teamcode.own.utils.states.OpModeStates;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;

public class CaptureGamepad implements Action {
    Gamepad gamepad1, gamepad2;
    RevolverStates state;
    CapturingState capturingState;



    @Override
    public void execute() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            gamepad2 = Robot.INSTANCE.getRobotData("Gamepad2", Gamepad.class);
            capturingState = Robot.INSTANCE.getRobotData("CapturingState", CapturingState.class);
            state = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
            switch (capturingState) {
                case STOP -> {
                    if (gamepad2.x) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.CAPTURE);
                        sleep(300);
                    }
                    if (gamepad2.y) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.UNCAPTURE);
                        sleep(300);
                    }
                }
                case CAPTURE -> {
                    if (gamepad2.x) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.STOP);
                        sleep(300);
                    }
                    if (gamepad2.y) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.UNCAPTURE);
                        sleep(300);
                    }
                }
                case UNCAPTURE -> {
                    if (gamepad2.x) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.CAPTURE);
                        sleep(300);
                    }
                    if (gamepad2.y) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.STOP);
                        sleep(300);
                    }
                }
            }
            switch (state) {
                case RIGHT -> {
                    if (gamepad2.a) {
                        Robot.INSTANCE.addData("RevolverState", RevolverStates.CENTER);
                        sleep(300);
                    }
                }
                case CENTER -> {
                    if (gamepad2.a) {
                        Robot.INSTANCE.addData("RevolverState", RevolverStates.LEFT);
                        sleep(300);
                    }
                }
                case LEFT -> {
                    if (gamepad2.a) {
                        Robot.INSTANCE.addData("RevolverState", RevolverStates.RIGHT);
                        sleep(300);
                    }
                }
            }
            sleep(10);
        }
    }
}
