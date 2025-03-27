package org.firstinspires.ftc.teamcode.base;

import org.firstinspires.ftc.teamcode.interfaces.Action;

public class Scheduler{
    Action action;

    public void runSchedule() {
        action.runAction();
    }

    public void addAction(Action action) {
        this.action = action;
    }

}
