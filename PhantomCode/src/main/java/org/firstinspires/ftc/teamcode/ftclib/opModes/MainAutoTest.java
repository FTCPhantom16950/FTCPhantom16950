package org.firstinspires.ftc.teamcode.ftclib.opModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ftclib.SYS.VerticalSliderSYS;
import org.firstinspires.ftc.teamcode.ftclib.SYS.WheelBaseSYS;
import org.firstinspires.ftc.teamcode.ftclib.SYS.ZXSYS;
import org.firstinspires.ftc.teamcode.ftclib.command.Spiceman2Sample;
@Disabled

@Autonomous
public class MainAutoTest extends CommandOpMode {
    VerticalSliderSYS verticalSliderSYS;
    WheelBaseSYS wheelBaseSYS;
    Spiceman2Sample spiceman2Sample;
    ZXSYS zxsys;
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
        verticalSliderSYS  = new VerticalSliderSYS(this);
        wheelBaseSYS = new WheelBaseSYS(this,spiceman2Sample.startPose);
        zxsys = new ZXSYS(this);
        spiceman2Sample = new Spiceman2Sample(verticalSliderSYS, wheelBaseSYS ,zxsys);
        register(verticalSliderSYS, wheelBaseSYS, zxsys);
        schedule(spiceman2Sample);
    }
}
