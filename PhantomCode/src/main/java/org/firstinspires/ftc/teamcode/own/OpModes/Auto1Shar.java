package org.firstinspires.ftc.teamcode.Own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Own.Actions.AutoActions.MoveAction;
import org.firstinspires.ftc.teamcode.Own.Actions.AutoActions.PulaloAction;
import org.firstinspires.ftc.teamcode.Own.Mechanism.PodxodMechanism;
import org.firstinspires.ftc.teamcode.Own.Mechanism.ShootMechanism;
import org.firstinspires.ftc.teamcode.Own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.Own.Utils.Action.Groups.LinearGroup;
import org.firstinspires.ftc.teamcode.Own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomOpMode;
@Autonomous
public class Auto1Shar extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        mechanism.add(new WheelBase());
        mechanism.add(new ShootMechanism());
        mechanism.add(new PodxodMechanism());
        actions = new LinearGroup(new
                ParallelGroup(
                new MoveAction(0,-0.5,0,550),
                new PulaloAction()
        ),
                new MoveAction(0,0,-0.5, 500),
                new MoveAction(0,-0.5,0,750)
        );
    }
}
