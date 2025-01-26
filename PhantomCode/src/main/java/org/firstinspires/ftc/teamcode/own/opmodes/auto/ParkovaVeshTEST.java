package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.pod;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrash;

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
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@Autonomous
public class ParkovaVeshTEST extends LinearOpMode  {
    Follower follower;
    ElapsedTime timer = new ElapsedTime();
    public int pathState = 0;
    public final Pose startPose = new Pose(134.47662485746864, 75.53021664766247, Math.toRadians(0));
    final Pose toSpiecman = new Pose(106.8, 71, Math.toRadians(0));
    final Pose toPark = new Pose(131.0285062713797, 133.1630558722919, Math.toRadians(90));
    final Pose toBucket = new Pose(128.6365280289331,19.790235081374327, Math.toRadians(135));
    final Pose to1Sample = new Pose(118, 24, Math.toRadians(180));
    final Pose to1SampleControl = new Pose(142.95840867992766, 21.09222423146474);
    final Pose to2Sample = new Pose(118, 15.88426763110307, Math.toRadians(180));
    final Pose to2SampleControl = new Pose(118.48101265822785, 27.8625678119349);
    final Pose to2SampleEnd = new Pose(108.32549728752261,15.88426763110307,Math.toRadians(180));
    final Pose to3Sample = new Pose(115.59407069555303, 10.672748004560997, Math.toRadians(210));
    final Pose to3SampleControl = new Pose(117.40022805017104, 31.03306727480046);
    final Pose toPark2 = new Pose(80.20253164556962,47.132007233273065, Math.toRadians(270));
    final Pose ToPark2Control = new Pose(70.56781193490055,8.59312839059675);
    private Timer pathTimer, actionTimer;
    PathChain toSpiecmanPC, toParkPC, toBucketPC, to1SamplePC, to2SamplePC, to3SamplePC, toPark2PC,toBucketPCthird,toBucketPCfirst,toBucketPCsecond;
    HorizontSlider horizontSlider = new HorizontSlider(this);
    VerticalSlider verticalSlider = new VerticalSlider(this);
    Zx zx = new Zx(this);
    Thread followerthr = new Thread(() ->{
        while(opModeIsActive()){
            follower.update();
        }
    });
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
        if(opModeIsActive()){
            vrash.setPower(-0.93);
        }
        followerthr.start();
        follower.poseUpdater.resetHeadingToIMU();
        while (opModeIsActive()){
            zx.play();
            zx.play1();
            horizontSlider.play();
            verticalSlider.play();
            spicemanTrajectory();
            follower.telemetryDebug(telemetry);
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
                )).setLinearHeadingInterpolation(toSpiecman.getHeading(), toPark.getHeading()
                ).setPathEndTimeoutConstraint(1500).build();
        toBucketPC = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(startPose),
                                new Point(toBucket)
                        )
                ).setLinearHeadingInterpolation(startPose.getHeading(), toBucket.getHeading())
                .setPathEndTimeoutConstraint(1000).build();
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
                        new BezierCurve(
                                new Point(toSpiecman),
                                new Point(to1SampleControl),
                                new Point(to1Sample)
                        )
                ).setLinearHeadingInterpolation(toSpiecman.getHeading(), to1Sample.getHeading())
                .build();
        to2SamplePC = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(toBucket),
                                new Point(to2SampleControl),
                                new Point(to2Sample)
                        )
                ).setLinearHeadingInterpolation(toBucket.getHeading(), to2Sample.getHeading()).build();
        to3SamplePC = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(toBucket),
                                new Point(to3Sample)
                        )
                ).setLinearHeadingInterpolation(toBucket.getHeading(), to3Sample.getHeading())
                .build();
        toPark2PC = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Point(toBucket),
                        new Point(ToPark2Control),
                        new Point(toPark2)
                )).setLinearHeadingInterpolation(toBucket.getHeading(), toPark2.getHeading()).setPathEndTimeoutConstraint(1500).build();
    }
    Thread thread = new Thread(() -> {
        verticalSlider.perviPodem();
    });
    Thread thread1 = new Thread(() -> {
        pod.setPower(-0.7);
        sleep(900);
        pod.setPower(0.13);
    });
    Thread thread9 = new Thread(() -> {
        verticalSlider.spuskPosleBucket();
    });
    Thread thread10 = new Thread(() -> {
        verticalSlider.spuskPosleBucket();
        pod.setPower(1);
        sleep(500);
        pod.setPower(0.13);
        sleep(200);
    });
    Thread thread6 = new Thread(() -> {
        pod.setPower(1);
        sleep(500);
        pod.setPower(0.13);
        sleep(200);
    });

    Thread thread5 = new Thread(() -> {
        pod.setPower(-0.9);
        sleep(900);
        pod.setPower(0.15);
    });

    public void spicemanTrajectory() {
        switch (pathState) {
            case 0:
                // Move from start to scoring position
                follower.followPath(toSpiecmanPC);
                setPathState(1);
                thread.start();
                break;

            case 1: // Wait until the robot is near the scoring position
                if (!follower.isBusy() && (follower.getPose().getX() > (toSpiecman.getX() - 1) && follower.getPose().getY() > (toSpiecman.getY() - 1)) ) {
                    verticalSlider.podvesSpiecMan();
                    follower.followPath(to1SamplePC, true);
                    thread1.start();
                    setPathState(4);
                }
                break;
            case 4: // Wait until the robot is near the scoring position
                if (!follower.isBusy() && (follower.getPose().getX() > (to1Sample.getX() - 1.5) && follower.getPose().getY() > (to1Sample.getY() - 1)) ) {
                    Zx.zxAuto();
                    if (Zx.canBeCaptured){
                        follower.followPath(toBucketPCfirst, true);
                        setPathState(5);
                    }
                    else {
                        follower.followPath(to2SamplePC, true);
                        setPathState(6);
                    }
                }
                break;
            case 5: // Wait until the robot is near the scoring position
                if (!follower.isBusy() && (follower.getPose().getX() > (toBucket.getX() - 1) && follower.getPose().getY() > (toBucket.getY() - 1)) ) {
                    verticalSlider.podvesSample();
                    follower.followPath(to2SamplePC, true);
                    thread5.start();
                    setPathState(6);
                }
                break;
            case 6: // Wait until the robot is near the scoring position
                if (!follower.isBusy() && (follower.getPose().getX() > (to2Sample.getX() - 1) && follower.getPose().getY() > (to2Sample.getY() - 1)) ) {
                    Zx.zxAuto();
                    if (Zx.canBeCaptured) {
                        follower.followPath(toBucketPCsecond, true);
                        setPathState(10);
                    } else {
                        follower.followPath(toPark2PC, true);
                        setPathState(-1);
                        thread6.start();
                        if(!follower.isBusy() && (follower.getPose().getX() > (toPark2.getX() - 1) && follower.getPose().getY() > (toPark2.getY() - 1))){
                            vrash.setPower(1);
                        }
                    }
                }
                break;

            case 9:
                if (!follower.isBusy() && (follower.getPose().getX() > (to2Sample.getX() - 1) && follower.getPose().getY() > (to2Sample.getY() - 1)) ) {
                    verticalSlider.podvesSamplelast();
                    follower.followPath(toParkPC, true);
                    sleep(500);
                    vrash.setPower(-0.5);
                    thread9.start();
                    setPathState(-1);
                }
                break;
            case 10:
                if(!follower.isBusy() && (follower.getPose().getX() > (toBucket.getX() - 1) && follower.getPose().getY() > (toBucket.getY() - 1)) ){
                    verticalSlider.podvesSamplelast();
                    follower.followPath(toPark2PC, true);
                    setPathState(-1);
                    thread10.start();
                    if(!follower.isBusy() && (follower.getPose().getX() > (toPark2.getX() - 1) && follower.getPose().getY() > (toPark2.getY() - 1)) ){
                        vrash.setPower(1);
                    }
                }
                break;
        }
    }
    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }
}
