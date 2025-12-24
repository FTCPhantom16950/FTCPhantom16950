package org.firstinspires.ftc.teamcode.own.Test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.Actions.Test.LoggerTestAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.LoggerTestMechanism;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

@TeleOp
public class ForStart extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        mechanism.add(new LoggerTestMechanism());
        actions = new ParallelGroup(
                new LoggerTestAction());
    }
}
