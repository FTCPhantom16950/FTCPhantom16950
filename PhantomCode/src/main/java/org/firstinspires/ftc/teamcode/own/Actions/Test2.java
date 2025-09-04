package org.firstinspires.ftc.teamcode.own.Actions;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class Test2 extends Action {
    PhantomOpMode opMode;

    public Test2(PhantomOpMode opMode, Mechanism mechanism) {
        super(mechanism);
        this.opMode = opMode;
    }

    @Override
    public void execute() {
        while (opMode.opModeIsActive()){
            if (opMode.gamepad1.x){
            }
        }
    }
}
