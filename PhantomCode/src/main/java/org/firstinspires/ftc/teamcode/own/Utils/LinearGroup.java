package org.firstinspires.ftc.teamcode.own.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/// Класс для добавления последовательных действий
/// Made by Hkial(Gleb)
/// Last Updated: 08.06.25 02:40
public class LinearGroup extends Action {
    /// список добавляемых действий
    private List<Action> actions = new ArrayList<Action>();
    private final Set<Mechanism> necessaryMechanisms = new HashSet<>();

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

    /**
     * Класс для добавления последовательных групп
     * @param actions действия которые будут выполняться последовательно
     */
    public LinearGroup(Action... actions) {
        this.actions.addAll(List.of(actions));
        for (Action a :
                this.actions) {
            necessaryMechanisms.addAll(a.getNecessaryMechanisms());
        }
    }
    /// Метод выполнения действий последовательно
    @Override
    public void execute() {
        for (Action a : actions) {
            a.execute();
        }
    }
}
