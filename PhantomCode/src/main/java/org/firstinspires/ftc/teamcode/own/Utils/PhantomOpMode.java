package org.firstinspires.ftc.teamcode.own.Utils;


import static org.firstinspires.ftc.teamcode.own.Utils.Robot.customObjects;
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


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.own.Mechanism.GyroScope;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.Group;


import java.util.HashSet;
import java.util.List;
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
    /// Действие запускаемое в начале OpMode
    public Group actions;
    public static Set<Mechanism> mechanism = new HashSet<Mechanism>();
    /// Планировщик задач
    private Scheduler scheduler;
    TelemetryPacket packet = new TelemetryPacket();
    Thread telemetryExecutor = new Thread(() -> {
        while (!isStopRequested()) {
            Robot.voltageCompenser = voltageSensor.getVoltage() / 12;
            packet = new TelemetryPacket();
            if (voltageSensor.getVoltage() <= 11 && !soundPlaying) {

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
            if (!isStopRequested()) {
                multipleTelemetry.update();
                FtcDashboard.getInstance().sendTelemetryPacket(packet);
            }

        }
    });


    @Override
    public void runOpMode() {
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule module : allHubs) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
        mechanism.clear();
        customObjects.clear();
        data.clear();
        try {
            mechanism.clear();
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
            mechanism.clear();

        } catch (Exception e) {
            data.clear();
            playDead();
            throw new RuntimeException(e);
        }
    }

    public static void playDead() {
        mechanism.clear();
        int soundID = myApp.getResources().getIdentifier("kolya_pridi", "raw", myApp.getPackageName());
        SoundPlayer.getInstance().startPlaying(myApp, soundID);
    }

    /// класс для указания имени, типа и группы OpMode
    public abstract void customOpModeSettings();


    private void initTelemetry() throws InterruptedException {
        multipleTelemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry(), PanelsTelemetry.INSTANCE.getFtcTelemetry());
        PhantomOpMode.addData("Нижняя подсветка", true);
        try {
            telemetryExecutor.start();
        } catch (Exception e) {

            playDead();
            throw new RuntimeException(e);
        }
    }

    private void initScheduler() {
        Robot.voltageCompenser = voltageSensor.getVoltage() / 12;
        scheduler = new Scheduler.Builder()
                .setAction(actions)
                .addMechanisms(mechanism)
                .build();

        scheduler.initMechanism();
        rot = imu.getRobotYawPitchRollAngles().getYaw();
    }

    private void runScheduler() throws InterruptedException {
        if (opModeIsActive()) {
            scheduler.run();
        }
        if (isStopRequested()) {
            SoundPlayer.getInstance().stopPlayingAll();
            data.clear();
            mechanism = new HashSet<>();
        }
    }

    public void onStart() {
        data.clear();
    }

    public static void addData(String s, Object data) {
        PhantomOpMode.data.put(s, data);
    }

}
