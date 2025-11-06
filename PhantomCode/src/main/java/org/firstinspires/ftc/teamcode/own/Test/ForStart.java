package org.firstinspires.ftc.teamcode.own.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.Actions.Test.ForTestAction;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

@Autonomous
public class ForStart extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        actions = new ForTestAction();
    }
}
