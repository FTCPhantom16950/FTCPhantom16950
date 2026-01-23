package org.firstinspires.ftc.teamcode.Own.Test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Own.Actions.TeleOPActions.ShootAction;
import org.firstinspires.ftc.teamcode.Own.Actions.Test.LoggerTestAction;
import org.firstinspires.ftc.teamcode.Own.Actions.Test.PodsvetkAction;
import org.firstinspires.ftc.teamcode.Own.Mechanism.LoggerTestMechanism;
import org.firstinspires.ftc.teamcode.Own.Mechanism.PodsvetkaMechanism;
import org.firstinspires.ftc.teamcode.Own.Mechanism.ShootMechanism;
import org.firstinspires.ftc.teamcode.Own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomOpMode;

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
