package org.firstinspires.ftc.teamcode.own.Utils.Action;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.Group;

/**
 * Класс создания действия
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 02:40
 */
public abstract class Action extends Group {
    /// Метод для реализации выполнения действия
    public abstract void execute() throws InterruptedException ;
    public Action() {}
}
