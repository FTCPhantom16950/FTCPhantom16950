package org.firstinspires.ftc.teamcode.base;

import org.firstinspires.ftc.teamcode.baseComand.Command;

import java.util.Arrays;
import java.util.List;

public class Scheduler extends Thread {
    List<Action> actions;
    public void run() {

    }
    public void addAction(Action... action) {
        actions.addAll(Arrays.asList(action));
    }

}
