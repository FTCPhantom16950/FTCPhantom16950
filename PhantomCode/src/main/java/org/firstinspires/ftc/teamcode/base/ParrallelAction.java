package org.firstinspires.ftc.teamcode.base;

import org.firstinspires.ftc.teamcode.interfaces.Action;
import org.firstinspires.ftc.teamcode.interfaces.Command;

import java.util.Arrays;
import java.util.List;

public class ParrallelAction implements Command, Action {
    List<Command> commands;
    public ParrallelAction(Command... command) {
        commands.addAll(Arrays.asList(command));
    }

    public List<Command> getCommands() {
        return commands;
    }

    @Override
    public void runCommand() {
        runAction();
    }

    @Override
    public void runAction() {
        for (Command command : commands){
            if (commands.stream().findFirst().equals(command)){
                command.runCommand();
            } else {
                Thread thread = new Thread(() -> {
                    command.runCommand();
                });
                thread.start();
            }
        }
    }
}
