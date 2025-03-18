package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toCapture1SpicemanPC;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toGetSpiceman;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toGetSpicemanPC;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toReturnPC;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSetSpiceman;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSetSpiceman2_5PC;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman1;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman1Control1;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman1Control2;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman1Stop;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman2;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman2Control1;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman2Stop;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman3;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman3Control1;
import static org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSpiceman3Stop;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.BezierCurve;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew;
import org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;
@Autonomous
public class Spiceman5 extends PhantomOpMode {
    VerticalSlider verticalSlider = new VerticalSlider(this);
    HorizontSlider horizontalSlider = new HorizontSlider(this);
    Zxnew zxnew = new Zxnew(this);
    MultipleTelemetry telemetry = new MultipleTelemetry(this.telemetry, this.telemetry);
    @Override
    public void afterWaitForStart() {

    }

    @Override
    public void initMechanism() {
        follower = new Follower(this.hardwareMap, FConstants.class, LConstants.class);
        follower.setStartingPose(POINTSPEDRO.startPosetoSpiceman);
        verticalSlider.init();
        horizontalSlider.init();
        zxnew.init();
        pathBuilding();
        beforePTLinear();
        beforePTParallel(new VerticalSlider.SetAutoAction(0),
                new VerticalSlider.SlozAutoAction(1),
                new VerticalSlider.SlozAutoAction(6),
                new VerticalSlider.SlozAutoAction(9),
                new VerticalSlider.SlozAutoAction(12),
                new VerticalSlider.SlozAutoAction(15));
        afterPTLinear(new VerticalSlider.SetAutoAction(0),
                new VerticalSlider.SetAutoAction(5),
                new VerticalSlider.SetAutoAction(8),
                new VerticalSlider.SetAutoAction(11),
                new VerticalSlider.SetAutoAction(14),
                new VerticalSlider.SetAutoAction(17));
        afterPTParallel();
    }


    @Override
    public void play() {
            pedroFollowOpMode(follower, POINTSPEDRO.toSetSpicemanPC, POINTSPEDRO.to3SpicemansPC, toCapture1SpicemanPC, toGetSpicemanPC, toSetSpiceman2_5PC,
                    toReturnPC, toGetSpicemanPC, toSetSpiceman2_5PC,
                    toReturnPC, toGetSpicemanPC, toSetSpiceman2_5PC,
                    toReturnPC, toGetSpicemanPC, toSetSpiceman2_5PC,
                    toReturnPC, toGetSpicemanPC, toSetSpiceman2_5PC);
    }

    @Override
    public void telemetryDebug() {
        follower.telemetryDebug(telemetry);
    }


    @Override
    public void pathBuilding() {
        POINTSPEDRO.toSetSpicemanPC = follower.pathBuilder().addPath(
                new BezierCurve(
                        POINTSPEDRO.startPosetoSpiceman,
                        toSetSpiceman
                )
        ).setLinearHeadingInterpolation(POINTSPEDRO.startPosetoSpiceman.getHeading(), toSetSpiceman.getHeading())
        .build();
        POINTSPEDRO.to3SpicemansPC = follower.pathBuilder().addPath(
                new BezierCurve(
                        toSetSpiceman,
                        toSpiceman1,
                        toSpiceman1Control1,
                        toSpiceman1Control2
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
}
