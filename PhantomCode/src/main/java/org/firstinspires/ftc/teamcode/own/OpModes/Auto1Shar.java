package org.firstinspires.ftc.teamcode.own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.Actions.AutoActions.MoveAction;
import org.firstinspires.ftc.teamcode.own.Actions.AutoActions.PulaloAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.PodxodMechanism;
import org.firstinspires.ftc.teamcode.own.Mechanism.ShootMechanism;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
@Autonomous
public class Auto1Shar extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        mechanism.add(new WheelBase());
        mechanism.add(new ShootMechanism());
        mechanism.add(new PodxodMechanism());
        actions = new ParallelGroup(
                new MoveAction(0,-0.5,0,750),
                new PulaloAction()

        );
    }
}
