package org.firstinspires.ftc.teamcode.own.Utils;


import static org.firstinspires.ftc.teamcode.own.Utils.Robot.gamepadOperator;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.hw;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.multipleTelemetry;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.myApp;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.params;


import android.annotation.SuppressLint;
import android.util.Log;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.Group;
import org.psilynx.psikit.core.Logger;
import org.psilynx.psikit.core.rlog.RLOGServer;
import org.psilynx.psikit.core.rlog.RLOGWriter;
//import org.psilynx.psikit.core.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>Класс для работы с OpMode</p>
 * <p>Создан для работы с {@link Scheduler}</p>
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 02:40
 */
public abstract class PhantomOpMode extends LinearOpMode {
    long currTime = System.currentTimeMillis();
    @SuppressLint("DefaultLocale") String name = String.format("Unilogs %d.rlog", currTime);
    ElapsedTime timer;
    /// Действие запускаемое в начале OpMode
    public Group actions;
    public Set<Mechanism> mechanism = new HashSet<Mechanism>();
    /// Планировщик задач
    private Scheduler scheduler;
    RLOGServer rlogServer;
    RLOGWriter rlogWriter;
    Thread telemetryExecutor = new Thread(() -> {
        timer = new ElapsedTime();
        timer.reset();
        while (opModeIsActive() || opModeInInit()){
            Logger.periodicBeforeUser();
            multipleTelemetry.update();
            Logger.periodicAfterUser(0, timer.time());
        }
    });


    @Override
    public void runOpMode() {
        rlogServer = new RLOGServer();
        rlogWriter  = new RLOGWriter("storage/emulated/0/test", name);
        myApp = hardwareMap.appContext;
        params.loopControl = 0;
        params.waitForNonLoopingSoundsToFinish = true;
        try {
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
//        Logger.end();
        } catch (Exception e) {
            int soundID = myApp.getResources().getIdentifier("otkaz_system_smotri_ekran", "raw", myApp.getPackageName());
            SoundPlayer.getInstance().startPlaying(myApp, soundID);
            throw new RuntimeException(e);
        } finally {
            Logger.end();
            rlogServer.end();
            rlogWriter.end();
        }


    }

    /// класс для указания имени, типа и группы OpMode
    public abstract void customOpModeSettings();


    private void initTelemetry() {
        multipleTelemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        rlogServer.start();
        rlogWriter.start();
        Logger.addDataReceiver(rlogServer);
        Logger.addDataReceiver(rlogWriter);
        Logger.start();
        Logger.periodicBeforeUser();
        multipleTelemetry.addData("Нижняя подсветка", true);
        multipleTelemetry.update();
        Logger.recordOutput("Нижняя подсветка", true);
        Logger.periodicAfterUser(0, 0);
        telemetryExecutor.start();
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

    }

    public void onStart() {}

}
