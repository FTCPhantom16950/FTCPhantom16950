package org.firstinspires.ftc.teamcode.own.Utils;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class PedroUtil {
    public Follower follower;
    LinearOpMode opMode;
    Pose startPose = new Pose(140,72,0);
    Pose endPose = new Pose(107,72,0);
    public PathChain start = follower.pathBuilder()
            .addPath(new BezierLine(new Point(startPose), new Point(endPose)))
            .setConstantHeadingInterpolation(0)
            .build(),
            end = follower.pathBuilder()
            .addPath(
                    // Line 2
                    new BezierCurve(
                            new Point(follower.getPose()),
                            new Point(104.680, 108.586, Point.CARTESIAN),
                            new Point(96.347, 143.479, Point.CARTESIAN),
                            new Point(135.146, 133.324, Point.CARTESIAN)
                    )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0)).build();
    public PedroUtil(Follower follower, LinearOpMode opMode) {
        this.follower = follower;
        this.opMode = opMode;
    }

    private int pathState = 0;
    public void setPathState(int pState) {
        pathState = pState;
    }
    public void autonomousPathUpdate() {


        switch (pathState) {
            case 0:
                follower.followPath(start, true);
                setPathState(1);
                break;
            case 1:

                /* You could check for
                - Follower State: "if(!follower.isBusy() {}"
                - Time: "if(pathTimer.getElapsedTimeSeconds() > 1) {}"
                - Robot Position: "if(follower.getPose().getX() > 36) {}"
                */

                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(follower.getPose().getX() > (endPose.getX() - 1) && follower.getPose().getY() > (endPose.getY() - 1)) {
                    /* Score Preload */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(end,true);
                    setPathState(2);
                }
                break;
            case 2:
                opMode.sleep(3000);
        }
    }
}
