package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.actions.auto.PedroAction;
import org.firstinspires.ftc.teamcode.own.actions.util.AprilTagFinder;
import org.firstinspires.ftc.teamcode.own.mechanism.FollowerMechanismDalnii;
import org.firstinspires.ftc.teamcode.own.mechanism.FollowerMechanismDalniiRed;
import org.firstinspires.ftc.teamcode.own.mechanism.LaunchMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.LimeLightMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.LinearGroup;

@Autonomous
public class AutoOtezdBlue extends PhantomOpMode {
    @Override
    public void customOpModeSettings() throws InterruptedException {
        Robot.INSTANCE.addMechanism(new FollowerMechanismDalniiRed());
        Robot.INSTANCE.addMechanism(new LimeLightMechanism());
        Robot.INSTANCE.addMechanism(new LaunchMechanism());
        Robot.INSTANCE.setStartAction(
                new LinearGroup(
                        new PedroAction(90, 125,
                                new BezierLine(
                                        new Pose(109.009, 135.701),
                                        new Pose(101.000, 98.000)
                                )
                        ),
                        new AprilTagFinder(),
                        new PedroAction(125, 55,
                                new BezierLine(
                                        new Pose(101.000, 98.000),
                                        new Pose(87.000, 85.000)
                                )
                        ),
                        new PedroAction(55, 0,
                                new BezierLine(
                                        new Pose(87.000, 85.000),
                                        new Pose(105.000, 84.000)
                                )
                        ),
                        new PedroAction(0, 0,
                                new BezierLine(
                                        new Pose(105.000, 84.000),
                                        new Pose(112.000, 84.000)
                                )
                        ),
                        new PedroAction(0, 0,
                                new BezierLine(
                                        new Pose(112.000, 84.000),
                                        new Pose(116.000, 84.000)
                                )
                        ),
                        new PedroAction(0, 55,
                                new BezierLine(
                                        new Pose(116.000, 84.000),
                                        new Pose(87.000, 85.000)
                                )
                        ),
                        new AprilTagFinder(),
                        new PedroAction(55, 0,
                                new BezierLine(
                                        new Pose(87.000, 85.000),
                                        new Pose(98.000, 60.000)
                                )
                        ),
                        new PedroAction(0, 0,
                                new BezierLine(
                                        new Pose(98.000, 60.000),
                                        new Pose(105.000, 60.000)
                                )
                        ),
                        new PedroAction(0, 0,
                                new BezierLine(
                                        new Pose(105.000, 60.000),
                                        new Pose(112.000, 60.000)
                                )
                        ),
                        new PedroAction(0,0,
                                new BezierLine(
                                        new Pose(112.000, 60.000),
                                        new Pose(116.000, 60.000)
                                )
                        ),
                        new PedroAction(0,55,
                                new BezierLine(
                                        new Pose(116.000, 60.000),
                                        new Pose(87.000, 85.000)
                                )
                                ),
                        new AprilTagFinder()
                )
        );

    }
}
