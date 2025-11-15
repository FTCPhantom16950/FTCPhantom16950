package org.firstinspires.ftc.teamcode.own.Utils.Action.Groups;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/// Класс для добавления последовательных действий
/// Made by Hkial(Gleb)
/// Last Updated: 08.06.25 02:40
public class LinearGroup extends Group {
    /// список добавляемых действий
    private final List<Group> actions = new ArrayList<Group>();

    /**
     * Класс для добавления последовательных групп
     *
     * @param actions действия которые будут выполняться последовательно
     */
    public LinearGroup(Group... actions) {

        this.actions.addAll(Arrays.asList(actions));
    }

    /// Метод выполнения действий последовательно
    @Override
    public void execute() throws InterruptedException {
        for (Group a : actions) {
            a.execute();
        }
    }
}
