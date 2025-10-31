package org.firstinspires.ftc.teamcode.own.Utils;


import static org.firstinspires.ftc.teamcode.own.Utils.Robot.gamepadOperator;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.hw;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.multipleTelemetry;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.myApp;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.params;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.soundPlaying;


import android.annotation.SuppressLint;
import android.util.Log;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.Group;

//import org.psilynx.psikit.core.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Класс для работы с OpMode</p>
 * <p>Создан для работы с {@link Scheduler}</p>
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 02:40
 */
public abstract class PhantomOpMode extends LinearOpMode {
    private static final Map<String, Object> data = new ConcurrentHashMap<>();
    public static volatile TelemetryPacket packet;
    /// Действие запускаемое в начале OpMode
    public Group actions;
    public Set<Mechanism> mechanism = new HashSet<Mechanism>();
    /// Планировщик задач
    private Scheduler scheduler;
    private VoltageSensor voltageSensor;
    Thread telemetryExecutor = new Thread(() -> {
        while (!isStopRequested()) {
            if (voltageSensor.getVoltage() <= 9.5 && !soundPlaying) {
                int soundID = myApp.getResources().getIdentifier("rubezvozvrata", "raw", myApp.getPackageName());
                PhantomOpMode.addData("playing", soundID);
                soundPlaying = true;
                SoundPlayer.getInstance().startPlaying(myApp, soundID, params, null,
                        new Runnable() {
                            public void run() {
                                soundPlaying = false;
                            }
                        });
            }
            multipleTelemetry.addData("voltage", voltageSensor.getVoltage());
            for (String s : data.keySet()) {
                multipleTelemetry.addData(s, data.get(s));
            }
            if (!isStopRequested()){
                FtcDashboard.getInstance().sendTelemetryPacket(packet);
                multipleTelemetry.update();
            }

        }
    });


    @Override
    public void runOpMode() {
        try {
            packet = new TelemetryPacket();
            voltageSensor = hardwareMap.voltageSensor.iterator().next();
            myApp = hardwareMap.appContext;
            params.loopControl = 0;
            params.waitForNonLoopingSoundsToFinish = true;
            Robot.opMode = this;
            hw = hardwareMap;
            Robot.gamepadDriver = gamepad1;
            Robot.gamepadOperator = gamepad2;
            // инициализация телеметрии
            initTelemetry();
            // инициализация настроек опмода
            customOpModeSettings();
            // инициализация Планировщик задач
            initScheduler();
            // ожидания нажатия на кнопку старт
            waitForStart();
            onStart();
            // запуск планировщика
            runScheduler();

        } catch (Exception e) {
            playDead();
            throw new RuntimeException(e);
        }


    }
    public static void playDead(){
        int soundID = myApp.getResources().getIdentifier("kolya_pridi", "raw", myApp.getPackageName());
        SoundPlayer.getInstance().startPlaying(myApp, soundID);
    }
    /// класс для указания имени, типа и группы OpMode
    public abstract void customOpModeSettings();


    private void initTelemetry() {
        multipleTelemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        multipleTelemetry.addData("Нижняя подсветка", true);
        multipleTelemetry.update();
        try {
            telemetryExecutor.start();
        } catch (Exception e) {
            playDead();
            throw new RuntimeException(e);
        }
    }

    private void initScheduler() {
        scheduler = new Scheduler.Builder()
                .setAction(actions)
                .addMechanisms(mechanism)
                .build();

        scheduler.initMechanism();
    }

    private void runScheduler() {
        if (opModeIsActive()) {
            scheduler.run();
        }
        if (isStopRequested()){
            SoundPlayer.getInstance().stopPlayingAll();
            data.clear();
        }
    }

    public void onStart() {
    }

    public static void addData(String s, Object data) {
        PhantomOpMode.data.put(s, data);
    }

}
