package org.firstinspires.ftc.teamcode.own.actions.auto;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.AngleState;
import org.firstinspires.ftc.teamcode.own.utils.states.ArtifactColor;
import org.firstinspires.ftc.teamcode.own.utils.states.CapturingState;
import org.firstinspires.ftc.teamcode.own.utils.states.MotifState;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;
import org.firstinspires.ftc.teamcode.own.utils.states.UpperState;

import java.util.Map;

public class AutoMotifFirstLaunch implements Action {
    Map<RevolverStates, ArtifactColor> balls;

    @Override
    public void execute() throws InterruptedException {
        balls = Robot.INSTANCE.getRobotData("balls", Map.class);
        balls.put(RevolverStates.CENTER, ArtifactColor.GREEN);
        balls.put(RevolverStates.RIGHT, ArtifactColor.PURPLE);
        balls.put(RevolverStates.LEFT, ArtifactColor.PURPLE);
        if (!Thread.currentThread().isInterrupted()) {
            Robot.INSTANCE.addData("AutoLaunch", true);
            Robot.INSTANCE.addData("CapturingState", CapturingState.CAPTURE);
            Robot.INSTANCE.addData("AngleState", AngleState.UP);
            while (!balls.isEmpty()) {
                if (Robot.motif == MotifState.LEFT) {
                    greenThrow();
                    purpleThrow();
                    purpleThrow();
                } else if (Robot.motif == MotifState.CENTER) {
                    purpleThrow();
                    greenThrow();
                    purpleThrow();
                } else if (Robot.motif == MotifState.RIGHT) {
                    purpleThrow();
                    purpleThrow();
                    greenThrow();
                } else if (Robot.motif == MotifState.UNKNOWN){
                    purpleThrow();
                    purpleThrow();
                    greenThrow();
                }
            }
            Robot.INSTANCE.addData("AutoLaunch", false);
        }
    }
    public void purpleThrow() throws InterruptedException {
        RevolverStates state = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
        for (RevolverStates revState : balls.keySet()){
            if (balls.get(revState) == ArtifactColor.PURPLE){
                Robot.INSTANCE.addTelemetryData("state", revState);
                Robot.INSTANCE.addData("RevolverState", revState);
                sleep(2000);
                Robot.INSTANCE.addData("UpperState", UpperState.UP);
                sleep(1000);
                Robot.INSTANCE.addData("UpperState", UpperState.DOWN);
                sleep(1000);
                state = revState;
                break;
            }
        }
        balls.remove(state);
    }
    public void greenThrow() throws InterruptedException{
        RevolverStates state = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
        for (RevolverStates revState : balls.keySet()){
            if (balls.get(revState) == ArtifactColor.GREEN){
                Robot.INSTANCE.addTelemetryData("state", revState);
                Robot.INSTANCE.addData("RevolverState", revState);
                sleep(2000);
                Robot.INSTANCE.addData("UpperState", UpperState.UP);
                sleep(1000);
                Robot.INSTANCE.addData("UpperState", UpperState.DOWN);
                sleep(1000);
                state = revState;
                break;
            }
        }
        balls.remove(state);
    }
}
