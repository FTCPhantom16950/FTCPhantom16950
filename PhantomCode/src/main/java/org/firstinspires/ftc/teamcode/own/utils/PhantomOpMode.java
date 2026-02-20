package org.firstinspires.ftc.teamcode.own.utils;


import static org.firstinspires.ftc.teamcode.own.utils.Robot.INSTANCE;
import static org.firstinspires.ftc.teamcode.own.utils.Robot.data;
import static org.firstinspires.ftc.teamcode.own.utils.Robot.telemetryData;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.bylazar.gamepad.GamepadManager;
import com.bylazar.gamepad.PanelsGamepad;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.mechanism.util.GyroScopeMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.util.VolatgeMechanism;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * <p>Класс для работы с OpMode</p>
 * <p>Создан для работы с {@link Scheduler}</p>
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 02:40
 */
public abstract class PhantomOpMode extends LinearOpMode {
    GamepadManager g1, g2;
    public static Set<Mechanism> mechanism = new CopyOnWriteArraySet<Mechanism>();
    private final Thread hardwareLoop = new Thread(() -> {
        while (!isStopRequested() && !Thread.currentThread().isInterrupted()) {
            try {
                Robot.INSTANCE.clearBulkCache();
                INSTANCE.gamepadDriver = g1.asCombinedFTCGamepad(gamepad1);
                INSTANCE.gamepadOperator = g2.asCombinedFTCGamepad(gamepad2);
                for (Mechanism m : PhantomOpMode.mechanism) {
                    try {
                        if (opModeIsActive() && m != null){
                            m.read();
                        }
                    } catch (Exception e) {
                        playDead();
                        throw new RuntimeException(e);
                    }
                }
            } catch (Exception e) {
                playDead();
                throw new RuntimeException(e);
            }
        }
    });
    /// Действие запускаемое в начале OpMode
    public Action actions;
    private ElapsedTime time;
    /// Планировщик задач
    private org.firstinspires.ftc.teamcode.own.utils.Scheduler scheduler;
    private TelemetryPacket packet = new TelemetryPacket();
    private Thread telemetryExecutor = new Thread(() -> {
        time = new ElapsedTime();
        while (!isStopRequested()) {
            Robot.INSTANCE.voltageCompenser = INSTANCE.voltage / 12.0;
            packet = new TelemetryPacket();

            if (INSTANCE.soundPlaying){
                INSTANCE.nearlyPlayed = true;
                time.reset();
            }
            if (time.seconds() > 15 && INSTANCE.nearlyPlayed) {
                INSTANCE.nearlyPlayed = false;
            }
            packet.put("voltage", INSTANCE.voltage);
            packet.put("Pose heading x", INSTANCE.x / 25.4);
            packet.put("Pose heading y", INSTANCE.y / 25.4);
            packet.put("Pose heading", INSTANCE.rot);
            if (opModeIsActive()) {
                for (String s : telemetryData.keySet()) {
                    INSTANCE.multipleTelemetry.addData(s, telemetryData.get(s));
                }
                INSTANCE.multipleTelemetry.update();
                FtcDashboard.getInstance().sendTelemetryPacket(packet);
            }

        }
    });

    public static void playDead() {
        int soundID = INSTANCE.getApp().getResources().getIdentifier("kolya_pridi", "raw", INSTANCE.getApp().getPackageName());
        SoundPlayer.getInstance().startPlaying(INSTANCE.getApp(), soundID);
    }


    @Override
    public void runOpMode() {

        INSTANCE.allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule module : INSTANCE.allHubs) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
        telemetry.setAutoClear(true);
        mechanism.clear();
        INSTANCE.customObjects.clear();
        telemetryData.clear();
        data.clear();
        try {
            INSTANCE.balls.clear();
            INSTANCE.voltageSensor = hardwareMap.voltageSensor.iterator().next();
            INSTANCE.myAppRef = new WeakReference<>(hardwareMap.appContext);
            INSTANCE.params.loopControl = 0;
            INSTANCE.params.waitForNonLoopingSoundsToFinish = true;
            Robot.INSTANCE.opMode = this;
            INSTANCE.hw = this.hardwareMap;
            g1 = PanelsGamepad.INSTANCE.getFirstManager();
            g2 = PanelsGamepad.INSTANCE.getSecondManager();
            Robot.INSTANCE.gamepadDriver = gamepad1;
            Robot.INSTANCE.gamepadOperator = gamepad2;
            mechanism.add(new GyroScopeMechanism());
            mechanism.add(new VolatgeMechanism());
            // инициализация настроек опмода
            customOpModeSettings();
            // инициализация Планировщик задач
            initScheduler();
            // инициализация телеметрии
            initTelemetry();
            // ожидания нажатия на кнопку старт
            waitForStart();
            hardwareLoop.start();
            onStart();
            // запуск планировщика
            runScheduler();
            mechanism.clear();
            hardwareLoop.interrupt();


        } catch (Exception e) {
            playDead();
            throw new RuntimeException(e);
        }
    }

    /// класс для указания имени, типа и группы OpMode
    public abstract void customOpModeSettings() throws InterruptedException;

    private void initTelemetry() throws InterruptedException {
        INSTANCE.multipleTelemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry(), PanelsTelemetry.INSTANCE.getFtcTelemetry());
        Robot.INSTANCE.addData("Нижняя подсветка", true);

        try {
            telemetryExecutor.start();
        } catch (Exception e) {
            playDead();
            throw new RuntimeException(e);
        }
    }

    private void initScheduler() throws InterruptedException {
        Robot.INSTANCE.voltageCompenser = INSTANCE.voltageSensor.getVoltage() / 12;
        scheduler = new Scheduler.Builder()
                .addMechanisms(mechanism)
                .build();
        scheduler.initMechanism();
        scheduler.addAction(actions);
        INSTANCE.rot = INSTANCE.imu.getRobotYawPitchRollAngles().getYaw();
    }

    private void runScheduler() throws InterruptedException {
        if (opModeIsActive()) {
            scheduler.run();
        }
            SoundPlayer.getInstance().stopPlayingAll();
            data.clear();
            telemetryData.clear();
            mechanism = new HashSet<>();

    }

    public void onStart() {
        for (Object object : INSTANCE.customObjects.values()){
            if (object instanceof SfMotor){
                ((SfMotor) object).resetEncoder();
            }
        }
        telemetryData.clear();
    }

}
