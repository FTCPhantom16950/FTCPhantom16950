package org.firstinspires.ftc.teamcode.own.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.Actions.TeleOPActions.PodxodAction;
import org.firstinspires.ftc.teamcode.own.Actions.TeleOPActions.ShootAction;
import org.firstinspires.ftc.teamcode.own.Actions.Test.ControllerTestAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.PodxodMechanism;
import org.firstinspires.ftc.teamcode.own.Mechanism.ShootMechanism;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
@Autonomous
public class ShootTest extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        ShootMechanism shootMechanism = new ShootMechanism();
        PodxodMechanism podxodMechanism = new PodxodMechanism();
        mechanism.add(shootMechanism);
        mechanism.add(podxodMechanism);
        actions = new ParallelGroup(
                new ControllerTestAction(),
                new PodxodAction()
        );
    }
}
