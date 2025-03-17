package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.klesh;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.pod;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.verx_color;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vidvig;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrash;
import static org.firstinspires.ftc.teamcode.own.Utils.Config.tolerance;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.ToPark2Control;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.to1Sample;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.to1SamplePC;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.to2Sample;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.to2SamplePC;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.to2SamplePC2;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.to3Sample;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.to3SamplePC;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toBucket;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toBucketCoontrol;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toBucketPC;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toBucketPCfirst;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toBucketPCsecond;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toBucketPCthird;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toBucketStart;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toPark2;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toPark2PC;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@Autonomous
public class Sample3TEST extends LinearOpMode  {
    Follower follower;
    ElapsedTime timer = new ElapsedTime();
    private int pathState = 0;
    public final Pose startPose = new Pose(135.35, 34.19, Math.toRadians(90));

    private Timer pathTimer, actionTimer;
    HorizontSlider horizontSlider = new HorizontSlider(this);
    VerticalSlider verticalSlider = new VerticalSlider(this);
    Zxnew zx = new Zxnew(this);
    Thread thread = new Thread(() -> {
        while (opModeInInit()){
            follower.telemetryDebug(new MultipleTelemetry(this.telemetry,  FtcDashboard.getInstance().getTelemetry()));
        }
        while (opModeIsActive()){
            telemetry.addData("korob", verticalSlider.captured);
            telemetry.update();
        }
    });
    @Override
    public void runOpMode() throws InterruptedException {
        Constants.setConstants(FConstants.class, LConstants.class);
        pathTimer = new Timer();
        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);
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

        }
    }
    public void pathBuilding(){
        toBucketStart = follower.pathBuilder().addPath(new BezierCurve(
                new Point(startPose),
                new Point(toBucketCoontrol),
                new Point(toBucket)
        )).setLinearHeadingInterpolation(startPose.getHeading(), toBucket.getHeading()).build();
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
        to2SamplePC2 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(to1Sample),
                                new Point(to2Sample)
                        )
                ).setLinearHeadingInterpolation(to1Sample.getHeading(), to2Sample.getHeading()).build();
    }
    boolean stopped = false;
    Thread thread1 = new Thread(() -> {
        verticalSlider.spuskPosleBucket();
    });
    Thread thread12 = new Thread(() -> {
        vrash.setPower(vidvig);
        klesh.setPower(-0.35);
        pod.setPower(1);
        klesh.setPower(-0.35);
        sleep(950);
        pod.setPower(0.15);
        stopped = true;
    });
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
        pod.setPower(-1);
        klesh.setPower(-0.35);
        sleep(650);
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
                if (!follower.isBusy() && (follower.getPose().getX() > (toBucket.getX() - tolerance) && follower.getPose().getY() > (toBucket.getY() - tolerance) && follower.getPose().getHeading() > (toBucket.getHeading() - 0.1))|| follower.isRobotStuck()) {follower.holdPoint(toBucket);
                    verticalSlider.podvesSample();
                    thread1.start();
                    follower.followPath(to1SamplePC, true);
                    setPathState(4);

                }
                break;
            case 4: // Wait until the robot is near the scoring position
                if (!follower.isBusy()&& (follower.getPose().getX() > (to1Sample.getX() - tolerance) && follower.getPose().getY() > (to1Sample.getY() - tolerance) && follower.getPose().getHeading() > (to1Sample.getHeading() - 0.1))|| follower.isRobotStuck()) {
                    follower.holdPoint(to1Sample);
                    zx.newZxAuto();
                    if (verx_color.getDistance(DistanceUnit.MM) <= 28) {
                        VerticalSlider.captured = true;
                    } else {
                        VerticalSlider.captured = false;
                    }
                    if (VerticalSlider.captured) {
                        thread12.start();
                        follower.followPath(toBucketPCfirst, true);
                        setPathState(5);
                    } else {
                        follower.followPath(to2SamplePC2, true);
                        setPathState(6);
                    }
                }
                break;
            case 5: // Wait until the robot is near the scoring position
                if (!follower.isBusy()&& (follower.getPose().getX() > (toBucket.getX() - tolerance) && follower.getPose().getY() > (toBucket.getY() - tolerance)&& follower.getPose().getHeading() > (toBucket.getHeading() - 0.1))|| follower.isRobotStuck()) {
                    follower.holdPoint(toBucket);
                    verticalSlider.podvesSample();
                    thread5.start();
                    follower.followPath(to2SamplePC, true);
                    setPathState(6);
                }
                break;
            case 6: // Wait until the robot is near the scoring position
                if (!follower.isBusy()&& (follower.getPose().getX() > (to2Sample.getX() - tolerance) && follower.getPose().getY() > (to2Sample.getY() - tolerance)&& follower.getPose().getHeading() > (to2Sample.getHeading() - 0.1))|| follower.isRobotStuck()) {
                    follower.holdPoint(to2Sample);
                    zx.newZxAuto();
                        if (verx_color.getDistance(DistanceUnit.MM) <= 28){
                            VerticalSlider.captured = true;
                        } else {
                            VerticalSlider.captured = false;
                        }
                        if(VerticalSlider.captured){
                            thread12.start();
                            follower.followPath(toBucketPCsecond, true);
                            setPathState(7);
                        }
                        else{
                            sleep(100);
                            follower.followPath(to3SamplePC, true);
                            setPathState(8);
                        }
                    }
                break;
            case 7:
                if (!follower.isBusy()&& (follower.getPose().getX() > (toBucket.getX() - tolerance) && follower.getPose().getY() > (toBucket.getY() - 1)&& follower.getPose().getHeading() > (toBucket.getHeading() - 0.1))|| follower.isRobotStuck()) {
                    follower.holdPoint(toBucket);
                    verticalSlider.podvesSample();
                    thread5.start();
                    follower.followPath(to3SamplePC, true);
                    setPathState(8);
                }
                break;
            case 8:
                if (!follower.isBusy()&& (follower.getPose().getX() > (to3Sample.getX() - 1) && follower.getPose().getY() > (to3Sample.getY() - 1)&& follower.getPose().getHeading() > (to3Sample.getHeading() - 0.1))|| follower.isRobotStuck()) {
                    follower.holdPoint(to3Sample);
                    zx.newZxAuto();
                        if (verx_color.getDistance(DistanceUnit.MM) <= 28){
                            VerticalSlider.captured = true;
                        } else {
                            VerticalSlider.captured = false;
                        }
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

                }
                break;
            case 10:
                if(!follower.isBusy()&& (follower.getPose().getX() > (toBucket.getX() - tolerance) && follower.getPose().getY() > (toBucket.getY() - tolerance)&& follower.getPose().getHeading() > (toBucket.getHeading() - 0.1))|| follower.isRobotStuck()){
                    verticalSlider.podvesSample();
                    thread6.start();
                    follower.followPath(toPark2PC, true);
                    setPathState(11);
                }
                break;

            case 11:
                if(!follower.isBusy()&& (follower.getPose().getX() > (toBucket.getX() - tolerance) && follower.getPose().getY() > (toBucket.getY() - tolerance)&& follower.getPose().getHeading() > (toBucket.getHeading() - 0.1))|| follower.isRobotStuck()) {
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
