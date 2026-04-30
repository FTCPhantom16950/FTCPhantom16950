package org.firstinspires.ftc.teamcode.own.actions.auto;

import com.qualcomm.hardware.rev.RevColorSensorV3;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.ArtifactColor;
import org.firstinspires.ftc.teamcode.own.utils.states.CapturingState;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;

import java.util.Map;

public class CaptureElement implements Action {
    Map<RevolverStates, ArtifactColor> balls;
    RevColorSensorV3 colorSpinner;
    RevolverStates revolverStates;
    double spinDist = 0;
    @Override
    public void execute() throws InterruptedException {
        colorSpinner = Robot.INSTANCE.getRobotDevice("colorSpinner", RevColorSensorV3.class);
        while (!Thread.currentThread().isInterrupted()){
            balls = Robot.INSTANCE.getRobotData("balls", Map.class);
            spinDist = colorSpinner.getDistance(DistanceUnit.MM);
            revolverStates = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
            Robot.INSTANCE.addTelemetryData("balls.size", balls.size());
            Robot.INSTANCE.addTelemetryData("spinDist",spinDist);
            if (balls.size() < 3 && !Robot.INSTANCE.getRobotData("AutoLaunch", Boolean.class)) {
                if (spinDist <= 27) {
                    Robot.INSTANCE.addData("CapturingState", CapturingState.SLOW);
                    Robot.INSTANCE.addData("RevolverState", revolverStates);
                    if (!Robot.INSTANCE.queueCurrent.contains("baraban")) {
                        Robot.INSTANCE.queueCurrent.add("baraban");
                        sleep(100);
                    }
                    sleep(300);
                    if (!balls.containsKey(revolverStates)){
                        balls.put(revolverStates, ArtifactColor.UNKNOWN);
                    }
                    sleep(500);
                    Robot.INSTANCE.addData("CapturingState", CapturingState.CAPTURE);
                }
            }
        }
    }
}
