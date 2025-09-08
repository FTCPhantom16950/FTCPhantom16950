package org.firstinspires.ftc.teamcode.own.Actions;

import static org.firstinspires.ftc.teamcode.own.Mechanism.Servos.krut;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Servos.sample;
import static org.firstinspires.ftc.teamcode.own.Utils.Config.KRUT_START_POWER;
import static org.firstinspires.ftc.teamcode.own.Utils.Config.SAMPLE_START_POWER;


import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Action.InterruptibleAction;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class Test1 extends InterruptibleAction {
    PhantomOpMode opMode;
    public Test1(PhantomOpMode opMode) {
        super(opMode);
        this.opMode = opMode;
    }

    @Override
    public void run() {
        krut.setPower(0);
        opMode.sleep(2000);
        krut.setPower(-0.5);
        opMode.sleep(2000);
    }

    @Override
    public void handleInterrupt() {
        krut.setPower(KRUT_START_POWER);
    }
}
