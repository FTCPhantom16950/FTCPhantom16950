package org.firstinspires.ftc.teamcode.own.actions.gamepadaction;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.ArtifactColor;
import org.firstinspires.ftc.teamcode.own.utils.states.CapturingState;
import org.firstinspires.ftc.teamcode.own.utils.states.LauncherState;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;
import org.firstinspires.ftc.teamcode.own.utils.states.UpperState;

import java.util.Map;

public class CaptureGamepad implements Action {
    Gamepad gamepad1;
    RevolverStates revolverStates;
    CapturingState capturingState;
    UpperState upperState;
    double spinDist = 0;
    RevColorSensorV3 colorSpinner;
    Map<RevolverStates, ArtifactColor> balls;

    @Override
    public void execute() throws InterruptedException {
        colorSpinner = Robot.INSTANCE.getRobotDevice("colorSpinner", RevColorSensorV3.class);
        gamepad1 = Robot.INSTANCE.getRobotData("Gamepad1", Gamepad.class);
        while (!Thread.currentThread().isInterrupted()) {
            balls = Robot.INSTANCE.getRobotData("balls", Map.class);
            spinDist = colorSpinner.getDistance(DistanceUnit.MM);
            upperState = Robot.INSTANCE.getRobotData("UpperState", UpperState.class);
            capturingState = Robot.INSTANCE.getRobotData("CapturingState", CapturingState.class);
            revolverStates = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);


            switch (capturingState) {
                case STOP -> {
                    if (gamepad1.x) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.CAPTURE);
                        sleep(300);
                        if (!Robot.INSTANCE.queueCurrent.contains("vacum")) {
                            Robot.INSTANCE.queueCurrent.add("vacum");
                        }
                    }
                    if (gamepad1.y) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.UNCAPTURE);
                        sleep(300);
                        if (!Robot.INSTANCE.queueCurrent.contains("vacum")) {
                            Robot.INSTANCE.queueCurrent.add("vacum");
                        }
                    }
                }
                case CAPTURE -> {
                    if (gamepad1.x) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.STOP);
                        sleep(300);
                    }
                    if (gamepad1.y) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.UNCAPTURE);
                        sleep(300);
                        if (!Robot.INSTANCE.queueCurrent.contains("vacum")) {
                            Robot.INSTANCE.queueCurrent.add("vacum");
                        }
                    }
                }
                case UNCAPTURE -> {
                    if (gamepad1.x) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.CAPTURE);
                        sleep(300);
                        if (!Robot.INSTANCE.queueCurrent.contains("vacum")) {
                            Robot.INSTANCE.queueCurrent.add("vacum");
                        }
                    }
                    if (gamepad1.y) {
                        Robot.INSTANCE.addData("CapturingState", CapturingState.STOP);
                        sleep(300);
                    }
                }
            }

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
            Robot.INSTANCE.addTelemetryData("spinDist", spinDist);
            Robot.INSTANCE.addTelemetryData("balls.size()", balls.size());
            sleep(10);

            Robot.INSTANCE.addTelemetryData("AutoLaunch", Robot.INSTANCE.getRobotData("AutoLaunch", Boolean.class));
        }
    }

    public void switchSpindex(RevolverStates revolverStates) throws InterruptedException {
        CapturingState prev = Robot.INSTANCE.getRobotData("CapturingState", CapturingState.class);
        if (upperState != UpperState.UP) {
            if (gamepad1.a) {
                Robot.INSTANCE.addData("LauncherState", LauncherState.STOP);
                Robot.INSTANCE.addData("RevolverState", revolverStates);
                if (!Robot.INSTANCE.queueCurrent.contains("baraban")) {
                    Robot.INSTANCE.queueCurrent.add("baraban");
                    sleep(100);
                }
                balls.put(revolverStates, ArtifactColor.UNKNOWN);
                Robot.INSTANCE.addData("CapturingState", CapturingState.STOP);
                sleep(500);
                Robot.INSTANCE.addData("CapturingState", prev);
                sleep(300);
            }
            if (balls.size() < 3 && !Robot.INSTANCE.getRobotData("AutoLaunch", Boolean.class)) {
                if (spinDist <= 36) {
                    Robot.INSTANCE.addData("CapturingState", CapturingState.UNCAPTURE);
                    sleep(100);
                    Robot.INSTANCE.addData("CapturingState", CapturingState.STOP);
                    Robot.INSTANCE.addData("RevolverState", revolverStates);
                    if (!Robot.INSTANCE.queueCurrent.contains("baraban")) {
                        Robot.INSTANCE.queueCurrent.add("baraban");
                        sleep(100);
                    }
                    balls.put(revolverStates, ArtifactColor.UNKNOWN);
                    sleep(300);

                    sleep(500);
                    Robot.INSTANCE.addData("CapturingState", prev);
                }
            }
        }
    }
}
