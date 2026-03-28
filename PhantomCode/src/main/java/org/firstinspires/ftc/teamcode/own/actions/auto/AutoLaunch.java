package org.firstinspires.ftc.teamcode.own.actions.auto;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.actions.InterruptibleAction;
import org.firstinspires.ftc.teamcode.own.utils.states.ArtifactColor;
import org.firstinspires.ftc.teamcode.own.utils.states.CapturingState;
import org.firstinspires.ftc.teamcode.own.utils.states.LauncherState;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;
import org.firstinspires.ftc.teamcode.own.utils.states.UpperState;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AutoLaunch extends InterruptibleAction {

    RevolverStates revolverStates, previousState;
    Map<RevolverStates, ArtifactColor> balls;
    boolean run_once = false;
    RevColorSensorV3 colorSpinner;
    DcMotorEx rotate;
    @Override
    public void run() throws InterruptedException {


        rotate = Robot.INSTANCE.getRobotDevice("rotate", DcMotorEx.class);
        colorSpinner = Robot.INSTANCE.getRobotDevice("colorSpinner", RevColorSensorV3.class);
        balls = Robot.INSTANCE.getRobotData("balls", Map.class);
        double velocity = Robot.INSTANCE.getRobotData("velocityShooter", Double.class);
        while (velocity <= 3000){
            velocity = Robot.INSTANCE.getRobotData("velocityShooter", Double.class);
            Robot.INSTANCE.addTelemetryData("data1", velocity);
        }
        if (velocity >= 3000) {
                Robot.INSTANCE.addData("AutoLaunch", true);
                sleep(500);
                Robot.INSTANCE.addTelemetryData("balls", balls.toString());
                balls = Robot.INSTANCE.getRobotData("balls", Map.class);
                for (RevolverStates state : balls.keySet()){
                    Robot.INSTANCE.addData("RevolverState", state);
                    sleep(1000);
                    Robot.INSTANCE.queueCurrent.add("pusk_razresh");
                    Robot.INSTANCE.addData("UpperState", UpperState.UP);
                    sleep(1000);
                    Robot.INSTANCE.addData("UpperState", UpperState.DOWN);
                    sleep(500);
                }
                balls.clear();
                Robot.INSTANCE.addData("AutoLaunch", false);
            }



    }

    @Override
    public void handleInterrupt() throws InterruptedException {
        if (!run_once){
            run_once = true;
            revolverStates = previousState;
            Robot.INSTANCE.addData("UpperState", UpperState.UP);
            sleep(800);
            Robot.INSTANCE.addData("UpperState", UpperState.DOWN);
            Robot.INSTANCE.addData("AutoLaunch", false);
        }
        if (gamepad1.left_bumper && gamepad1.options){
            isInterrupted = true;
            run_once = false;
        }
    }
}
