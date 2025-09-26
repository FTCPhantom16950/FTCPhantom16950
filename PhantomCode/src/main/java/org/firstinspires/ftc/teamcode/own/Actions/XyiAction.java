package org.firstinspires.ftc.teamcode.own.Actions;

import org.firstinspires.ftc.teamcode.own.Mechanism.PidKal;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Action.InterruptibleAction;
import org.firstinspires.ftc.teamcode.own.Utils.GamepadControl;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class XyiAction extends InterruptibleAction {

    @Override
    public void run() {
        PidKal.xyn.setPower(1);
        if (GamepadControl.gamepadDriver.a){
            isInterrupted = true;
        }
    }

    @Override
    public void handleInterrupt() {
        PidKal.xyn.setPower(-1);
    }

    public XyiAction(PhantomOpMode opMode) {
        super(opMode);
        this.opMode = opMode;
    }
    private PhantomOpMode opMode;
/*    @Override
    public void execute() {
        while (opMode.opModeIsActive()){
            PidKal.xyn.setPower(1);
        }
    }*/
}
