package org.firstinspires.ftc.teamcode.own.actions.gamepadaction;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.CapturingState;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;

public class CaptureGamepad implements Action {
    Gamepad gamepad2;
    RevolverStates revolverStates;
    CapturingState capturingState;

    @Override
    public void execute() throws InterruptedException {
        gamepad2 = Robot.INSTANCE.getRobotData("Gamepad2", Gamepad.class);
        while (!Thread.currentThread().isInterrupted()) {
            capturingState = Robot.INSTANCE.getRobotData("CapturingState", CapturingState.class);
            revolverStates = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
            switch (capturingState) {
                case STOP -> {
                    if (gamepad2.x) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.CAPTURE);
                        sleep(300);
                        if (!Robot.INSTANCE.queueCurrent.contains("vacum")){
                            Robot.INSTANCE.queueCurrent.add("vacum");
                        }
                    }
                    if (gamepad2.y) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.UNCAPTURE);
                        sleep(300);
                        if (!Robot.INSTANCE.queueCurrent.contains("vacum")){
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
                        if (!Robot.INSTANCE.queueCurrent.contains("vacum")){
                            Robot.INSTANCE.queueCurrent.add("vacum");
                        }
                    }
                }
                case UNCAPTURE -> {
                    if (gamepad2.x) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.CAPTURE);
                        sleep(300);
                        if (!Robot.INSTANCE.queueCurrent.contains("vacum")){
                            Robot.INSTANCE.queueCurrent.add("vacum");
                        }
                    }
                    if (gamepad2.y) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.STOP);
                        sleep(300);
                    }
                }
            }
            switch (revolverStates) {
                case RIGHT -> {
                    if (gamepad2.a) {
                        Robot.INSTANCE.addData("RevolverState", RevolverStates.CENTER);
                        if (!Robot.INSTANCE.queueCurrent.contains("baraban")) {
                            Robot.INSTANCE.queueCurrent.add("baraban");
                            sleep(100);
                        }
                        sleep(300);
                    }
                }
                case CENTER -> {
                    if (gamepad2.a) {
                        Robot.INSTANCE.addData("RevolverState", RevolverStates.LEFT);
                        if (!Robot.INSTANCE.queueCurrent.contains("baraban")) {
                            Robot.INSTANCE.queueCurrent.add("baraban");
                            sleep(100);
                        }
                        sleep(300);
                    }
                }
                case LEFT -> {
                    if (gamepad2.a) {
                        Robot.INSTANCE.addData("RevolverState", RevolverStates.RIGHT);
                        if (!Robot.INSTANCE.queueCurrent.contains("baraban")) {
                            Robot.INSTANCE.queueCurrent.add("baraban");
                            sleep(100);
                        }
                        sleep(300);
                    }
                }
            }
            sleep(10);
//            Robot.INSTANCE.addTelemetryData("mem1", "mem1");
        }
    }
}
