package org.firstinspires.ftc.teamcode.ftclib.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.ftclib.SYS.VerticalSliderSYS;
import org.firstinspires.ftc.teamcode.own.Utils.PIDControl;

public class VeshSpiceman extends CommandBase {
    VerticalSliderSYS veetSl;
    PIDControl pidControl;

    @Override
    public void initialize() {
        super.initialize();
        pidControl = veetSl.control;
        pidControl.setTarget(0);
        thread.start();
        pidControl.start();
    }

    public VeshSpiceman(VerticalSliderSYS veetSl) {
        this.veetSl = veetSl;
        addRequirements(veetSl);
    }

    Thread thread = new Thread(() -> {
        while(!isFinished()){
            pidControl.setMeasured(VerticalSliderSYS.pod.getCurrentPosition());
        }
    });
    @Override
    public void execute() {
        super.execute();
        pidControl.setTarget(1000);
        VerticalSliderSYS.pod.set(pidControl.getOut());
        while(!pidControl.isAtTargetPos()){

        }
        pidControl.setTarget(2000);
        VerticalSliderSYS.pod.set(pidControl.getOut());
        while(!pidControl.isAtTargetPos()){

        }
        pidControl.setTarget(-3000);
        VerticalSliderSYS.pod.set(pidControl.getOut());
        while(!pidControl.isAtTargetPos()){

        }
    }
}
