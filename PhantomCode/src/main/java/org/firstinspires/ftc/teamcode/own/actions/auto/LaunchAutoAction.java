package org.firstinspires.ftc.teamcode.own.actions.auto;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.AngleState;
import org.firstinspires.ftc.teamcode.own.utils.states.ArtifactColor;
import org.firstinspires.ftc.teamcode.own.utils.states.LauncherState;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;
import org.firstinspires.ftc.teamcode.own.utils.states.UpperState;

import java.util.Map;

public class LaunchAutoAction implements Action {
    RevolverStates revolverStates, previousState;
    RevColorSensorV3 colorSpinner;
    @Override
    public void execute() throws InterruptedException {
        colorSpinner = Robot.INSTANCE.getRobotDevice("colorSpinner", RevColorSensorV3.class);
        if (!Thread.currentThread().isInterrupted()){
            Robot.INSTANCE.addData("AngleState", AngleState.UP);
            for (int i = 0; i < 3; i++){
                revolverStates = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
                if (colorSpinner.getDistance(DistanceUnit.MM) >= 40){
                    if (revolverStates == RevolverStates.RIGHT){
                        Robot.INSTANCE.addData("RevolverState", RevolverStates.CENTER);
                        sleep(800);
                    } else if (revolverStates == RevolverStates.CENTER) {
                        Robot.INSTANCE.addData("RevolverState", RevolverStates.LEFT);
                        sleep(800);
                    } else if (revolverStates == RevolverStates.LEFT){
                        Robot.INSTANCE.addData("RevolverState", RevolverStates.RIGHT);
                        sleep(800);
                    }
                    continue;
                }

                Robot.INSTANCE.addData("AutoLaunch", true);
                Robot.INSTANCE.queueCurrent.add("pusk_razresh");
                Robot.INSTANCE.addData("UpperState", UpperState.UP);
                sleep(800);
                Robot.INSTANCE.addData("UpperState", UpperState.DOWN);
                sleep(500);
                Robot.INSTANCE.addTelemetryData("revolverState", revolverStates);
                if (revolverStates == RevolverStates.RIGHT){
                    Robot.INSTANCE.addData("RevolverState", RevolverStates.CENTER);
                    sleep(800);
                } else if (revolverStates == RevolverStates.CENTER) {
                    Robot.INSTANCE.addData("RevolverState", RevolverStates.LEFT);
                    sleep(800);
                } else if (revolverStates == RevolverStates.LEFT){
                    Robot.INSTANCE.addData("RevolverState", RevolverStates.RIGHT);
                    sleep(800);
                }
                Robot.INSTANCE.addTelemetryData("revolverState", revolverStates);
                sleep(300);
                previousState = revolverStates;
            }
            Robot.INSTANCE.addData("LauncherState", LauncherState.STOP);
        }
    }
}
