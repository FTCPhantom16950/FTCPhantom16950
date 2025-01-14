package org.firstinspires.ftc.teamcode.FORTEST.opModes;

import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;
@Autonomous
public class PedroPathingTester extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Constants.setConstants(FConstants.class, LConstants.class);
        Follower follower = new Follower(hardwareMap);
        PathBuilder builder = new PathBuilder();
        PathChain to90;
        to90 = builder
                .addPath(
                        // Line 1
                        new BezierLine(
                                new Point(follower.getPose()),
                                new Point(9.757, 84.983, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(90)).build();

        waitForStart();
        follower.setMaxPower(0.3);
        if (opModeIsActive()){
            follower.followPath(to90, false);
            follower.update();
            sleep(30000);
        }

    }
}
