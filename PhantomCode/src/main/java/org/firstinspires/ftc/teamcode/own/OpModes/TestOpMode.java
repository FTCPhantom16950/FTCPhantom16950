package org.firstinspires.ftc.teamcode.own.OpModes;

import org.firstinspires.ftc.teamcode.own.Actions.TestAction;
import org.firstinspires.ftc.teamcode.own.Utils.LinearGroup;
import org.firstinspires.ftc.teamcode.own.Utils.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class TestOpMode extends PhantomOpMode {

    @Override
    public PhantomOpMode runOpMode() {
        name = "test";
        group = "test";
        action = new ParallelGroup(
                new TestAction(this.hardwareMap),
                new LinearGroup(
                        new TestAction(this.hardwareMap)
                )
        );
        return this;
    }
}
