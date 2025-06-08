package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс для работы с OpMode
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 02:40
 */
public abstract class PhantomOpMode extends OpMode {
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

    /// Инициализация всех механизмов, а также планировщика
    @Override
    public void init() {
        scheduler = new Scheduler.Builder()
                .setAction(action)
                .addMechanisms(
                        findNecessaryMechanisms(action)
                )
                .build();
        scheduler.initMechanism();
    }

    /// Старт OpMode
    @Override
    public void start() {
        super.start();
        scheduler.run();
    }

    /// Происходит в течении OpMode
    @Override
    public void loop() {

    }

    /// класс для указания имени, типа и группы OpMode
    public abstract PhantomOpMode runOpMode();

    /// Поиск необходимых механизмов
    private Set<Mechanism> findNecessaryMechanisms(Action actionAll) {
        return new HashSet<>(actionAll.getNecessaryMechanisms());
    }
}
