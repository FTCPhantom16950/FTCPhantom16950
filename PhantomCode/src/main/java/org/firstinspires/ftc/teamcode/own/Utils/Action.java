package org.firstinspires.ftc.teamcode.own.Utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс создания действия
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 02:40
 */
public abstract class Action {
    /// Сет необходимых механизмов
    private final  Set<Mechanism> necessaryMechanisms = new HashSet<>();
    /// Метод добавления необходимых механизмов
    public void addNecessaryMechanism(Mechanism mechanism) {
        necessaryMechanisms.add(mechanism);
    }
    /// Метод добавления необходимых механизмов
    public void addNecessaryMechanisms(Set<Mechanism> mechanisms) {
        necessaryMechanisms.addAll(mechanisms);
    }
    /// Метод получения необходимых механизмов
    public Set<Mechanism> getNecessaryMechanisms() {
        return Collections.unmodifiableSet(necessaryMechanisms);
    }
    /// Метод для реализации выполнения действия
    public abstract void execute();
    /// Метод для ожидания
    public boolean sleep(long ms) {
        try {
            wait(ms);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CustomException("stopped while sleep", e);
        }

    }

}
