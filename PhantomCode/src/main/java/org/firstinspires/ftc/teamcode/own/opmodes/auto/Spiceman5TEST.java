package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import static org.firstinspires.ftc.teamcode.own.Utils.Config.tolerance;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toBucket;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toCapture1SpicemanPC;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toGetSpiceman;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toGetSpicemanPC;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toReturnPC;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSetSpiceman;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSetSpiceman2_5PC;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSetSpicemanPC;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman1;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman1Control1;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman1Control2;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman1Control3;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman1Stop;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman2;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman2Control1;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman2Stop;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman3;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman3Control1;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman3Stop;

import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew;
import org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@Autonomous(name = "Spiceman5Test")
public class Spiceman5TEST extends LinearOpMode {
    VerticalSlider verticalSlider = new VerticalSlider(this);
    HorizontSlider horizontSlider = new HorizontSlider(this);
    Zxnew zx = new Zxnew(this);
    Follower follower;
    int pathState = 0;
    Timer pathTimer;
    int counter = 0;

    public void pathBuilding() {
        toSetSpicemanPC = follower.pathBuilder().addPath(
                new BezierCurve(
                        POINTSPEDRO.startPosetoSpiceman,
                        toSetSpiceman
                )
        ).setConstantHeadingInterpolation(toSetSpiceman.getHeading())
        .build();
        POINTSPEDRO.to3SpicemansPC = follower.pathBuilder().addPath(
                new BezierCurve(
                        toSetSpiceman,
                        toSpiceman1,
                        toSpiceman1Control1,
                        toSpiceman1Control2,
                        toSpiceman1Control3
                )
        ).setLinearHeadingInterpolation(toSetSpiceman.getHeading(), toSpiceman1.getHeading())
                .addPath(
                        new BezierCurve(
                                toSpiceman1,
                                toSpiceman1Stop
                        )
                ).setConstantHeadingInterpolation(toSpiceman1Stop.getHeading())
                .addPath(
                        new BezierCurve(
                                toSpiceman1Stop,
                                toSpiceman2,
                                toSpiceman2Control1
                        )
                ).setConstantHeadingInterpolation(toSpiceman2.getHeading())
                .addPath(
                        new BezierCurve(
                                toSpiceman2,
                                toSpiceman2Stop
                        )
                ).setConstantHeadingInterpolation(toSpiceman2Stop.getHeading())
                .addPath(
                        new BezierCurve(
                                toSpiceman2Stop,
                                toSpiceman3,
                                toSpiceman3Control1
                        )
                ).setConstantHeadingInterpolation(toSpiceman3.getHeading())
                .addPath(
                        new BezierCurve(
                                toSpiceman3,
                                toSpiceman3Stop
                        )
                ).setConstantHeadingInterpolation(toSpiceman3Stop.getHeading())
                .build();
        toCapture1SpicemanPC = follower.pathBuilder().addPath(
                new BezierCurve(
                        toSpiceman3Stop,
                        toSpiceman1Stop
                )
        ).build();
        toGetSpicemanPC = follower.pathBuilder().addPath(
                new BezierCurve(
                    toSpiceman1Stop,
                        toGetSpiceman
                )
        ).setConstantHeadingInterpolation(toGetSpiceman.getHeading())
                .build();
        toSetSpiceman2_5PC = follower.pathBuilder().addPath(
                new BezierCurve(
                        toGetSpiceman,
                        toSetSpiceman
                )
        ).setLinearHeadingInterpolation(toGetSpiceman.getHeading(), toSetSpiceman.getHeading())
                .build();
        toReturnPC = follower.pathBuilder().addPath(
                new BezierCurve(
                        toSetSpiceman,
                        toSpiceman1Stop
                )
        ).setLinearHeadingInterpolation(toSetSpiceman.getHeading(), POINTSPEDRO.startPosetoSpiceman.getHeading())
                .build();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        Constants.setConstants(FConstants.class, LConstants.class);
        pathTimer = new Timer();
        follower = new Follower(this.hardwareMap, FConstants.class, LConstants.class);
        follower.setStartingPose(POINTSPEDRO.startPosetoSpiceman);
        follower.update();
        verticalSlider.init();
        horizontSlider.init();
        zx.init();
        pathBuilding();
        waitForStart();
        while (opModeIsActive()){
            telemetry.addData("pathState", pathState);
            telemetry.update();
            follower.update();
            zx.play();
            zx.play1();
            horizontSlider.play();
            verticalSlider.play();
            spicemanTrajectory();
        }
    }
    public void spicemanTrajectory() {
        switch (pathState){
            case 0:
                follower.followPath(toSetSpicemanPC);
                setPathState(1);
                break;
            case 1:
                if (!follower.isBusy() && (follower.getPose().getX() > (toSetSpiceman.getX() - tolerance) && follower.getPose().getY() > (toSetSpiceman.getY() - tolerance))|| follower.isRobotStuck()) {
                    follower.followPath(POINTSPEDRO.to3SpicemansPC);
                    setPathState(2);
                }
                break;
            case 2:
                if (!follower.isBusy() && (follower.getPose().getX() > (toSpiceman3Stop.getX() - tolerance) && follower.getPose().getY() > (toSpiceman3Stop.getY() - tolerance))|| follower.isRobotStuck()) {
                    follower.followPath(POINTSPEDRO.toCapture1SpicemanPC);
                    setPathState(3);
                }
                break;
            case 3:
                if (!follower.isBusy() && (follower.getPose().getX() > (toSpiceman1Stop.getX() - tolerance) && follower.getPose().getY() > (toSpiceman1Stop.getY() - tolerance))|| follower.isRobotStuck()) {
                    follower.followPath(POINTSPEDRO.toGetSpicemanPC);
                    setPathState(4);
                }
                break;
            case 4:
                if (!follower.isBusy() && (follower.getPose().getX() > (toGetSpiceman.getX() - tolerance) && follower.getPose().getY() > (toGetSpiceman.getY() - tolerance))|| follower.isRobotStuck()) {
                    follower.followPath(POINTSPEDRO.toSetSpiceman2_5PC);
                    setPathState(5);
                }
                break;
            case 5:
                if (!follower.isBusy() && (follower.getPose().getX() > (toSetSpiceman.getX() - tolerance) && follower.getPose().getY() > (toSetSpiceman.getY() - tolerance))|| follower.isRobotStuck()) {
                    follower.followPath(POINTSPEDRO.toReturnPC);
                    if (counter < 4) {
                        setPathState(6);
                    } else {
                        setPathState(-1);
                    }
                }
                break;
            case 6:
                if (!follower.isBusy() && (follower.getPose().getX() > (toSpiceman1Stop.getX() - tolerance) && follower.getPose().getY() > (toSpiceman1Stop.getY() - tolerance))|| follower.isRobotStuck()) {
                    follower.followPath(toGetSpicemanPC);
                    setPathState(7);
                }
                break;
            case 7:
                if (!follower.isBusy() && (follower.getPose().getX() > (toGetSpiceman.getX() - tolerance) && follower.getPose().getY() > (toGetSpiceman.getY() - tolerance))|| follower.isRobotStuck()) {
                    follower.followPath(toSetSpiceman2_5PC);
                    counter++;
                    setPathState(5);
                }
                break;
                
        }
            
    }
    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }
    
}
