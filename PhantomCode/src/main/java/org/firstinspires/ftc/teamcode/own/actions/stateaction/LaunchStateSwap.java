package org.firstinspires.ftc.teamcode.own.actions.stateaction;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.regulators.FullRegulator;
import org.firstinspires.ftc.teamcode.own.utils.states.AngleState;
import org.firstinspires.ftc.teamcode.own.utils.states.LauncherState;
import org.firstinspires.ftc.teamcode.own.utils.states.OpModeStates;
import org.firstinspires.ftc.teamcode.own.utils.states.RotateState;
import org.firstinspires.ftc.teamcode.own.utils.states.UpperState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Configurable
@Config
public class LaunchStateSwap implements Action {
    public static double kV = 1 / 6000.0, kA = 0.04, kP = 0.0025, kD = 0, kI = 0, derivativeFilter = 0.5, output = 0, target = 4000, motorVelocity = 0;
    public static int angleStartDegree, upperStartDegree, angleUpDegree = 270;
    public static double upperUpPower = -0.7;
    private final FullRegulator fullRegulator = new FullRegulator(kV, kA, kP, kD, kI, derivativeFilter, output, target, motorVelocity);
    DcMotorEx rotate, launcher;
    CRServo angle, upper;
    AngleState angleState;
    LauncherState launcherState;
    RotateState rotateState;
    UpperState upperState;
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    List<Callable<Void>> tasks = new ArrayList<>();
    List<Future<Void>> futures = new ArrayList<>();

    @Override
    public void execute() throws InterruptedException {
        angleStartDegree = Robot.INSTANCE.getRobotData("angleStartDegree", Integer.class);
        upperStartDegree = Robot.INSTANCE.getRobotData("upperStartDegree", Integer.class);
        rotate = Robot.INSTANCE.getRobotDevice("rotate", DcMotorEx.class);
        launcher = Robot.INSTANCE.getRobotDevice("launcher", DcMotorEx.class);
        angle = Robot.INSTANCE.getRobotDevice("angle", CRServo.class);
        upper = Robot.INSTANCE.getRobotDevice("upper", CRServo.class);

        tasks.add(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                angleState = Robot.INSTANCE.getRobotData("AngleState", AngleState.class);
                switch (angleState) {
                    case UP -> {
                        angle.setPower(PhantomMath.servoCRPowerToDegrees(angleUpDegree, 270));
                        sleep(300);
                        if (!Robot.queueCurrent.contains("predel_ugl_dlin")) {
                            Robot.queueCurrent.add("predel_ugl_dlin");
                        }
                    }
                    case DOWN -> {
                        angle.setPower(PhantomMath.servoCRPowerToDegrees(angleStartDegree, 270));
                        sleep(300);
                    }
                }
                sleep(10);
            }
            return null;
        });

        tasks.add(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                upperState = Robot.INSTANCE.getRobotData("UpperState", UpperState.class);
                switch (upperState) {
                    case UP -> {
                        upper.setPower(upperUpPower);
                        sleep(300);
                        if (!Robot.queueCurrent.contains("pusk_raketi")){
                            Robot.queueCurrent.add("pusk_raketi");
                        }
                    }
                    case DOWN -> {
                        upper.setPower(PhantomMath.servoCRPowerToDegrees(upperStartDegree, 270));
                        sleep(300);
                    }
                }
                sleep(10);
            }
            return null;
        });

        tasks.add(() -> {
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
                if (PhantomMath.convertToRPM(launcher.getVelocity(), 28) > 3500 && !Robot.queueCurrent.contains("pusk_raketi")){
                    Robot.queueCurrent.add("pusk_razresh");
                }
                sleep(10);
            }
            return null;
        });

        tasks.add(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                rotateState = Robot.INSTANCE.getRobotData("RotateState", RotateState.class);
                switch (rotateState) {
                    case LEFT -> {
                        rotate.setPower(1);
                        sleep(300);
                    }
                    case STOP -> {
                        rotate.setPower(0);
                        sleep(300);
                    }
                    case RIGHT -> {
                        rotate.setPower(-1);
                        sleep(300);
                    }
                }
                sleep(10);
            }
            return null;
        });


        if (Robot.INSTANCE.getRobotData("OpModeState", OpModeStates.class) == OpModeStates.ACTIVE) {
            for (Callable<Void> task : tasks) {
                futures.add(executorService.submit(task));
            }
            try {
                while (Robot.INSTANCE.getRobotData("OpModeState", OpModeStates.class) == OpModeStates.ACTIVE) {
                    for (Future<Void> future : futures) {
                        if (future.isDone()) {
                            future.get();
                        }
                    }
                    if (Robot.INSTANCE.getRobotData("OpModeState", OpModeStates.class) == OpModeStates.STOP) {
                        break;
                    }
                    sleep(10);
                }
            } catch (ExecutionException e) {
                e.getCause().printStackTrace();
                throw new RuntimeException("Error in thread: " + e.getCause().getMessage(), e.getCause());
            } finally {
                executorService.shutdownNow();
            }
        }
    }
}
