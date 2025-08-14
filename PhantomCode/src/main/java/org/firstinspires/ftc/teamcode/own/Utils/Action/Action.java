package org.firstinspires.ftc.teamcode.own.Utils.Action;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.Group;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс создания действия
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 02:40
 */
public abstract class Action extends Group {
    /// Сет необходимых механизмов
    private final Set<Mechanism> necessaryMechanisms = new HashSet<>();

    /// Метод добавления необходимых механизмов


    /// Метод получения необходимых механизмов
    public Set<Mechanism> getNecessaryMechanisms() {
        return Collections.unmodifiableSet(necessaryMechanisms);
    }

    /// Метод для реализации выполнения действия
    public abstract void execute();

    public Action(Mechanism mechanism) {
        addNecessaryMechanism(mechanism);
    }

    public Action(Set<Mechanism> mechanisms) {
        addNecessaryMechanisms(mechanisms);
    }

    /// Метод для ожидания
    public boolean sleep(long ms) {
        try {
            Thread.sleep(ms);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

}
