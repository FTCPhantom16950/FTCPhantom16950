package org.firstinspires.ftc.teamcode.own.actions.util;

import org.firstinspires.ftc.teamcode.own.camera.ColorDetectorProcessor;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.ArtifactColor;
import org.firstinspires.ftc.teamcode.own.utils.states.CapturingState;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;

import java.util.Map;

public class CameraColorAction implements Action {
    RevolverStates revolverStates, prevState = RevolverStates.CENTER;
    Map<RevolverStates, ArtifactColor> balls;
    ColorDetectorProcessor processor;
    CapturingState capturingState;
    @Override
    public void execute() throws InterruptedException {
        capturingState = Robot.INSTANCE.getRobotData("CapturingState", CapturingState.class);
        processor = Robot.INSTANCE.getRobotData("ColorDetectorProcessor", ColorDetectorProcessor.class);
        while (!Thread.currentThread().isInterrupted()) {
            capturingState = Robot.INSTANCE.getRobotData("CapturingState", CapturingState.class);
            ArtifactColor color = processor.getCurrentColor();
            if (color != ArtifactColor.UNKNOWN) {
                if (capturingState == CapturingState.CAPTURE){
                    revolverStates = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
                    balls = Robot.INSTANCE.getRobotData("balls", Map.class);
                    balls.put(revolverStates, color);
                    Robot.INSTANCE.addData("balls", balls);
                } else if (capturingState == CapturingState.UNCAPTURE) {
                    revolverStates = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
                    balls = Robot.INSTANCE.getRobotData("balls", Map.class);
                    balls.remove(revolverStates);
                    Robot.INSTANCE.addData("balls", balls);
                }
            }
        }
    }
}
