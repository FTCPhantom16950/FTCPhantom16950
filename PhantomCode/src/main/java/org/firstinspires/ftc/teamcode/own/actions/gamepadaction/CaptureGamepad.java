package org.firstinspires.ftc.teamcode.own.actions.gamepadaction;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.CapturingState;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;
import org.firstinspires.ftc.teamcode.own.utils.states.UpperState;

public class CaptureGamepad implements Action {
    Gamepad gamepad2;
    RevolverStates revolverStates;
    CapturingState capturingState;
    UpperState upperState;
    double spinDist = 0;

    @Override
    public void execute() throws InterruptedException {
        gamepad2 = Robot.INSTANCE.getRobotData("Gamepad2", Gamepad.class);
        while (!Thread.currentThread().isInterrupted()) {
//            spinDist = Robot.INSTANCE.getRobotData("spinnerDistance", Double.class);
            upperState = Robot.INSTANCE.getRobotData("UpperState", UpperState.class);
            capturingState = Robot.INSTANCE.getRobotData("CapturingState", CapturingState.class);
            revolverStates = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
            switch (capturingState) {
                case STOP -> {
                    if (gamepad2.x) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.CAPTURE);
                        sleep(300);
                        if (!Robot.INSTANCE.queueCurrent.contains("vacum")) {
                            Robot.INSTANCE.queueCurrent.add("vacum");
                        }
                    }
                    if (gamepad2.y) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.UNCAPTURE);
                        sleep(300);
                        if (!Robot.INSTANCE.queueCurrent.contains("vacum")) {
                            Robot.INSTANCE.queueCurrent.add("vacum");
                        }
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
                        if (!Robot.INSTANCE.queueCurrent.contains("vacum")) {
                            Robot.INSTANCE.queueCurrent.add("vacum");
                        }
                    }
                }
                case UNCAPTURE -> {
                    if (gamepad2.x) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.CAPTURE);
                        sleep(300);
                        if (!Robot.INSTANCE.queueCurrent.contains("vacum")) {
                            Robot.INSTANCE.queueCurrent.add("vacum");
                        }
                    }
                    if (gamepad2.y) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.STOP);
                        sleep(300);
                    }
                }
            }
            if (upperState != UpperState.UP) {
                switch (revolverStates) {
                    case RIGHT -> {
                        switchSpindex(RevolverStates.CENTER);
                    }
                    case CENTER -> {
                        switchSpindex(RevolverStates.LEFT);
                    }
                    case LEFT -> {
                        switchSpindex(RevolverStates.RIGHT);
                    }
                }
            }
            sleep(10);
        }
    }
    public void switchSpindex(RevolverStates revolverStates) throws InterruptedException {
        if (gamepad2.a) {
            Robot.INSTANCE.addData("RevolverState", revolverStates);
            if (!Robot.INSTANCE.queueCurrent.contains("baraban")) {
                Robot.INSTANCE.queueCurrent.add("baraban");
                sleep(100);
            }
            sleep(300);
        }
        else if (spinDist <= 25){
            Robot.INSTANCE.addData("RevolverState", revolverStates);
            if (!Robot.INSTANCE.queueCurrent.contains("baraban")) {
                Robot.INSTANCE.queueCurrent.add("baraban");
                sleep(100);
            }
            sleep(300);
        }
    }
}
