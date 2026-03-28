package org.firstinspires.ftc.teamcode.own.actions.auto;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.ArtifactColor;
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
            while (!balls.isEmpty()) {
                if (Robot.motif == MotifState.LEFT) {

                } else if (Robot.motif == MotifState.CENTER) {

                } else if (Robot.motif == MotifState.RIGHT) {
                    RevolverStates state = RevolverStates.CENTER;
                    for (RevolverStates revState : balls.keySet()){
                        if (balls.get(revState) == ArtifactColor.PURPLE){
                            Robot.INSTANCE.addTelemetryData("state", revState);
                            Robot.INSTANCE.addData("RevolverState", revState);
                            sleep(1000);
                            Robot.INSTANCE.addData("UpperState", UpperState.UP);
                            sleep(1000);
                            Robot.INSTANCE.addData("UpperState", UpperState.DOWN);
                            sleep(500);
                            state = revState;
                            break;
                        }
                    }
                    balls.remove(state);
                    for (RevolverStates revState : balls.keySet()){
                        if (balls.get(revState) == ArtifactColor.PURPLE){
                            Robot.INSTANCE.addTelemetryData("state", revState);
                            Robot.INSTANCE.addData("RevolverState", revState);
                            sleep(1000);
                            Robot.INSTANCE.addData("UpperState", UpperState.UP);
                            sleep(1000);
                            Robot.INSTANCE.addData("UpperState", UpperState.DOWN);
                            sleep(500);
                            state = revState;
                            break;
                        }
                    }
                    balls.remove(state);
                    for (RevolverStates revState : balls.keySet()){
                        if (balls.get(revState) == ArtifactColor.GREEN){
                            Robot.INSTANCE.addTelemetryData("state", revState);
                            Robot.INSTANCE.addData("RevolverState", revState);
                            sleep(1000);
                            Robot.INSTANCE.addData("UpperState", UpperState.UP);
                            sleep(1000);
                            Robot.INSTANCE.addData("UpperState", UpperState.DOWN);
                            sleep(500);
                            state = revState;
                            break;
                        }
                    }
                    balls.remove(state);
                }
            }
        }
    }
}
