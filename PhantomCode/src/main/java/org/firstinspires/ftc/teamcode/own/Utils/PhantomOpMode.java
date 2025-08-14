package org.firstinspires.ftc.teamcode.own.Utils;

import static org.firstinspires.ftc.teamcode.own.Utils.UnitedTelemetry.multipleTelemetry;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Класс для работы с OpMode</p>
 * <p>Создан для работы с {@link Scheduler}</p>
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 02:40
 */
public abstract class PhantomOpMode extends LinearOpMode {
    /// Имя необходимое для указания в runOpMode, должно быть уникальным
    private String name = "Default";
    /// Тип необходимый для указания в runOpMode
    private OpModeMeta.Flavor flavor = OpModeMeta.Flavor.TELEOP;
    /// Группа необходимая для указания в runOpMode
    private String group = "default";
    /// Действие запускаемое в начале OpMode
    public Action action;
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
        try {
            // инициализация настроек опмода
            customOpModeSettings();
            // инициализация телеметрии
            initTelemetry();
            // инициализация Планировщик задач
            initScheduler();
            // ожидания нажатия на кнопку старт
            waitForStart();
            // запуск планировщика
            runScheduler();
        } finally {
            finishOpMode();
        }

    }

    /// класс для указания имени, типа и группы OpMode
    public abstract void customOpModeSettings();

    /// Поиск необходимых механизмов
    private Set<Mechanism> findNecessaryMechanisms(Action action) {
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
                .addMechanisms(findNecessaryMechanisms(action))
                .build();

        scheduler.initMechanism();
        multipleTelemetry.addData("Status", "Initialized");
        multipleTelemetry.update();
    }
    private void runScheduler() {
        multipleTelemetry.addData("Status", "Running");
        multipleTelemetry.update();

        if (opModeIsActive()) {
            scheduler.run();
        }
    }
    private void finishOpMode(){
        multipleTelemetry.addData("Status", "Finished");
        multipleTelemetry.update();
    }

}
