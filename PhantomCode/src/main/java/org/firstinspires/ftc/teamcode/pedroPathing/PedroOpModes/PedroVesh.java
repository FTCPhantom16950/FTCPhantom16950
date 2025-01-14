package org.firstinspires.ftc.teamcode.pedroPathing.PedroOpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;
import org.firstinspires.ftc.teamcode.own.Utils.PedroUtil;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@Autonomous
public class PedroVesh extends LinearOpMode {
    private Follower follower;
    HorizontSlider horizontSlider = new HorizontSlider(this);
    Zx zx = new Zx(this);
    Pose startPose = new Pose(140,72,0);
    Pose endPose = new Pose(107,72,0);
    int i = 0;
    private int pathState = 0;
    public void setPathState(int pState) {
        pathState = pState;
    }
    private PathChain start, end;
    PedroUtil pedroUtil;
    private Telemetry telemetryA;
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
                sleep(30000);
        }}
    @Override
    public void runOpMode() throws InterruptedException {
        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        telemetryA = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetryA.update();
        zx.init();
        start = follower.pathBuilder()
                .addPath(new BezierLine(new Point(startPose), new Point(endPose)))
                .setConstantHeadingInterpolation(0)
                .build();
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
        horizontSlider.init();
        follower.setMaxPower(0.3);
        follower.followPath(start);
        waitForStart();
        while (opModeIsActive()){
            autonomousPathUpdate();
            follower.update();
            follower.telemetryDebug(telemetryA);
            telemetryA.update();
        }
    }
}
