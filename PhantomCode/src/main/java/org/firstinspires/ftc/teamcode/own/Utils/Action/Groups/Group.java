package org.firstinspires.ftc.teamcode.own.Utils.Action.Groups;

import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Group {
    /// Сет необходимых механизмов
    private final Set<Mechanism> necessaryMechanisms = new HashSet<>();

    /// Метод добавления необходимых механизмов
    public final void addNecessaryMechanism(Mechanism mechanism) {
        necessaryMechanisms.add(mechanism);
    }

    /// Метод добавления необходимых механизмов
    public final void addNecessaryMechanisms(Set<Mechanism> mechanisms) {
        necessaryMechanisms.addAll(mechanisms);
    }

    /// Метод получения необходимых механизмов
    public Set<Mechanism> getNecessaryMechanisms() {
        return Collections.unmodifiableSet(necessaryMechanisms);
    }

    /// Метод для реализации выполнения действия
    public abstract void execute();

}
