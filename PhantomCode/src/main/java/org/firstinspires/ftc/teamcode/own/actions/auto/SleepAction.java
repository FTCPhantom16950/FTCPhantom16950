package org.firstinspires.ftc.teamcode.own.actions.auto;

import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class SleepAction implements Action {
    long ms = 0;

    public SleepAction(long ms) {
        this.ms = ms;
    }

    @Override
    public void execute() throws InterruptedException {
        sleep(ms);
    }
}
