package org.firstinspires.ftc.teamcode.own.OpModes;

import org.firstinspires.ftc.teamcode.own.Actions.DriveAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class GameBased extends PhantomOpMode {
    @Override
    public PhantomOpMode customOpModeSettings() {
        name = "GamepadBased";
        group = "";
        action = new DriveAction(this, new WheelBase(this.hardwareMap));
        return this;
    }
}
