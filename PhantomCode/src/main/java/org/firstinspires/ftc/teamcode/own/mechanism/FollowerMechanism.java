package org.firstinspires.ftc.teamcode.own.mechanism;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.own.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.own.pedroPathing.Drawing;
import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;

public class FollowerMechanism implements Mechanism {
    Follower follower;

    Pose startPose;

    public FollowerMechanism(Pose startPose, Follower follower) {
        this.startPose = startPose;
        this.follower = follower;
        follower.setStartingPose(startPose);
    }

    @Override
    public void init() throws InterruptedException {
        Robot.INSTANCE.follower = follower;
        Drawing.init();
    }

    @Override
    public void read() throws InterruptedException {
        Mechanism.super.read();
        Robot.INSTANCE.addData("follower", Robot.INSTANCE.follower.isBusy());
    }


}
