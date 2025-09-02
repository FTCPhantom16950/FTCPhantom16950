package org.firstinspires.ftc.teamcode.own.Actions;

import static org.firstinspires.ftc.teamcode.own.Mechanism.Servos.krut;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Servos.sample;

import org.firstinspires.ftc.teamcode.own.Utils.Action.InterruptibleAction;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class Test1 extends InterruptibleAction {
    PhantomOpMode opMode;

    public static void interruptTest1(){
        interrupt();
    }

    public Test1(Mechanism mechanism, PhantomOpMode opMode) {
        super(mechanism);
        this.opMode = opMode;
    }

    @Override
    public void run() {
        krut.setPower(0);
        opMode.sleep(2000);
        krut.setPower(-1);
    }

    @Override
    public void handleInterruption() {
        krut.setPower(-0.5);
    }
}
