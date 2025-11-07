package org.firstinspires.ftc.teamcode.own.Utils;


import static org.firstinspires.ftc.teamcode.own.Utils.Robot.gamepadOperator;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.hw;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.imu;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.multipleTelemetry;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.myApp;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.params;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.rot;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.soundPlaying;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.voltageSensor;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.x;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.y;


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
import org.firstinspires.ftc.teamcode.own.Mechanism.CameraMechanism;
import org.firstinspires.ftc.teamcode.own.Mechanism.GyroScope;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.Group;
import org.psilynx.psikit.core.Logger;
import org.psilynx.psikit.core.rlog.RLOGServer;
import org.psilynx.psikit.core.rlog.RLOGWriter;

//import org.psilynx.psikit.core.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
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
    /// Действие запускаемое в начале OpMode
    public Group actions;
    public static volatile Set<Mechanism> mechanism = new HashSet<Mechanism>();
    /// Планировщик задач
    private Scheduler scheduler;
    TelemetryPacket packet = new TelemetryPacket();
    Thread telemetryExecutor = new Thread(() -> {

        while (!isStopRequested()) {
            packet = new TelemetryPacket();
            Logger.periodicBeforeUser();
            if (voltageSensor.getVoltage() <= 9.5 && !soundPlaying) {

                int soundID = myApp.getResources().getIdentifier("rubezvozvrata", "raw", myApp.getPackageName());
                multipleTelemetry.addData("playing", soundID);
                soundPlaying = true;
                SoundPlayer.getInstance().startPlaying(myApp, soundID, params, null,
                        new Runnable() {
                            public void run() {
                                soundPlaying = false;
                            }
                        });
            }
            packet.put("voltage", voltageSensor.getVoltage());
            packet.put("Pose heading x", x / 25.4);
            packet.put("Pose heading y", y / 25.4);
            packet.put("Pose heading", rot);
            for (String s : data.keySet()) {
                multipleTelemetry.addData(s, data.get(s));
            }
            Logger.periodicAfterUser(0, 0);
            if (!isStopRequested()) {
                multipleTelemetry.update();
                FtcDashboard.getInstance().sendTelemetryPacket(packet);
            }

        }
    });


    @Override
    public void runOpMode() {
        mechanism.clear();
        try {
            voltageSensor = hardwareMap.voltageSensor.iterator().next();
            myApp = hardwareMap.appContext;
            params.loopControl = 0;
            params.waitForNonLoopingSoundsToFinish = true;
            Robot.opMode = this;
            hw = hardwareMap;
            Robot.gamepadDriver = gamepad1;
            Robot.gamepadOperator = gamepad2;
            mechanism.add(new GyroScope());

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
            if (CameraMechanism.visionPortal != null) {
                CameraMechanism.visionPortal.close();
            }
            CameraMechanism.visionPortal = null;
            data.clear();
            playDead();
            throw new RuntimeException(e);
        }
    }

    public static void playDead() {
        int soundID = myApp.getResources().getIdentifier("kolya_pridi", "raw", myApp.getPackageName());
        SoundPlayer.getInstance().startPlaying(myApp, soundID);
    }

    /// класс для указания имени, типа и группы OpMode
    public abstract void customOpModeSettings();


    private void initTelemetry() {
        Logger.addDataReceiver(new RLOGServer());
        Logger.addDataReceiver(new RLOGWriter("storage/emulated/0/test"));
        Logger.start();
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
        rot = imu.getRobotYawPitchRollAngles().getYaw();
    }

    private void runScheduler() {
        if (opModeIsActive()) {
            scheduler.run();
        }
        if (isStopRequested()) {
            SoundPlayer.getInstance().stopPlayingAll();
            data.clear();
            mechanism = new HashSet<>();
            if (CameraMechanism.visionPortal != null) {
                CameraMechanism.visionPortal.close();
            }
            CameraMechanism.visionPortal = null;
        }
    }

    public void onStart() {
    }

    public static void addData(String s, Object data) {
        PhantomOpMode.data.put(s, data);
    }

}
