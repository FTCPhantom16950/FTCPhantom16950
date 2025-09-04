package org.firstinspires.ftc.teamcode.own.Actions;

import static org.firstinspires.ftc.teamcode.own.Mechanism.Servos.krut;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Servos.sample;
import static org.firstinspires.ftc.teamcode.own.Utils.Action.InterruptibleAction.interrupt;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Action.InterruptibleAction;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class Test1 extends Action {
    PhantomOpMode opMode;

    public Test1(Mechanism mechanism, PhantomOpMode opMode) {
        super(mechanism);
        this.opMode = opMode;
    }

    @Override
    public void execute() {
        krut.setPower(0);
        opMode.sleep(2000);
        krut.setPower(-1);
    }
}
