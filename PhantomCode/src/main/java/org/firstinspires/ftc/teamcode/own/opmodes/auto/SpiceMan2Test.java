package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.ds;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.pod;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.sample;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrash;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.KRUT_2_START_POWER;
import static org.firstinspires.ftc.teamcode.own.Utils.Config.tolerance;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@Autonomous
/// тестовый автоном с траекторией для 2 спишмэнов
public class SpiceMan2Test extends PhantomOpMode {
    Follower follower;
    ElapsedTime timer = new ElapsedTime();
    public int pathState = 0;
    // прописываем позиции

    public final Pose initialPose = new Pose(134.47662485746864, 75.53021664766247, Math.toRadians(0));
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
    PathChain toSpiceManPC, toSecondSample, toSecondSpiceman, toCapture, toSpiceman2, toParkPC;
    HorizontSlider horizontSlider = new HorizontSlider(this);
    VerticalSlider verticalSlider = new VerticalSlider(this);
    Zx zx = new Zx(this);
    private final double VRASH_POWER = -0.49;

    @Override
    public void afterWaitForStart() {

    }

    @Override
    public void initMechanism() {
        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        horizontSlider.init();
        verticalSlider.init();
        zx.init();
        Zx.brat.setPower(KRUT_2_START_POWER);
        Zx.brat2.setPower(KRUT_2_START_POWER);
        follower.setStartingPose(initialPose);
        vrash.setPower(VRASH_POWER );
        pathBuilding();
    }

    @Override
    public void play() {
        follower.update();
        zx.play();
        zx.play1();
        horizontSlider.play();
        verticalSlider.play();
        trajectory();
        follower.telemetryDebug(telemetry);
        telemetry.update();
    }

    @Override
    public void autoActions() {

    }

    @Override
    public void telemetryDebug() {

    }

    // производим постройку всех траекторий
    public void pathBuilding() {
        toSpiceManPC = follower.pathBuilder()
                .addPath(
                new BezierCurve(
                        new Point(initialPose),
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
    @Override
    public void trajectory() {
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

}
