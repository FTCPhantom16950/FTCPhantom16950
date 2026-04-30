package org.firstinspires.ftc.teamcode.own.mechanism;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.pedroPathing.pedroPathing.Constants;

@Config
public class FollowerMechanismDalnii implements Mechanism {
    public static Follower follower;
    HardwareMap hw;
    Pose start = new Pose(36.037, 136.075, Math.toRadians(90));

    @Override
    public void init() throws InterruptedException {
        hw = Robot.INSTANCE.getRobotData("HardwareMap" , HardwareMap.class);

        follower = Constants.createFollower(hw);
        follower.setStartingPose(start);
        follower.update();
        Robot.INSTANCE.addTelemetryData("Follower", follower);
        Robot.INSTANCE.addTelemetryData("RobotPoseX", follower.getPose().getX());
        Robot.INSTANCE.addTelemetryData("RobotPoseY", follower.getPose().getY());
        Robot.INSTANCE.addTelemetryData("startPoseX", start.getX());
        Robot.INSTANCE.addTelemetryData("startPoseY", start.getY());
        Robot.INSTANCE.addData("Follower", follower);
    }
}
