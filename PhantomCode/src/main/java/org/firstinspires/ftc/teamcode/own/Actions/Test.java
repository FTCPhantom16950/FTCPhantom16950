package org.firstinspires.ftc.teamcode.own.Actions;

import static org.firstinspires.ftc.teamcode.own.Mechanism.Servos.sample;

import android.net.wifi.aware.ParcelablePeerHandle;

import org.firstinspires.ftc.teamcode.own.Mechanism.Servos;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Action.InterruptibleAction;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class Test extends Action {
    PhantomOpMode opMode;
    public Test(Mechanism mechanism, PhantomOpMode opMode) {
        super(mechanism);
        this.opMode = opMode;
    }

    @Override
    public void execute() {
        while (opMode.opModeIsActive()){
            sample.setPower(0);
            opMode.sleep(1000);
            sample.setPower(-1);
            opMode.sleep(1000);
        }
    }
}
