package org.firstinspires.ftc.teamcode.own.Actions;

import static org.firstinspires.ftc.teamcode.own.Mechanism.Servos.sample;

import android.net.wifi.aware.ParcelablePeerHandle;

import org.firstinspires.ftc.teamcode.own.Mechanism.Servos;
import org.firstinspires.ftc.teamcode.own.Utils.Action.InterruptibleAction;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class Test extends InterruptibleAction {
    PhantomOpMode opMode;
    public static void interruptTest(){
        interrupt();
    }
    public Test(Mechanism mechanism, PhantomOpMode opMode) {
        super(mechanism);
        this.opMode = opMode;
    }

    @Override
    public void run() {
        sample.setPower(0);
        opMode.sleep(2000);
        sample.setPower(-1);
    }

    @Override
    public void handleInterruption() {
        sample.setPower(-0.5);
    }
}
