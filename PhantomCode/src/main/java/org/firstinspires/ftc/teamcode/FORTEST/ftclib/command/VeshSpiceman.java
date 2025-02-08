package org.firstinspires.ftc.teamcode.FORTEST.ftclib.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.FORTEST.ftclib.SYS.VerticalSliderSYS;
import org.firstinspires.ftc.teamcode.own.Utils.PIDControl;

public class VeshSpiceman extends CommandBase {
    VerticalSliderSYS veetSl;
    PIDControl pidControl;

    @Override
    public void initialize() {
        super.initialize();
        pidControl = veetSl.control;
        pidControl.setTarget(0);
        pidControl.start();
        thread.start();
    }

    public VeshSpiceman(VerticalSliderSYS veetSl) {
        this.veetSl = veetSl;
        addRequirements(veetSl);
    }

    Thread thread = new Thread(() -> {
        while(!isFinished()){
            pidControl.setMeasured(VerticalSliderSYS.pod.getCurrentPosition());
            VerticalSliderSYS.pod.set(pidControl.getOut());
        }
    });
    @Override
    public void execute() {
        super.execute();
        pidControl.setTarget(1000);
        while(!pidControl.isAtTargetPos()){}
        pidControl.setTarget(2000);
        while(!pidControl.isAtTargetPos()){}
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
