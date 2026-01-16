package org.firstinspires.ftc.teamcode.own.Test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.Actions.TeleOPActions.ShootAction;
import org.firstinspires.ftc.teamcode.own.Actions.Test.LoggerTestAction;
import org.firstinspires.ftc.teamcode.own.Actions.Test.PodsvetkAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.LoggerTestMechanism;
import org.firstinspires.ftc.teamcode.own.Mechanism.PodsvetkaMechanism;
import org.firstinspires.ftc.teamcode.own.Mechanism.ShootMechanism;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

@TeleOp
public class ForStart extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
//        mechanism.add(new LoggerTestMechanism());
        mechanism.add(new PodsvetkaMechanism());
        mechanism.add(new ShootMechanism());
        actions = new ParallelGroup(
//                new LoggerTestAction(),
                new PodsvetkAction(),
                new ShootAction());
    }
}
