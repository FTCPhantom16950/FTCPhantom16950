package org.firstinspires.ftc.teamcode.own.actions.util;

import org.firstinspires.ftc.teamcode.own.camera.ColorDetectorProcessor;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.ArtifactColor;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;

import kotlinx.coroutines.channels.ActorKt;

public class CameraAction implements Action {
    RevolverStates revolverStates;
    ColorDetectorProcessor processor;
    ArtifactColor currentColor = ArtifactColor.UNKNOWN;
    @Override
    public void execute() throws InterruptedException {
        processor = Robot.INSTANCE.getRobotData("ColorDetectorProcessor", ColorDetectorProcessor.class);
        while (!Thread.currentThread().isInterrupted()){
            revolverStates = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
            currentColor = processor.getCurrentColor();
            Robot.INSTANCE.addTelemetryData("color", currentColor);
            if (currentColor != ArtifactColor.UNKNOWN){
            }
        }
    }
}
