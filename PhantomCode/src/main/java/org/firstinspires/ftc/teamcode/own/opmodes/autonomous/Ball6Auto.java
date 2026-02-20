package org.firstinspires.ftc.teamcode.own.opmodes.autonomous;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.actions.autoactions.MoveLeft;
import org.firstinspires.ftc.teamcode.own.actions.autoactions.Rotate;
import org.firstinspires.ftc.teamcode.own.actions.autoactions.ShootAutoAction;
import org.firstinspires.ftc.teamcode.own.actions.utilactions.CapturePosAction;
import org.firstinspires.ftc.teamcode.own.actions.utilactions.FollowerPosUpdate;
import org.firstinspires.ftc.teamcode.own.actions.utilactions.LimelightAction;
import org.firstinspires.ftc.teamcode.own.actions.utilactions.ShootPosSet;
import org.firstinspires.ftc.teamcode.own.actions.utilactions.SpinPosSet;
import org.firstinspires.ftc.teamcode.own.mechanism.CaptureMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.FollowerMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.LimelightMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.ShooterMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.SpinMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.WheelBaseMechanism;
import org.firstinspires.ftc.teamcode.own.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.LinearGroup;
import org.firstinspires.ftc.teamcode.own.utils.actions.ParallelGroup;

import java.util.concurrent.Executors;

@Autonomous
public class Ball6Auto extends PhantomOpMode {

    @Override
    public void onStart() {
        super.onStart();

    }

    Follower follower;

    @Override
    public void customOpModeSettings() throws InterruptedException {
        follower = Constants.createFollower(hardwareMap);
        mechanism.add(new WheelBaseMechanism());
        mechanism.add(new FollowerMechanism(new Pose(26.224, 127.141, 335), follower));
        mechanism.add(new ShooterMechanism());
        mechanism.add(new CaptureMechanism());
        mechanism.add(new SpinMechanism());
        mechanism.add(new LimelightMechanism());
        actions = new ParallelGroup(
                Robot.INSTANCE.threadPool, this,
                new LimelightAction(),
                new ParallelGroup(
                        Executors.newCachedThreadPool(), this,
                        new SpinPosSet(),
                        new ShootPosSet(),
                        new CapturePosAction(),
                        new FollowerPosUpdate()
                ),
//                new LinearGroup(
                        new Rotate(),
                        new ShootAutoAction()
//                        new MoveLeft()
//                )
        );
    }

    public static class Paths {

        public PathChain Path2;
        public PathChain Path3;

        public Paths() {


            Path2 = Robot.INSTANCE.follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(56.000, 84.000),

                                    new Pose(16.434, 83.298)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(143), Math.toRadians(180))

                    .build();

            Path3 = Robot.INSTANCE.follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(16.434, 83.298),

                                    new Pose(61.000, 84.000)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(143))

                    .build();
        }
    }


}
