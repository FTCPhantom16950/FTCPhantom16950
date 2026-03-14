package org.firstinspires.ftc.teamcode.own.actions.auto;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.actions.InterruptibleAction;
import org.firstinspires.ftc.teamcode.own.utils.states.ArtifactColor;
import org.firstinspires.ftc.teamcode.own.utils.states.LauncherState;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;
import org.firstinspires.ftc.teamcode.own.utils.states.UpperState;

import java.util.Map;

public class AutoLaunch extends InterruptibleAction {
    DcMotorEx launcher;
    RevolverStates revolverStates, previousState;
    Map<RevolverStates, ArtifactColor> balls;
    boolean run_once = false;
    RevColorSensorV3 colorSpinner;
    @Override
    public void run() throws InterruptedException {
        colorSpinner = Robot.INSTANCE.getRobotDevice("colorSpinner", RevColorSensorV3.class);
        balls = Robot.INSTANCE.getRobotData("balls", Map.class);
        launcher = Robot.INSTANCE.getRobotDevice("launcher", DcMotorEx.class);
        Robot.INSTANCE.addTelemetryData("velocity", PhantomMath.convertToRPM(launcher.getVelocity(), 28) );
        revolverStates = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
        if (gamepad1.left_bumper && gamepad1.options){
            isInterrupted = true;
        }
        if (PhantomMath.convertToRPM(launcher.getVelocity(), 28) > 3000) {
            while (!balls.isEmpty()){
                revolverStates = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
                balls = Robot.INSTANCE.getRobotData("balls", Map.class);
                if (colorSpinner.getDistance(DistanceUnit.MM) >= 37){
                    if (balls.containsKey(revolverStates)){
                        balls.remove(revolverStates);
                    }
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
            Robot.INSTANCE.addData("AutoLaunch", false);

        }
        Robot.INSTANCE.addTelemetryData("revolverState", revolverStates);
    }

    @Override
    public void handleInterrupt() throws InterruptedException {
        if (!run_once){
            run_once = true;
            revolverStates = previousState;
            Robot.INSTANCE.addData("UpperState", UpperState.UP);
            sleep(800);
            Robot.INSTANCE.addData("UpperState", UpperState.DOWN);
        }
        if (gamepad1.left_bumper && gamepad1.options){
            isInterrupted = true;
            run_once = false;
        }
    }
}
