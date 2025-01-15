package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.pod;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;
import org.firstinspires.ftc.teamcode.own.positions.ZxPos;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@Autonomous
public class ParkovaVeshTEST extends LinearOpMode {
    Follower follower;
    ElapsedTime timer = new ElapsedTime();
    public int pathState = 0;
    public final Pose startPose = new Pose(134.47662485746864, 75.53021664766247, Math.toRadians(0));
    final Pose toSpiecman = new Pose(106.8, 71, Math.toRadians(0));
    final Pose toPark = new Pose(131.0285062713797, 133.1630558722919, Math.toRadians(90));
    final Pose toBucket = new Pose(128.07297605473204,15.270239452679593, Math.toRadians(135));
    final Pose to1Sample = new Pose(112.96693272519954, 24.136830102622582, Math.toRadians(180));
    final Pose to1SampleControl = new Pose(127.90877993158495, 26.928164196123156);
    final Pose to2Sample = new Pose(112.80273660205245, 12.31470923603192, Math.toRadians(180));
    final Pose to2SampleControl = new Pose(121.34093500570125, 7.8814139110604255);
    final Pose to3Sample = new Pose(115.59407069555303, 10.672748004560997, Math.toRadians(210));
    final Pose to3SampleControl = new Pose(117.40022805017104, 31.03306727480046);
    final Pose toPark2 = new Pose(80.62029646522234,46.63169897377423, Math.toRadians(270));
    final Pose ToPark2Control = new Pose(120.1915621436716,4.2690992018244085);
    private Timer pathTimer, actionTimer;
    PathChain toSpiecmanPC, toParkPC, toBucketPC, to1SamplePC, to2SamplePC, to3SamplePC, toPark2PC,toBucketPCthird,toBucketPCfirst,toBucketPCsecond;
    HorizontSlider horizontSlider = new HorizontSlider(this);
    VerticalSlider verticalSlider = new VerticalSlider(this);
    Zx zx = new Zx(this);
    @Override
    public void runOpMode() throws InterruptedException {
        Constants.setConstants(FConstants.class, LConstants.class);
        pathTimer = new Timer();
        follower = new Follower(hardwareMap);
        horizontSlider.init();
        verticalSlider.init();
        zx.init();
        waitForStart();
        follower.setStartingPose(startPose);
        pathBuilding();
        while (opModeIsActive()){
            spicemanTrajectory();
            follower.update();
            telemetry.addData("State pos", pathState);
            telemetry.update();
        }
    }
    public void pathBuilding(){
        toSpiecmanPC = follower.pathBuilder().addPath(new BezierLine(
                new Point(startPose),
                new Point(toSpiecman)
        )).setConstantHeadingInterpolation(Math.toRadians(0)).build();
        toParkPC = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Point(toBucket),
                        new Point(toPark)
                )).setLinearHeadingInterpolation(toSpiecman.getHeading(), toPark.getHeading()).build();
        toBucketPC = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(startPose),
                                new Point(toBucket)
                        )
                ).setLinearHeadingInterpolation(startPose.getHeading(), toBucket.getHeading()).build();
        toBucketPCfirst = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(to1Sample),
                                new Point(toBucket)
                        )
                ).setLinearHeadingInterpolation(to1Sample.getHeading(), toBucket.getHeading()).build();
        toBucketPCsecond = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(to2Sample),
                                new Point(toBucket)
                        )
                ).setLinearHeadingInterpolation(to2Sample.getHeading(), toBucket.getHeading()).build();
        toBucketPCthird = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(to3Sample),
                                new Point(toBucket)
                        )
                ).setLinearHeadingInterpolation(to3Sample.getHeading(), toBucket.getHeading()).build();
        to1SamplePC = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(toSpiecman),
                                new Point(to1Sample)
                        )
                ).setLinearHeadingInterpolation(toSpiecman.getHeading(), to1Sample.getHeading()).build();
        to2SamplePC = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(toBucket),
                                new Point(to2Sample)
                        )
                ).setLinearHeadingInterpolation(toBucket.getHeading(), to2Sample.getHeading()).build();
        to3SamplePC = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(toBucket),
                                new Point(to3Sample)
                        )
                ).setLinearHeadingInterpolation(toBucket.getHeading(), to3Sample.getHeading()).build();
        toPark2PC = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Point(toBucket),
                        new Point(ToPark2Control),
                        new Point(toPark2)
                )).setLinearHeadingInterpolation(toBucket.getHeading(), toPark2.getHeading()).build();
    }
    public void spicemanTrajectory() {
        switch (pathState) {
            case 0:
                verticalSlider.pod.setPower(1.0);
                sleep(300);
                pod.setPower(0.13);// Move from start to scoring position
                follower.followPath(toSpiecmanPC);
                setPathState(1);
                break;

            case 1: // Wait until the robot is near the scoring position
                if (!follower.isBusy() && (follower.getPose().getX() > (toSpiecman.getX() - 1) && follower.getPose().getY() > (toSpiecman.getY() - 1))) {
                    verticalSlider.podvesSpiecMan();
                    follower.followPath(to1SamplePC, true);
                    setPathState(4);
                }
                break;
            case 4: // Wait until the robot is near the scoring position
                if (!follower.isBusy() && (follower.getPose().getX() > (to1Sample.getX() - 1) && follower.getPose().getY() > (to1Sample.getY() - 1))) {
                    horizontSlider.bliz_zx();
                    follower.followPath(toBucketPCfirst, true);
                    setPathState(5);
                }
                break;
            case 5: // Wait until the robot is near the scoring position
                if (!follower.isBusy() && (follower.getPose().getX() > (toBucket.getX() - 1) && follower.getPose().getY() > (toBucket.getY() - 1))) {
                    verticalSlider.podvesSample();
                    follower.followPath(to2SamplePC, true);
                    setPathState(6);
                }
                break;
            case 6: // Wait until the robot is near the scoring position
                if (!follower.isBusy() && (follower.getPose().getX() > (to2Sample.getX() - 1) && follower.getPose().getY() > (to2Sample.getY() - 1))) {
                    horizontSlider.bliz_zx();
                    follower.followPath(toBucketPCsecond, true);
                    setPathState(7);
                }
                break;
            case 7: // Wait until the robot is near the scoring position
                if (!follower.isBusy() && (follower.getPose().getX() > (toBucket.getX() - 1) && follower.getPose().getY() > (toBucket.getY() - 1))) {
                    verticalSlider.podvesSample();
                    follower.followPath(to3SamplePC, true);
                    setPathState(8);
                }
                break;
            case 8: // Wait until the robot is near the scoring position
                if (!follower.isBusy() && (follower.getPose().getX() > (to3Sample.getX() - 1) && follower.getPose().getY() > (to3Sample.getY() - 1))) {
                    horizontSlider.bliz_zx();
                    follower.followPath(toBucketPCthird, true);
                    setPathState(9);
                }
                break;
            case 9:
                if (!follower.isBusy() && (follower.getPose().getX() > (toBucket.getX() - 1) && follower.getPose().getY() > (toBucket.getY() - 1))) {
                    verticalSlider.podvesSample();
                    follower.followPath(toParkPC, true);
                    setPathState(-1);
                }
                break;
        }
    }
    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }
}
