package org.firstinspires.ftc.teamcode.own.Utils;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс для работы с OpMode
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 02:40
 */
public abstract class PhantomOpMode extends LinearOpMode {
    /// Имя необходимое для указания в runOpMode, должно быть уникальным
    public String name = "Default";
    /// Тип необходимый для указания в runOpMode
    public OpModeMeta.Flavor flavor = OpModeMeta.Flavor.TELEOP;
    /// Группа необходимая для указания в runOpMode
    public String group = "default";
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
    MultipleTelemetry multipleTelemetry;
    @Override
    public void runOpMode() throws InterruptedException {
        // инициализация настроек опмода
        customOpModeSettings();
        // Проверка наличия FtcDashboard
        if (FtcDashboard.getInstance() != null){
            // создание объекта для вывода информации в FtcDashboard и на контроллер
            multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        }
        else {
            multipleTelemetry = new MultipleTelemetry(telemetry);
        }
        // Планировщик задач
        scheduler = new Scheduler.Builder()
                // добавление действий
                .setAction(action)
                // добавление механизмов
                .addMechanisms(
                        findNecessaryMechanisms(action)
                )
                .build();
        // инициализация механизмов
        scheduler.initMechanism();
        //вывод инициализации телеметрии
        multipleTelemetry.addData("Status", "Initialized");
        multipleTelemetry.update();
        // ожидания нажатия на кнопку старт
        waitForStart();
        // запуск планировщика
        scheduler.run();
        // опмод выполняется
        while (this.opModeIsActive()){
            multipleTelemetry.addData("Status", "Initialized");
            multipleTelemetry.update();
            // Происходит в течении OpMode
        }
        // Опмод завершается
        multipleTelemetry.addData("Status", "Finished");
        multipleTelemetry.update();
    }

    /// класс для указания имени, типа и группы OpMode
    public abstract PhantomOpMode customOpModeSettings();

    /// Поиск необходимых механизмов
    private Set<Mechanism> findNecessaryMechanisms(Action actionAll) {
        return new HashSet<>(actionAll.getNecessaryMechanisms());
    }

}
