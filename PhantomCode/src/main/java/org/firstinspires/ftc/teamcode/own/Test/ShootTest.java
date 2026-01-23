package org.firstinspires.ftc.teamcode.Own.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Own.Actions.TeleOPActions.PodxodAction;
import org.firstinspires.ftc.teamcode.Own.Actions.TeleOPActions.ShootAction;
import org.firstinspires.ftc.teamcode.Own.Actions.Test.ControllerTestAction;
import org.firstinspires.ftc.teamcode.Own.Actions.Test.LoggerTestAction;
import org.firstinspires.ftc.teamcode.Own.Mechanism.LoggerTestMechanism;
import org.firstinspires.ftc.teamcode.Own.Mechanism.PodxodMechanism;
import org.firstinspires.ftc.teamcode.Own.Mechanism.ShootMechanism;
import org.firstinspires.ftc.teamcode.Own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomOpMode;
@TeleOp
public class ShootTest extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        mechanism.add(new LoggerTestMechanism());
        ShootMechanism shootMechanism = new ShootMechanism();
        PodxodMechanism podxodMechanism = new PodxodMechanism();
        mechanism.add(shootMechanism);
        mechanism.add(podxodMechanism);
        actions = new ParallelGroup(
                new ControllerTestAction(),
                new PodxodAction(),
                new LoggerTestAction()
        );
    }
}
