package org.firstinspires.ftc.teamcode.own.utils;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.own.utils.states.OpModeStates;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class PhantomOpMode extends LinearOpMode {
    ExecutorService executorService = Executors.newCachedThreadPool();
    List<Callable<Void>> tasks = new ArrayList<>();
    OpModeStates states;
    List<Future<Void>> futures = new ArrayList<>();
    private Scheduler scheduler;

    @Override
    public void runOpMode() throws InterruptedException {
        List<LynxModule> lynxModuleList = hardwareMap.getAll(LynxModule.class);
        for (LynxModule module : lynxModuleList) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
        tasks.add(() -> {
            states = OpModeStates.INIT;
            Robot.INSTANCE.addData("CurrentOpMode", this);
            Robot.INSTANCE.addData("HardwareMap", this.hardwareMap);
            Robot.INSTANCE.addData("Gamepad1", this.gamepad1);
            Robot.INSTANCE.addData("Gamepad2", this.gamepad2);
            Robot.INSTANCE.addData("OpModeState", states);
            waitForStart();
            if (opModeIsActive()) {
                states = OpModeStates.ACTIVE;
                Robot.INSTANCE.addData("OpModeState", states);
            }
            if (isStopRequested()) {
                states = OpModeStates.STOP;
                Robot.INSTANCE.addData("OpModeState", states);
                Robot.INSTANCE.clearAction();
                Robot.INSTANCE.clearData();
                Robot.INSTANCE.clearMechanisms();
                Robot.INSTANCE.clearTelemetry();
                Robot.INSTANCE.clearRobotDevices();
            }
            return null;
        });
        tasks.add(() -> {
            while (opModeInInit() || opModeIsActive()) {
                for (LynxModule module : lynxModuleList) {
                    module.clearBulkCache();
                }
                for (String name : Robot.INSTANCE.getTelemetryMap().keySet()) {
                    telemetry.addData(name, Robot.INSTANCE.getTelemetryData(name));
                }
                telemetry.update();
            }
            return null;
        });
        tasks.add(() -> {
            customOpModeSettings();
            scheduler = new Scheduler.Builder()
                    .addMechanisms(Robot.INSTANCE.getMechanisms())
                    .setAction(Robot.INSTANCE.getAction())
                    .build();
            scheduler.initMechanisms();
            waitForStart();
            if (opModeIsActive()) {
                scheduler.run();
            }
            return null;
        });

        for (Callable<Void> task : tasks) {
            futures.add(executorService.submit(task));
        }

        try {
            while (!isStopRequested()) {
                for (Future<Void> future : futures) {
                    if (future.isDone()) {
                        future.get();
                    }
                }
                if (!opModeInInit() && !opModeIsActive()) {
                    break;
                }
            }
        } catch (ExecutionException e) {
            e.getCause().printStackTrace();
            throw new RuntimeException("Error in thread: " + e.getCause().getMessage(), e.getCause());
        } finally {
            executorService.shutdownNow();
        }
    }

    public abstract void customOpModeSettings() throws InterruptedException;
}
