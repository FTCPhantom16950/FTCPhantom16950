package org.firstinspires.ftc.teamcode.ftclib.opModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.Subsystem;
import com.pedropathing.localization.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ftclib.SYS.HorSliderSYS;
import org.firstinspires.ftc.teamcode.ftclib.SYS.VerticalSliderSYS;
import org.firstinspires.ftc.teamcode.ftclib.SYS.WheelBaseSYS;
import org.firstinspires.ftc.teamcode.ftclib.SYS.ZXSYS;

@Autonomous
public class TESTOPMODE extends CommandOpMode {
    public final Pose startPose = new Pose(134.47662485746864, 75.53021664766247, Math.toRadians(0));
    WheelBaseSYS wheelBaseSYS;
    VerticalSliderSYS verticalSliderSYS;
    ZXSYS zxsys;
    HorSliderSYS horSliderSYS;
    @Override
    public void register(Subsystem... subsystems) {
        super.register(subsystems);
    }

    @Override
    public void schedule(Command... commands) {
        super.schedule(commands);
    }

    @Override
    public void initialize() {
        zxsys = new ZXSYS(this);
        wheelBaseSYS = new WheelBaseSYS(this, startPose);
        verticalSliderSYS = new VerticalSliderSYS(this);
        horSliderSYS = new HorSliderSYS(this);
        register(wheelBaseSYS, verticalSliderSYS, zxsys, horSliderSYS);
        schedule();
    }
}
