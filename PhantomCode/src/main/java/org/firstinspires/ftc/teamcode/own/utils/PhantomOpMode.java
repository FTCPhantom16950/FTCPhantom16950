package org.firstinspires.ftc.teamcode.own.utils;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.teamcode.own.utils.states.OpModeStates;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class PhantomOpMode extends LinearOpMode {
    ExecutorService executorService = Executors.newFixedThreadPool(4);
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
            Robot.INSTANCE.addData("OpModeState", states);
            Robot.INSTANCE.addData("HardwareMap", this.hardwareMap);
            Robot.INSTANCE.addData("Gamepad1", this.gamepad1);
            Robot.INSTANCE.addData("Gamepad2", this.gamepad2);
            waitForStart();
            if (opModeIsActive()) {
                states = OpModeStates.ACTIVE;
                Robot.INSTANCE.addData("OpModeState", states);
            }
            if (isStopRequested()) {
                states = OpModeStates.STOP;
                Robot.INSTANCE.addData("OpModeState", states);
            }
            sleep(100);

            return null;
        });
        tasks.add(() -> {
            while (opModeInInit() || opModeIsActive()) {
                for (LynxModule module : lynxModuleList) {
                    module.clearBulkCache();
                }
                sleep(10);
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
        tasks.add(() -> {
            String[] soundNames = {"kolya_pridi", "otkaz_system_smotri_ekran", "otkazavtopilota",
                    "pozar_dvigat", "predel_ugl_dlin", "predelataki", "pusk_raketi", "pusk_razresh",
                    "rubezvozvrata", "skorost_predel", "vipusti_shasi", "baraban"};
            if (Robot.INSTANCE.sounds.isEmpty()) {
                for (String soundName : soundNames) {
                    Robot.INSTANCE.sounds.put(soundName, hardwareMap.appContext.getResources()
                            .getIdentifier(soundName, "raw", hardwareMap.appContext.getPackageName()));
                }
            }
            AtomicBoolean soundPlaying = new AtomicBoolean(false);
            while (!isStopRequested()) {
                String nextSound = Robot.INSTANCE.queueCurrent.poll();
                if (!soundPlaying.get() && nextSound != null) {
                    SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, Robot.INSTANCE.sounds.get(nextSound),
                            new SoundPlayer.PlaySoundParams(), null, () -> soundPlaying.set(true));
                }
                Thread.sleep(10);
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
                Thread.sleep(10);
            }
        } catch (ExecutionException e) {
            if (Robot.INSTANCE.sounds.get("kolya_pridi") != null) {
                SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, Robot.INSTANCE.sounds.get("kolya_pridi"));
            }
            e.getCause().printStackTrace();
            RobotLog.ee("PhantomOpMode", e.getCause(), "Внимание! Фоновый поток упал с ошибкой!");
            telemetry.addData("!!! CRITICAL ERROR !!!", e.getCause().getMessage());
            telemetry.update();
            requestOpModeStop();
            throw new RuntimeException("Error in thread: " + e.getCause().getMessage(), e.getCause());
        } finally {
            executorService.shutdownNow();
            Robot.INSTANCE.clearAction();
            Robot.INSTANCE.clearData();
            Robot.INSTANCE.clearMechanisms();
            Robot.INSTANCE.clearTelemetry();
            Robot.INSTANCE.clearRobotDevices();
            Robot.INSTANCE.queueCurrent.clear();
        }
    }

    public abstract void customOpModeSettings() throws InterruptedException;

    private void playStop() {

    }
}
