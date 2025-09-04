package org.firstinspires.ftc.teamcode.own.Utils;

import static org.firstinspires.ftc.teamcode.own.Utils.UnitedTelemetry.multipleTelemetry;
import static org.firstinspires.ftc.teamcode.own.Utils.UnitedTelemetry.setOpMode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.Group;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Класс для работы с OpMode</p>
 * <p>Создан для работы с {@link Scheduler}</p>
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 02:40
 */
public abstract class PhantomOpMode extends LinearOpMode {
    Thread telemetryExecutor;
    /// Имя необходимое для указания в runOpMode, должно быть уникальным
    private String name = "Default";
    /// Тип необходимый для указания в runOpMode
    private OpModeMeta.Flavor flavor = OpModeMeta.Flavor.TELEOP;
    /// Группа необходимая для указания в runOpMode
    private String group = "default";
    /// Действие запускаемое в начале OpMode
    public Group action;
    public Set<Mechanism> mechanism = new HashSet<Mechanism>();
    /// Планировщик задач
    private Scheduler scheduler;

    ///  Получить имя
    public String getName() {
        return name;
    }

    /// Получить тип
    public OpModeMeta.Flavor getFlavor() {
        return flavor;
    }

    /// Получить Группу
    public String getGroup() {
        return group;
    }



    @Override
    public void runOpMode(){
        setOpMode(this);
            // инициализация телеметрии
            initTelemetry();
            // инициализация настроек опмода
            customOpModeSettings();
            // инициализация Планировщик задач
            initScheduler();
            // ожидания нажатия на кнопку старт
            waitForStart();
            // запуск планировщика
            runScheduler();


    }

    /// класс для указания имени, типа и группы OpMode
    public abstract void customOpModeSettings();

    /// Поиск необходимых механизмов
    private Set<Mechanism> findNecessaryMechanisms(Group action) {
        if (action == null) throw new IllegalStateException("Action in OpMode mustn't be null");
        return new HashSet<>(action.getNecessaryMechanisms());
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setFlavor(OpModeMeta.Flavor flavor) {
        this.flavor = flavor;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void initTelemetry() {
        UnitedTelemetry.init();
    }
    private void initScheduler() {
        scheduler = new Scheduler.Builder()
                .setAction(action)
                .addMechanisms(mechanism)
                .build();

        scheduler.initMechanism();
        multipleTelemetry.addData("Status", "Initialized");
        multipleTelemetry.update();
    }
    private void runScheduler() {
        multipleTelemetry.addData("Status", "Running");
        multipleTelemetry.update();
        if (!telemetryExecutor.isAlive()){
            telemetryExecutor = new Thread(){
                @Override
                public void run() {
                    super.run();
                    while (opModeIsActive()){
                        multipleTelemetry.update();
                    }
                }
            };
            telemetryExecutor.start();
        }
        if (opModeIsActive()) {
            scheduler.run();
        }

    }
    private void finishOpMode(){
        multipleTelemetry.addData("Status", "Finished");
        multipleTelemetry.update();
    }

}
