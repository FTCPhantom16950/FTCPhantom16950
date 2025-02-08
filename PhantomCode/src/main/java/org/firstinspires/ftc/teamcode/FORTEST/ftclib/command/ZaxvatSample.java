package org.firstinspires.ftc.teamcode.FORTEST.ftclib.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.FORTEST.ftclib.SYS.ZXSYS;

public class ZaxvatSample extends CommandBase {
    ZXSYS zxsys;
    public ZaxvatSample(ZXSYS zxsys) {
        this.zxsys = zxsys;
        addRequirements(zxsys);
    }

    @Override
    public void initialize() {
        super.initialize();
        zxsys.zxOtpusk();
        zxsys.krutOtpusk();
    }

    @Override
    public void execute() {
        super.execute();
        zxsys.krutZaxvat();
        withTimeout(500);
        zxsys.zxZaxvat();
        withTimeout(300);
        zxsys.krutOtpusk();
        withTimeout(500);
        zxsys.zxOtpusk();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
