package org.firstinspires.ftc.teamcode.own.Actions;

import static org.firstinspires.ftc.teamcode.own.Mechanism.Servos.krut;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Servos.sample;
import static org.firstinspires.ftc.teamcode.own.Utils.Config.SAMPLE_START_POWER;

import android.net.wifi.aware.ParcelablePeerHandle;

import org.firstinspires.ftc.teamcode.own.Mechanism.Servos;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Action.InterruptibleAction;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class Test extends InterruptibleAction {
    PhantomOpMode opMode;
    @Override
    public void run() {
        sample.setPower(0);
        opMode.sleep(2000);
        sample.setPower(-1);
        opMode.sleep(2000);
    }

    @Override
    public void handleInterrupt() {
        sample.setPower(SAMPLE_START_POWER);
    }

    public Test(PhantomOpMode opMode) {
        super(opMode);
        this.opMode = opMode;
    }
}
