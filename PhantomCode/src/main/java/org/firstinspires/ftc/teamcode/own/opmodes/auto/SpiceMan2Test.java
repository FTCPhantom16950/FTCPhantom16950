package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.ds;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.pod;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.sample;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrash;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrashPower;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krut2_start_power;
import static org.firstinspires.ftc.teamcode.own.Utils.Config.tolerance;

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
public class SpiceMan2Test extends LinearOpMode {
    Follower follower;
    ElapsedTime timer = new ElapsedTime();
    public int pathState = 0;
    public final Pose startPose = new Pose(134.47662485746864, 75.53021664766247, Math.toRadians(0));
    final Pose toSpiecman = new Pose(105.9, 71, Math.toRadians(0));
    final Pose toGetToSample = new Pose(84.36889692585895,103.37793851717902, Math.toRadians(180));
    final Pose toGetToSampleControl = new Pose(131.5,112.7, Math.toRadians(180));
    final Pose to2Sample = new Pose(77.07775768535262,120, Math.toRadians(180));
    final Pose toThrowSecondSample = new Pose(122,120, Math.toRadians(180));
    final Pose toThrowSecondSample2 = new Pose(116,120, Math.toRadians(180));
    final Pose toCapture2Spiceman = new Pose(134.5,121.8, Math.toRadians(180));
    final Pose toSet2SpicemanControl = new Pose(134.8860759493671,79.42133815551537);
    final Pose toPark = new Pose(131.0285062713797, 133.1630558722919, Math.toRadians(90));
    final Pose toParkControl = new Pose(132.54249547920432,94.78481012658227);
    private Timer pathTimer, actionTimer;
    PathChain toSpiceManPC, toSecondSample, toSecondSpiceman, toCapture, toSpiceman2, toParkPC;
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
        VerticalSlider.vrashPower = -0.49;
        vrash.setPower(vrashPower);
        Zx.brat.setPower(zx.krut_start_power);
        Zx.brat.setPower(krut2_start_power);
        while (opModeIsActive()){
            follower.update();
            zx.play();
            zx.play1();
            horizontSlider.play();
            verticalSlider.play();
            spicemanTrajectory();
            follower.telemetryDebug(telemetry);
            telemetry.update();
        }
    }
    // производим постройку всех траекторий
    public void pathBuilding() {
        toSpiceManPC = follower.pathBuilder()
                .addPath(
                new BezierCurve(
                        new Point(startPose),
                        new Point(toSpiecman)
                )
        ).setConstantHeadingInterpolation(0).build();
        toSecondSample = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(toSpiecman),
                                new Point(toGetToSampleControl),
                                new Point(toGetToSample)
                        )
                )
                .setLinearHeadingInterpolation(toSpiecman.getHeading(), toGetToSample.getHeading())
                .addPath(new BezierLine(
                       new Point(toGetToSample),
                        new Point(to2Sample)
                )
                ).setConstantHeadingInterpolation(to2Sample.getHeading())
                .addPath(
                        new BezierLine(
                                new Point(to2Sample),
                                new Point(toThrowSecondSample)
                        )
                ).setConstantHeadingInterpolation(toThrowSecondSample.getHeading())
                .addPath(
                        new BezierLine(
                                new Point(toThrowSecondSample),
                                new Point(toThrowSecondSample2)
                        )
                ).setConstantHeadingInterpolation(toThrowSecondSample2.getHeading())
                .build();
        toCapture = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(toThrowSecondSample),
                                new Point(toCapture2Spiceman)
                        )
                ).setConstantHeadingInterpolation(toCapture2Spiceman.getHeading())
                .build();
        toSpiceManPC = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(toCapture2Spiceman),
                                new Point(toSet2SpicemanControl),
                                new Point(toSpiecman)
                        )
                ).setLinearHeadingInterpolation(toCapture2Spiceman.getHeading(), toSpiecman.getHeading())
                .build();
        toParkPC = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(toSpiecman),
                                new Point(toParkControl),
                                new Point(toPark)
                        )
                ).setLinearHeadingInterpolation(toSpiecman.getHeading(), toPark.getHeading()).build();
    }
    Thread case0 = new Thread(() ->{
        verticalSlider.perviPodem();
    });
    Thread case1 = new Thread(() ->{
        pod.setPower(-0.9);
        sleep(900);
        pod.setPower(0.15);
    });
    Thread case3 = new Thread(() -> {
        verticalSlider.perviPodem();
    });
    Thread case4 = new Thread(() ->{
        pod.setPower(-0.9);
        sleep(900);
        pod.setPower(0.15);
    });

    public void spicemanTrajectory() {
        switch (pathState) {
            case 0:
                case0.start();
                follower.followPath(toSpiceManPC,true);
                setPathState(1);
                break;
            case 1:
                if(!follower.isBusy() && (follower.getPose().getX() > (toSpiecman.getX() - tolerance) && follower.getPose().getY() > (toSpiecman.getY() - tolerance) && follower.getPose().getHeading() > (toSpiecman.getHeading() - tolerance))|| follower.isRobotStuck()){
                    follower.holdPoint(toSpiecman);
                    verticalSlider.podvesSpiecMan();
                    sleep(1000);
                    follower.followPath(toSecondSample, true);
                    case1.start();
                    setPathState(2);
                }
                break;
            case 2:
                if(!follower.isBusy() && (follower.getPose().getX() > (toThrowSecondSample.getX() - tolerance) && follower.getPose().getY() > (toThrowSecondSample.getY() - tolerance) && follower.getPose().getHeading() > (toThrowSecondSample.getHeading() - tolerance))|| follower.isRobotStuck()) {
                    follower.holdPoint(toThrowSecondSample);
                    sample.setPower(-0.85);
                    if(ds.getDistance(DistanceUnit.MM) >= 30){
                        follower.followPath(toCapture,true);
                        setPathState(3);
                    } else {
                        follower.followPath(toParkPC,true);
                        case4.start();
                        setPathState(5);
                    }
                }
                break;
            case 3:
                if(!follower.isBusy() && (follower.getPose().getX() > (toCapture2Spiceman.getX() - tolerance) && follower.getPose().getY() > (toCapture2Spiceman.getY() - tolerance) && follower.getPose().getHeading() > (toCapture2Spiceman.getHeading() - tolerance))|| follower.isRobotStuck()) {
                    sleep(4000);
                    follower.holdPoint(toCapture2Spiceman);
                    follower.followPath(toSpiceManPC,true);
                    sample.setPower(0.71);
                    case3.start();
                    setPathState(4);
                }
                break;
            case 4:
                if(!follower.isBusy() && (follower.getPose().getX() > (toSpiecman.getX() - tolerance) && follower.getPose().getY() > (toSpiecman.getY() - tolerance) && follower.getPose().getHeading() > (toSpiecman.getHeading() - tolerance))|| follower.isRobotStuck()) {
                    follower.holdPoint(toSpiecman);
                    verticalSlider.podvesSpiecMan();
                    follower.followPath(toParkPC,true);
                    case4.start();
                    setPathState(5);
                }
                break;
            case 5:
                if(!follower.isBusy() && (follower.getPose().getX() > (toPark.getX() - tolerance) && follower.getPose().getY() > (toPark.getY() - tolerance) && follower.getPose().getHeading() > (toPark.getHeading() - tolerance))|| follower.isRobotStuck()) {
                    follower.holdPoint(toPark);
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
