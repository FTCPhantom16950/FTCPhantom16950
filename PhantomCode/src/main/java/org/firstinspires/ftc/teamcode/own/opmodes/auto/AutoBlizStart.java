package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.actions.auto.MotifFinder;
import org.firstinspires.ftc.teamcode.own.actions.auto.PedroAction;
import org.firstinspires.ftc.teamcode.own.mechanism.FollowerMechanismBliz;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.LinearGroup;

@Autonomous
public class AutoBlizStart extends PhantomOpMode {
    @Override
    public void customOpModeSettings() throws InterruptedException {
        Robot.INSTANCE.addMechanism(new FollowerMechanismBliz());
        Robot.INSTANCE.setStartAction(
                new LinearGroup(new PedroAction(
                        90, 55,
                        new BezierLine(
                                new Pose(48.000, 8.000),
                                new Pose(35.000, 23.000)
                        )
                ),
                new MotifFinder()));
    }
}
