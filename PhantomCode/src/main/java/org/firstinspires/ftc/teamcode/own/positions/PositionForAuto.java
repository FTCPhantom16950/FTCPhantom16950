package org.firstinspires.ftc.teamcode.own.positions;

import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Timer;

public class PositionForAuto {
    public static final Pose startPose = new Pose(134.47662485746864, 75.53021664766247, Math.toRadians(0));
    public static final Pose toSpiecman = new Pose(106.8, 71, Math.toRadians(0));
    public static final Pose toPark = new Pose(131.0285062713797, 133.1630558722919, Math.toRadians(90));
    public static final Pose toBucket = new Pose(128.6365280289331,19.790235081374327, Math.toRadians(135));
    public static final Pose to1Sample = new Pose(118, 24, Math.toRadians(180));
    public static final Pose to1SampleControl = new Pose(142.95840867992766, 21.09222423146474);
    public static final Pose to2Sample = new Pose(118, 15.88426763110307, Math.toRadians(180));
    public static final Pose to2SampleControl = new Pose(118.48101265822785, 27.8625678119349);
    public static final Pose to2SampleEnd = new Pose(108.32549728752261,15.88426763110307,Math.toRadians(180));
    public static final Pose to3Sample = new Pose(115.59407069555303, 10.672748004560997, Math.toRadians(210));
    public static final Pose to3SampleControl = new Pose(117.40022805017104, 31.03306727480046);
    public static final Pose toPark2 = new Pose(80.20253164556962,47.132007233273065, Math.toRadians(270));
    public static final Pose ToPark2Control = new Pose(70.56781193490055,8.59312839059675);
    public static Timer pathTimer, actionTimer;
    public static PathChain toSpiecmanPC, toParkPC, toBucketPC, to1SamplePC, to2SamplePC, to3SamplePC, toPark2PC,toBucketPCthird,toBucketPCfirst,toBucketPCsecond;
    public static void pathBuilding(PathBuilder pathBuilder){
        PositionForAuto.toSpiecmanPC = pathBuilder.addPath(new BezierLine(
                new Point(startPose),
                new Point(toSpiecman)
        )).setConstantHeadingInterpolation(Math.toRadians(0)).build();
        PositionForAuto.toParkPC = pathBuilder
                .addPath(new BezierLine(
                        new Point(toBucket),
                        new Point(toPark)
                )).setLinearHeadingInterpolation(toSpiecman.getHeading(), toPark.getHeading()
                ).setPathEndTimeoutConstraint(1500).build();
        PositionForAuto.toBucketPC = pathBuilder
                .addPath(
                        new BezierLine(
                                new Point(startPose),
                                new Point(toBucket)
                        )
                ).setLinearHeadingInterpolation(startPose.getHeading(), toBucket.getHeading())
                .setPathEndTimeoutConstraint(1000).build();
        PositionForAuto.toBucketPCfirst = pathBuilder
                .addPath(
                        new BezierLine(
                                new Point(to1Sample),
                                new Point(toBucket)
                        )
                ).setLinearHeadingInterpolation(to1Sample.getHeading(), toBucket.getHeading()).build();
        PositionForAuto.toBucketPCsecond = pathBuilder
                .addPath(
                        new BezierLine(
                                new Point(to2Sample),
                                new Point(toBucket)
                        )
                ).setLinearHeadingInterpolation(to2Sample.getHeading(), toBucket.getHeading()).build();
        PositionForAuto.toBucketPCthird = pathBuilder
                .addPath(
                        new BezierLine(
                                new Point(to3Sample),
                                new Point(toBucket)
                        )
                ).setLinearHeadingInterpolation(to3Sample.getHeading(), toBucket.getHeading()).build();
        PositionForAuto.to1SamplePC = pathBuilder
                .addPath(
                        new BezierCurve(
                                new Point(toSpiecman),
                                new Point(to1SampleControl),
                                new Point(to1Sample)
                        )
                ).setLinearHeadingInterpolation(toSpiecman.getHeading(), to1Sample.getHeading())
                .build();
        PositionForAuto.to2SamplePC = pathBuilder
                .addPath(
                        new BezierCurve(
                                new Point(toBucket),
                                new Point(to2SampleControl),
                                new Point(to2Sample)
                        )
                ).setLinearHeadingInterpolation(toBucket.getHeading(), to2Sample.getHeading()).build();
        PositionForAuto.to3SamplePC = pathBuilder
                .addPath(
                        new BezierLine(
                                new Point(toBucket),
                                new Point(to3Sample)
                        )
                ).setLinearHeadingInterpolation(toBucket.getHeading(), to3Sample.getHeading())
                .build();
        PositionForAuto.toPark2PC = pathBuilder
                .addPath(new BezierCurve(
                        new Point(toBucket),
                        new Point(ToPark2Control),
                        new Point(toPark2)
                )).setLinearHeadingInterpolation(toBucket.getHeading(), toPark2.getHeading()).setPathEndTimeoutConstraint(1500).build();
    }
}
