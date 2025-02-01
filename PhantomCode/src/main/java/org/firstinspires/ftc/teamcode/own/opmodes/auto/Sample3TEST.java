package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.pod;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.verx_color;
import static org.firstinspires.ftc.teamcode.own.Utils.Config.tolerance;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
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

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@Autonomous
public class Sample3TEST extends LinearOpMode  {
    Follower follower;
    ElapsedTime timer = new ElapsedTime();
    public int pathState = 0;
    public final Pose startPose = new Pose(135.35, 34.19, Math.toRadians(90));
    final Pose toSpiecman = new Pose(105.9, 71, Math.toRadians(0));
    final Pose toPark = new Pose(131.0285062713797, 133.1630558722919, Math.toRadians(90));
    final Pose toBucket = new Pose(127.36,18.364, Math.toRadians(135));
    final Pose toBucketCoontrol = new Pose(121.60578661844485,37.2368896925859);
    final Pose to1Sample = new Pose(118.7, 25, Math.toRadians(180));
    final Pose to1SampleControl = new Pose(142.95840867992766, 21.09222423146474);
    final Pose to2Sample = new Pose(119, 15.8, Math.toRadians(180));
    final Pose to2SampleControl = new Pose(118.48101265822785, 27.8625678119349);
    final Pose to2SampleEnd = new Pose(108.32549728752261,15.88426763110307,Math.toRadians(180));
    final Pose to3Sample = new Pose(117.2, 13.4, Math.toRadians(200));
    final Pose to3SampleControl = new Pose(117.40022805017104, 31.03306727480046);
    final Pose toPark2 = new Pose(80.20253164556962,50, Math.toRadians(270));
    final Pose ToPark2Control = new Pose(70.56781193490055,8.59312839059675);
    final Pose ToPark2Control2 = new Pose(114.05424954792043,8.59312839059675);
    private Timer pathTimer, actionTimer;
    PathChain toBucketStart, toParkPC, toBucketPC, to1SamplePC, to2SamplePC, to3SamplePC,toPark2PC2, toPark2PC,toBucketPCthird,toBucketPCfirst,toBucketPCsecond;
    HorizontSlider horizontSlider = new HorizontSlider(this);
    VerticalSlider verticalSlider = new VerticalSlider(this);
    Zx zx = new Zx(this);
    Thread thread = new Thread(() -> {
        while (opModeInInit()){
            follower.telemetryDebug(new MultipleTelemetry(this.telemetry,  FtcDashboard.getInstance().getTelemetry()));
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
        follower.update();
        if(opModeIsActive()){
            follower.poseUpdater.setCurrentPoseWithOffset(startPose);
            follower.update();
        }
        thread.start();
        while (opModeIsActive()){
            follower.update();
            zx.play();
            zx.play1();
            horizontSlider.play();
            verticalSlider.play();
            spicemanTrajectory();
            telemetry.addData("korob", verticalSlider.captured);
            telemetry.update();
        }
    }
    public void pathBuilding(){
        toBucketStart = follower.pathBuilder().addPath(new BezierCurve(
                new Point(startPose),
                new Point(toBucketCoontrol),
                new Point(toBucket)
        )).setLinearHeadingInterpolation(startPose.getHeading(), toBucket.getHeading()).build();
        toParkPC = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Point(toBucket),
                        new Point(toPark)
                )).setLinearHeadingInterpolation(toSpiecman.getHeading(), toPark.getHeading()
                ).build();
        toBucketPC = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(startPose),
                                new Point(toBucket)
                        )
                ).setLinearHeadingInterpolation(startPose.getHeading(), toBucket.getHeading())
                .build();
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
                                new Point(toBucket),
                                new Point(to1Sample)
                        )
                ).setLinearHeadingInterpolation(toBucket.getHeading(), to1Sample.getHeading())
                .build();
        to2SamplePC = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
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
                ).setLinearHeadingInterpolation(toBucket.getHeading(), to3Sample.getHeading())
                .build();
        toPark2PC = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Point(toBucket),
                        new Point(ToPark2Control),
                        new Point(toPark2)
                )).setLinearHeadingInterpolation(toBucket.getHeading(), toPark2.getHeading()).setPathEndTimeoutConstraint(1500).build();
        toPark2PC2 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(toSpiecman),
                                new Point(ToPark2Control2),
                                new Point(toPark2)
                        )
                ).setLinearHeadingInterpolation(toSpiecman.getHeading(),toPark2.getHeading()).build();
    }

    Thread thread1 = new Thread(() -> {
        verticalSlider.spuskPosleBucket();
    });
    Thread thread12 = new Thread(VerticalSlider::podem);
    Thread thread10 = new Thread(() -> {
        verticalSlider.spuskPosleBucket();
        pod.setPower(1);
        sleep(500);
        pod.setPower(0.13);
        sleep(200);
    });
    Thread thread6 = new Thread(() -> {
        pod.setPower(-0.9);
        sleep(800);
        pod.setPower(0.15);
    });

    Thread thread5 = new Thread(() -> {
        pod.setPower(-0.9);
        sleep(800);
        pod.setPower(0.15);
    });

    public void spicemanTrajectory() {
        switch (pathState) {
            case 0:
                // Move from start to scoring position
                thread12.start();
                follower.followPath(toBucketStart,true);
                setPathState(1);
                break;

            case 1:// Wait until the robot is near the scoring position
                if (!follower.isBusy() && (follower.getPose().getX() > (toBucket.getX() - tolerance) && follower.getPose().getY() > (toBucket.getY() - tolerance) && follower.getPose().getHeading() > (toBucket.getHeading() - tolerance))|| follower.isRobotStuck()) {
                    follower.holdPoint(toBucket);
                    verticalSlider.podvesSample();
                    thread1.start();
                    follower.followPath(to1SamplePC, true);
                    setPathState(4);

                }
                break;
            case 4: // Wait until the robot is near the scoring position
                if (!follower.isBusy()&& (follower.getPose().getX() > (to1Sample.getX() - tolerance) && follower.getPose().getY() > (to1Sample.getY() - tolerance) && follower.getPose().getHeading() > (to1Sample.getHeading() - 0.5))|| follower.isRobotStuck()) {
                    follower.holdPoint(to1Sample);
                    sleep(100);
                    Zx.zxAuto();
                    sleep(100);
                    if (Zx.canBeCaptured){

                        if (verx_color.getDistance(DistanceUnit.MM) <= 28){
                            VerticalSlider.captured = true;
                        } else {
                            VerticalSlider.captured = false;
                        }
                        sleep(200);
                        if(VerticalSlider.captured){
                            thread12.start();
                            follower.followPath(toBucketPCfirst, true);
                            setPathState(5);
                        }
                       else{
                            follower.followPath(toPark2PC, true);
                           setPathState(-1);
                            pod.setPower(1);
                            sleep(600);
                            pod.setPower(0);
                        }
                    }
                    else {
                        follower.followPath(to2SamplePC, true);
                        setPathState(6);
                    }
                }
                break;
            case 5: // Wait until the robot is near the scoring position
                if (!follower.isBusy()&& (follower.getPose().getX() > (toBucket.getX() - tolerance) && follower.getPose().getY() > (toBucket.getY() - tolerance)&& follower.getPose().getHeading() > (toBucket.getHeading() - 0.5))|| follower.isRobotStuck()) {
                    follower.holdPoint(toBucket);
                    verticalSlider.podvesSample();
                    thread5.start();
                    follower.followPath(to2SamplePC, true);
                    setPathState(6);
                }
                break;
            case 6: // Wait until the robot is near the scoring position
                if (!follower.isBusy()&& (follower.getPose().getX() > (to2Sample.getX() - tolerance) && follower.getPose().getY() > (to2Sample.getY() - tolerance)&& follower.getPose().getHeading() > (to2Sample.getHeading() - 0.5))|| follower.isRobotStuck()) {
                    follower.holdPoint(to2Sample);
                    Zx.zxAuto();
                    if (Zx.canBeCaptured){

                        if (verx_color.getDistance(DistanceUnit.MM) <= 28){
                            VerticalSlider.captured = true;
                        } else {
                            VerticalSlider.captured = false;
                        }
                        sleep(200);
                        if(VerticalSlider.captured){
                            thread12.start();
                            follower.followPath(toBucketPCsecond, true);
                            setPathState(7);
                        }
                        else{
                            follower.followPath(toPark2PC, true);
                            setPathState(-1);
                            pod.setPower(1);
                            sleep(600);
                            pod.setPower(0);
                        }
                    } else {
                        follower.followPath(to3SamplePC, true);
                        setPathState(8);

                    }
                }
                break;
            case 7:
                if (!follower.isBusy()&& (follower.getPose().getX() > (toBucket.getX() - tolerance) && follower.getPose().getY() > (toBucket.getY() - 1)&& follower.getPose().getHeading() > (toBucket.getHeading() - 0.5))|| follower.isRobotStuck()) {
                    follower.holdPoint(toBucket);
                    verticalSlider.podvesSample();
                    thread5.start();
                    follower.followPath(to3SamplePC, true);
                    setPathState(8);
                }
            case 8:
                if (!follower.isBusy()&& (follower.getPose().getX() > (to3Sample.getX() - 1) && follower.getPose().getY() > (to3Sample.getY() - 1)&& follower.getPose().getHeading() > (to3Sample.getHeading() - 0.5))|| follower.isRobotStuck()) {
                    follower.holdPoint(to3Sample);
                    Zx.zxAuto();
                    if (Zx.canBeCaptured){
                        if (verx_color.getDistance(DistanceUnit.MM) <= 28){
                            VerticalSlider.captured = true;
                        } else {
                            VerticalSlider.captured = false;
                        }
                        sleep(200);
                        if(VerticalSlider.captured){
                            thread12.start();
                            follower.followPath(toBucketPCthird, true);
                            setPathState(10);
                        }
                        else{
                            follower.followPath(toPark2PC, true);
                            setPathState(-1);
                            pod.setPower(1);
                            sleep(600);
                            pod.setPower(0);
                        }
                    } else {
                        follower.followPath(toPark2PC, true);
                        setPathState(-1);
                        pod.setPower(1);
                        sleep(600);
                        pod.setPower(0);
                    }
                }
            case 10:
                if(!follower.isBusy()&& (follower.getPose().getX() > (toBucket.getX() - tolerance) && follower.getPose().getY() > (toBucket.getY() - tolerance)&& follower.getPose().getHeading() > (toBucket.getHeading() - 0.5))|| follower.isRobotStuck()){
                    verticalSlider.podvesSample();
                    thread6.start();
                    follower.followPath(toPark2PC, true);
                    setPathState(11);
                }
                break;

            case 11:
                if(!follower.isBusy()&& (follower.getPose().getX() > (toBucket.getX() - tolerance) && follower.getPose().getY() > (toBucket.getY() - tolerance)&& follower.getPose().getHeading() > (toBucket.getHeading() - 0.5))|| follower.isRobotStuck()) {
                    pod.setPower(1);
                    sleep(600);
                    pod.setPower(0);
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
