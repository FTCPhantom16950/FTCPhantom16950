package org.firstinspires.ftc.teamcode.own.Utils.Action.Groups;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/// Класс для добавления последовательных действий
/// Made by Hkial(Gleb)
/// Last Updated: 08.06.25 02:40
public class LinearGroup extends Group {
    /// список добавляемых действий
    private final List<Action> actions = new ArrayList<Action>();



    /**
     * Класс для добавления последовательных групп
     * @param actions действия которые будут выполняться последовательно
     */
    public LinearGroup(Action... actions) {
        this.actions.addAll(List.of(actions));
    }
    /// Метод выполнения действий последовательно
    @Override
    public void execute() {
        for (Action a : actions) {
            a.execute();
        }
    }
}
