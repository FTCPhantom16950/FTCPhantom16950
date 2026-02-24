package org.firstinspires.ftc.teamcode.own.actions.stateaction;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.regulators.FullRegulator;
import org.firstinspires.ftc.teamcode.own.utils.states.CapturingState;
import org.firstinspires.ftc.teamcode.own.utils.states.OpModeStates;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Config
@Configurable
public class CaptureStateSwap implements Action {
    public static int centerDegree, leftDegree, rightDegree;

    public static double kV = 1.0 / 6000, kA = 0.06, kP = 0, kI = 0, kD = 0, df = 0.5, output = 0, motorVelocity = 0, target = 6000;
    private final FullRegulator fullRegulator = new FullRegulator(kV, kA, kP, kD, kI, df, output, target, motorVelocity);
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    List<Callable<Void>> tasks = new ArrayList<>();
    List<Future<Void>> futures = new ArrayList<>();
    DcMotorEx capture;
    CRServo revolver;
    WebcamName webcam;
    RevColorSensorV3 colorSensor;
    RevolverStates revolverStates;
    CapturingState capturingState;

    @Override
    public void execute() throws InterruptedException {
        capture = Robot.INSTANCE.getRobotDevice("capture", DcMotorEx.class);
        revolver = Robot.INSTANCE.getRobotDevice("rotator", CRServo.class);
//        colorSensor = Robot.INSTANCE.getRobotDevice("colorSensor", RevColorSensorV3.class);
//        webcam = Robot.INSTANCE.getRobotDevice("webcam", WebcamName.class);

        centerDegree = Robot.INSTANCE.getRobotData("centerDegree", Integer.class);

        rightDegree = centerDegree + 135;
        leftDegree = centerDegree - 135;

        tasks.add(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                revolverStates = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
                switch (revolverStates) {
                    case LEFT -> {
                        revolver.setPower(PhantomMath.servoCRPowerToDegrees(leftDegree, 270));
                        sleep(500);
                    }
                    case CENTER -> {
                        revolver.setPower(PhantomMath.servoCRPowerToDegrees(centerDegree, 270));
                        sleep(500);
                    }
                    case RIGHT -> {
                        revolver.setPower(PhantomMath.servoCRPowerToDegrees(rightDegree, 270));
                        sleep(500);
                    }
                }
                if (!Robot.queueCurrent.contains("baraban")) {
                    Robot.queueCurrent.add("baraban");
                }
                sleep(10);
            }
            return null;
        });
        tasks.add(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                capturingState = Robot.INSTANCE.getRobotData("CapturingState", CapturingState.class);
                switch (capturingState) {
                    case STOP -> {
                        target = 0;
                    }
                    case CAPTURE -> {
                        target = 6000;
                    }
                    case UNCAPTURE -> {
                        target = -6000;
                    }
                }
                fullRegulator.setkA(kA);
                fullRegulator.setkV(kV);
                fullRegulator.setkP(kP);
                fullRegulator.setkD(kD);
                fullRegulator.setkI(kI);
                fullRegulator.setDerivativeFilter(df);
                fullRegulator.setMotorVelocity(PhantomMath.convertToRPM(capture.getVelocity(), 28));
                fullRegulator.setTarget(target);
                output = fullRegulator.calculate();
                capture.setPower(output);
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

