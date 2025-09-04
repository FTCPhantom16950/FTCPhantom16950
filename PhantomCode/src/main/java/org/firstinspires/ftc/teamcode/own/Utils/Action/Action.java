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
    /// Метод для реализации выполнения действия
    public abstract void execute();
    protected Action() {}

}
