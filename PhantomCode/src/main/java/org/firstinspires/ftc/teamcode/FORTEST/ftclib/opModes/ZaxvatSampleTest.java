package org.firstinspires.ftc.teamcode.FORTEST.ftclib.opModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.FORTEST.ftclib.SYS.ZXSYS;
import org.firstinspires.ftc.teamcode.FORTEST.ftclib.command.ZaxvatSample;
@Disabled

@Autonomous
public class ZaxvatSampleTest extends CommandOpMode {
    ZXSYS zxsys;
    ZaxvatSample zaxvatSample;
    @Override
    public void schedule(Command... commands) {
        super.schedule(commands);
    }

    @Override
    public void register(Subsystem... subsystems) {
        super.register(subsystems);
    }

    @Override
    public void initialize() {
        zxsys = new ZXSYS(this);
        zaxvatSample= new ZaxvatSample(zxsys);
        schedule(zaxvatSample);
    }
}
