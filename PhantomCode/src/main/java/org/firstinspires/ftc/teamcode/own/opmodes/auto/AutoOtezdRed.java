package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.actions.auto.PedroAction;
import org.firstinspires.ftc.teamcode.own.actions.util.AprilTagFinder;
import org.firstinspires.ftc.teamcode.own.mechanism.FollowerMechanismDalnii;
import org.firstinspires.ftc.teamcode.own.mechanism.LaunchMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.LimeLightMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.LinearGroup;

@Autonomous
public class AutoOtezdRed extends PhantomOpMode {
    @Override
    public void customOpModeSettings() throws InterruptedException {
        Robot.INSTANCE.addMechanism(new FollowerMechanismDalnii());
        Robot.INSTANCE.addMechanism(new LimeLightMechanism());
        Robot.INSTANCE.addMechanism(new LaunchMechanism());
        Robot.INSTANCE.setStartAction(
                new LinearGroup(
                        new PedroAction(90, 55,
                                new BezierLine(
                                        new Pose(34.991, 135.701),
                                        new Pose(43.000, 98.000)
                                )
                        ),
                        new AprilTagFinder(),
                        new PedroAction(55, 125,
                                new BezierLine(
                                        new Pose(43.000, 98.000),
                                        new Pose(57.000, 85.000)
                                )
                        ),
                        new PedroAction(125, 180,
                                new BezierLine(
                                        new Pose(57.000, 85.000),
                                        new Pose(39.000, 84.000)
                                )
                        ),
                        new PedroAction(180, 180,
                                new BezierLine(
                                        new Pose(39.000, 84.000),
                                        new Pose(32.000, 84.000)
                                )
                        ),
                        new PedroAction(180, 180,
                                new BezierLine(
                                        new Pose(32.000, 84.000),
                                        new Pose(28.000, 84.000)
                                )
                        ),
                        new PedroAction(180, 125,
                                new BezierLine(
                                        new Pose(28.000, 84.000),
                                        new Pose(57.000, 85.000)
                                )
                        ),
                        new AprilTagFinder(),
                        new PedroAction(125, 180,
                                new BezierLine(
                                        new Pose(57.000, 85.000),
                                        new Pose(46.000, 60.000)
                                )
                        ),
                        new PedroAction(180, 180,
                                new BezierLine(
                                        new Pose(46.000, 60.000),
                                        new Pose(39.000, 60.000)
                                )
                        ),
                        new PedroAction(180, 180,
                                new BezierLine(
                                        new Pose(39.000, 60.000),
                                        new Pose(32.000, 60.000)
                                )
                        ),
                        new PedroAction(180,180,
                                new BezierLine(
                                        new Pose(32.000, 60.000),
                                        new Pose(28.000, 60.000)
                                )
                        ),
                        new PedroAction(180,125,
                                new BezierLine(
                                        new Pose(28.000, 60.000),
                                        new Pose(57.000, 85.000)
                                )
                        ),
                        new AprilTagFinder()
                )
        );

    }
}
