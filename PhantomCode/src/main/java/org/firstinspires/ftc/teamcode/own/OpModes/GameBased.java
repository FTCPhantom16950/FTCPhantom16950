package org.firstinspires.ftc.teamcode.own.OpModes;

import org.firstinspires.ftc.teamcode.own.Actions.DriveAction;
import org.firstinspires.ftc.teamcode.own.Utils.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class GameBased extends PhantomOpMode {
    @Override
    public PhantomOpMode runOpMode() {
        name = "GamepadBased";
        group = "";
        action = new DriveAction(this);
        return this;
    }
}
