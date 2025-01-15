package org.firstinspires.ftc.teamcode.own.Utils;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.ArrayList;
import java.util.List;

import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;
import org.firstinspires.ftc.teamcode.own.positions.*;

public class PedroUtil {
    List<AutonomousPositions.AutonomousPosition> autonomousPositionList = new ArrayList<>();

    public void setHorizontSlider(HorizontSlider horizontSlider) {
        this.horizontSlider = horizontSlider;
    }

    public void setVerticalSlider(VerticalSlider verticalSlider) {
        this.verticalSlider = verticalSlider;
    }

    public void setZx(Zx zx) {
        this.zx = zx;
    }

    private Follower follower;
    private LinearOpMode opMode;
    public int pathState = 0;
    private Timer pathTimer, actionTimer;
    public PedroUtil(Follower follower, LinearOpMode opMode) {
        this.follower = follower;
        this.opMode = opMode;
    }
    HorizontSlider horizontSlider;
    VerticalSlider verticalSlider;
    Zx zx;

    public final Pose startPose = new Pose(134.47662485746864, 75.53021664766247, Math.toRadians(0));
    final Pose toSpiecman = new Pose(109.6830102622577, 72.73888255416192, Math.toRadians(0));
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

    PathChain toSpiecmanPC, toParkPC, toBucketPC, to1SamplePC, to2SamplePC, to3SamplePC, toPark2PC;


    public Zx getZx() {
        return zx;
    }

    public VerticalSlider getVerticalSlider() {
        return verticalSlider;
    }

    public HorizontSlider getHorizontSlider() {
        return horizontSlider;
    }

    public void initPedro(){
        pathTimer = new Timer();
        if (zx != null){
            zx.init();
        }
        if (verticalSlider != null){
            verticalSlider.init();
        }
        if (horizontSlider != null){
            horizontSlider.init();
        }
        pathBuilding();
    }
    public void pathBuilding(){
        toSpiecmanPC = follower.pathBuilder().addPath(new BezierLine(
                new Point(startPose),
                new Point(toSpiecman)
        )).setConstantHeadingInterpolation(Math.toRadians(0)).build();
        toParkPC = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Point(toSpiecman),
                        new Point(toPark)
                )).setLinearHeadingInterpolation(toSpiecman.getHeading(), toPark.getHeading()).build();
        toBucketPC = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(startPose),
                                new Point(toBucket)
                        )
                ).setLinearHeadingInterpolation(startPose.getHeading(), toBucket.getHeading()).build();
        to1SamplePC = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(toBucket),
                                new Point(to1SampleControl),
                                new Point(to1Sample)
                        )
                ).setLinearHeadingInterpolation(toBucket.getHeading(), to1Sample.getHeading()).build();
        to2SamplePC = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(toBucket),
                                new Point(to2SampleControl),
                                new Point(to1Sample)
                        )
                ).setLinearHeadingInterpolation(toBucket.getHeading(), to2Sample.getHeading()).build();
        to3SamplePC = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(toBucket),
                                new Point(to3SampleControl),
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
            case -1:
                opMode.sleep(300000);
            case 0: // Move from start to scoring position
                follower.followPath(toSpiecmanPC);
                setPathState(1);
                break;

            case 1: // Wait until the robot is near the scoring position
                if (!follower.isBusy() && (follower.getPose().getX() > (toSpiecman.getX() - 1) && follower.getPose().getY() > (toSpiecman.getY() - 1))) {
                    follower.followPath(toParkPC, true);
                    setPathState(-1);
                }
                break;
        }
    }
    public void trajectory2() {
        switch (pathState) {

            case 0:
                follower.followPath(toSpiecmanPC);
                setPathState(1);
                break;
            case 1:
                if (!follower.isBusy() && (follower.getPose().getX() > (toSpiecman.getX() - 1) && follower.getPose().getY() > (toSpiecman.getY() - 1))) {
                    follower.followPath(toPark2PC, true);
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
