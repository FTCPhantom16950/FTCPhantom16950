package org.firstinspires.ftc.teamcode.FORTEST.ftclib.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.FORTEST.ftclib.SYS.VerticalSliderSYS;
import org.firstinspires.ftc.teamcode.own.Utils.PIDControl;

public class BucketUP extends CommandBase {
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

    public BucketUP(VerticalSliderSYS veetSl) {
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
        veetSl.kleshOpen();
        veetSl.vrashPerexvat();
        withTimeout(500);
        veetSl.kleshClosed();
        veetSl.poxod();
        pidControl.setTarget(3000);
        while (!pidControl.isAtTargetPos()){}
        veetSl.vrashSkin();
        withTimeout(300);
        veetSl.kleshOpen();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
