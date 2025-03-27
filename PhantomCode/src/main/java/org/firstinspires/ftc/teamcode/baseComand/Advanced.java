package org.firstinspires.ftc.teamcode.baseComand;

import org.firstinspires.ftc.teamcode.interfaces.Command;

public abstract class Advanced implements Command {
    @Override
    public void runCommand() {
        Command.super.runCommand();
        runAbstract();
    }
    public abstract void runAbstract();
}
