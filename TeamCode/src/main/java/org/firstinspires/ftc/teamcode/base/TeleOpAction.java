package org.firstinspires.ftc.teamcode.base;

import org.firstinspires.ftc.teamcode.baseComand.Command;

import java.util.Arrays;
import java.util.List;

public class TeleOpAction extends Thread implements Action, Command {
    List<Command> commands;
    public TeleOpAction(Command... command) {
        commands.addAll(Arrays.asList(command));
    }

    public List<Command> getCommands() {
        return commands;
    }
}
