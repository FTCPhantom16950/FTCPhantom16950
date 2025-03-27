package org.firstinspires.ftc.teamcode.base;

import org.firstinspires.ftc.teamcode.baseComand.Command;

import java.util.Arrays;
import java.util.List;

public  class LinearAction implements Command, Action {
    List<Command> commands;
    public LinearAction(Command... command) {
        commands.addAll(Arrays.asList(command));
    }

    public List<Command> getCommands() {
        return commands;
    }

    @Override
    public void run() {

    }
}
