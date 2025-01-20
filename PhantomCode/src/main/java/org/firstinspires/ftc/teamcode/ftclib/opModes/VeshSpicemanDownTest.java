package org.firstinspires.ftc.teamcode.ftclib.opModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ftclib.SYS.VerticalSliderSYS;
import org.firstinspires.ftc.teamcode.ftclib.command.VeshSpicemanDown;

@Autonomous
public class VeshSpicemanDownTest extends CommandOpMode {
    VerticalSliderSYS verticalSliderSYS;
    VeshSpicemanDown veshSpicemanDown;
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
        veshSpicemanDown = new VeshSpicemanDown(verticalSliderSYS);
        register(verticalSliderSYS);
        schedule(veshSpicemanDown);
    }
}
