package org.firstinspires.ftc.teamcode.own.actions.autoactions;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.FuturePose;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class PedroAction implements Action {
    Follower follower;
    PathChain pathChain;
    double tolerance = 0.5;

    public PathChain Path1;


    public PedroAction(Follower follower) {
        this.follower = follower;
    }

    @Override
    public void execute() throws InterruptedException {
        follower.setStartingPose(new Pose(26.224, 127.141));
        Path1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new FuturePose() {
                                    @Override
                                    public Pose getPose() {
                                        return follower.getPose();
                                    }
                                },

                                new Pose(53.893, 90.322)
                        )
                )

                .build();
        if (Robot.INSTANCE.opMode.opModeIsActive()) {
            follower.followPath(Path1);
            Pose end = follower.getCurrentPath().endPose();
            follower.update();
            while ((follower.getPose().getY() <= end.getY() + tolerance && follower.getPose().getY() >= end.getY() - tolerance) && (follower.getPose().getX() <= end.getX() + tolerance && follower.getPose().getX() >= end.getX() - tolerance) && follower.isBusy() && !follower.isRobotStuck()) {
                Robot.INSTANCE.addData("Path", pathChain.toString());

            }
        }

    }

}
