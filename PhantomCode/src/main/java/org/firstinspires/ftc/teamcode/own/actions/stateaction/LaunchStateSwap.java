package org.firstinspires.ftc.teamcode.own.actions.stateaction;

import com.acmerobotics.dashboard.config.Config;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.regulators.FullRegulator;
import org.firstinspires.ftc.teamcode.own.utils.states.LauncherState;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;
import org.firstinspires.ftc.teamcode.own.utils.states.UpperState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Config
public class LaunchStateSwap implements Action {
    public static double kV = 1 / 6000.0, kA = 0.04, kP = 0.0025, kD = 0, kI = 0, derivativeFilter = 0.5, output = 0, target = 4000, motorVelocity = 0;
    private final FullRegulator fullRegulator = new FullRegulator(kV, kA, kP, kD, kI, derivativeFilter, output, target, motorVelocity);
    DcMotorEx launcher;
    LauncherState launcherState;
    RevolverStates revolverStates;
    @Override
    public void execute() throws InterruptedException {

        launcher = Robot.INSTANCE.getRobotDevice("launcher", DcMotorEx.class);

        while (!Thread.currentThread().isInterrupted()) {
            launcherState = Robot.INSTANCE.getRobotData("LauncherState", LauncherState.class);

            switch (launcherState) {
                case STOP -> {
                    target = 0;
                }
                case LAUNCH -> {
                    target = 4000;
                }
            }
            fullRegulator.setkA(kA);
            fullRegulator.setkV(kV);
            fullRegulator.setkP(kP);
            fullRegulator.setkD(kD);
            fullRegulator.setkI(kI);
            fullRegulator.setDerivativeFilter(derivativeFilter);
            fullRegulator.setMotorVelocity(PhantomMath.convertToRPM(launcher.getVelocity(), 28));
            fullRegulator.setTarget(target);
            output = fullRegulator.calculate();
            launcher.setPower(output);
            Robot.INSTANCE.addTelemetryData("velovity", PhantomMath.convertToRPM(launcher.getVelocity(), 28));
            sleep(10);
        }
    }
}
