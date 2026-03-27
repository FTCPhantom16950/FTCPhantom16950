package org.firstinspires.ftc.teamcode.own.actions.auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;



public class PedroAction implements Action {
    PathChain path;
    Follower follower;
    double anglePrev, angleCurr;
    BezierCurve bezierCurve;
    BezierLine bezierLine;


    public PedroAction(int anglePrev, int angleCurr, BezierLine bezierLine) {
        this.anglePrev = Math.toRadians(anglePrev);
        this.angleCurr = Math.toRadians(angleCurr);
        this.bezierLine = bezierLine;
    }

    public PedroAction(int anglePrev, int angleCurr, BezierCurve bezierCurve) {
        this.anglePrev = Math.toRadians(anglePrev);
        this.angleCurr = Math.toRadians(angleCurr);
        this.bezierCurve = bezierCurve;
    }

    @Override
    public void execute() throws InterruptedException {
        follower = Robot.INSTANCE.getRobotData("Follower", Follower.class);
        if (bezierLine != null){
            path = follower.pathBuilder()
                    .addPath(bezierLine)
                    .setLinearHeadingInterpolation(anglePrev, angleCurr).build();
        }
        else {
            path = follower.pathBuilder()
                    .addPath(bezierCurve)
                    .setLinearHeadingInterpolation(anglePrev, angleCurr).build();
        }
        if (!Thread.currentThread().isInterrupted()){
            follower.followPath(path, true);
            Robot.INSTANCE.addTelemetryData("RobotPoseX", follower.getPose().getX());
            Robot.INSTANCE.addTelemetryData("RobotPoseY", follower.getPose().getY());
            while(((Math.abs(follower.getPose().getX() - follower.getCurrentPath().endPose().getX()) >= 0.3 &&
                    Math.abs(follower.getPose().getY() - follower.getCurrentPath().endPose().getY()) >= 0.3 &&
                    Math.abs(follower.getPose().getHeading() - follower.getCurrentPath().endPose().getHeading()) >= 0.3) || follower.isBusy()) && !follower.isRobotStuck()){
                Robot.INSTANCE.addTelemetryData("RobotPoseX", follower.getPose().getX());
                Robot.INSTANCE.addTelemetryData("RobotPoseY", follower.getPose().getY());
                follower.update();
            }
            follower.breakFollowing();
            follower.holdPoint(follower.getPose());
            follower.update();

        }
    }
}
