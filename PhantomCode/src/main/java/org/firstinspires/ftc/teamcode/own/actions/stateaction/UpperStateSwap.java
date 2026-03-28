package org.firstinspires.ftc.teamcode.own.actions.stateaction;

import com.acmerobotics.dashboard.config.Config;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.ArtifactColor;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;
import org.firstinspires.ftc.teamcode.own.utils.states.UpperState;

import java.util.Map;

@Config
public class UpperStateSwap implements Action {
    UpperState upperState;
    CRServo upper;
    public static double upperUpPower = -0.7;
    public static int upperStartDegree;
    RevolverStates revolverStates;
    Map<RevolverStates, ArtifactColor> balls;
    @Override
    public void execute() throws InterruptedException {

        upperStartDegree = Robot.INSTANCE.getRobotData("upperStartDegree", Integer.class);
        upper = Robot.INSTANCE.getRobotDevice("upper", CRServo.class);
        while (!Thread.currentThread().isInterrupted()) {
            balls = Robot.INSTANCE.getRobotData("balls", Map.class);
            revolverStates = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
            upperState = Robot.INSTANCE.getRobotData("UpperState", org.firstinspires.ftc.teamcode.own.utils.states.UpperState.class);
            switch (upperState) {
                case UP -> {
                    revolverStates = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
                    upper.setPower(upperUpPower);
                    sleep(300);
                }
                case DOWN -> {
                    upper.setPower(PhantomMath.servoCRPowerToDegrees(upperStartDegree, 270));
                    sleep(300);
                }
            }
            Robot.INSTANCE.addTelemetryData("telemetryData", upperState);
            sleep(10);
        }
    }
}
