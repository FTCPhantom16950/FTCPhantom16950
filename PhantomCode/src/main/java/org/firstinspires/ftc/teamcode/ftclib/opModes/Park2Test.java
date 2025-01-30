package org.firstinspires.ftc.teamcode.ftclib.opModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ftclib.SYS.VerticalSliderSYS;
import org.firstinspires.ftc.teamcode.ftclib.command.Park2;
@Disabled

@Autonomous
public class Park2Test extends CommandOpMode {
    VerticalSliderSYS verticalSliderSYS;
    Park2 park2;
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
        park2 = new Park2(verticalSliderSYS);
        register(verticalSliderSYS);
        schedule(park2);
    }
}
