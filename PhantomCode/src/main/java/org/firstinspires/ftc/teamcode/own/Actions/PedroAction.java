package org.firstinspires.ftc.teamcode.own.Actions;

import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;


public class PedroAction extends Action {
    PhantomOpMode opMode;
    public PedroAction(PhantomOpMode OpMode, Mechanism mechanism) {
        super(mechanism);
        this.opMode = OpMode;
    }

    @Override
    public void execute() {

    }
}
