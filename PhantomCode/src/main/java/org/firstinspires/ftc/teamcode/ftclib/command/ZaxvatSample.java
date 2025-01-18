package org.firstinspires.ftc.teamcode.ftclib.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.ftclib.SYS.ZXSYS;

public class ZaxvatSample extends CommandBase {
    ZXSYS zxsys;
    public ZaxvatSample(ZXSYS zxsys) {
        this.zxsys = zxsys;
        addRequirements(zxsys);
    }

    @Override
    public void execute() {
        super.execute();
        
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
