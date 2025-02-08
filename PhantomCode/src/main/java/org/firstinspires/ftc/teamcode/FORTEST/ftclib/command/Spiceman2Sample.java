package org.firstinspires.ftc.teamcode.FORTEST.ftclib.command;

import static org.firstinspires.ftc.teamcode.FORTEST.ftclib.SYS.WheelBaseSYS.follower;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.pedropathing.commands.FollowPath;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;

import org.firstinspires.ftc.teamcode.FORTEST.ftclib.SYS.VerticalSliderSYS;
import org.firstinspires.ftc.teamcode.FORTEST.ftclib.SYS.WheelBaseSYS;
import org.firstinspires.ftc.teamcode.FORTEST.ftclib.SYS.ZXSYS;

public class Spiceman2Sample extends SequentialCommandGroup {

    public final Pose startPose = new Pose(134.47662485746864, 75.53021664766247, Math.toRadians(0));
    final Pose toSpiecman = new Pose(106.8, 71, Math.toRadians(0));
    final Pose toPark = new Pose(131.0285062713797, 133.1630558722919, Math.toRadians(90));
    final Pose toBucket = new Pose(128.6365280289331,19.790235081374327, Math.toRadians(135));
    final Pose to1Sample = new Pose(119.26220614828209, 23.43580470162748, Math.toRadians(180));
    final Pose to1SampleControl = new Pose(142.95840867992766, 21.09222423146474);
    final Pose to1SampleEnd = new Pose(108, 24.73779385171791, Math.toRadians(180));
    final Pose to2Sample = new Pose(114.5750452079566, 15.88426763110307, Math.toRadians(180));
    final Pose to2SampleControl = new Pose(118.48101265822785, 27.8625678119349);
    final Pose to2SampleEnd = new Pose(108.32549728752261,15.88426763110307,Math.toRadians(180));
    final Pose to3Sample = new Pose(115.59407069555303, 10.672748004560997, Math.toRadians(210));
    final Pose to3SampleControl = new Pose(117.40022805017104, 31.03306727480046);
    final Pose toPark2 = new Pose(80.20253164556962,47.132007233273065, Math.toRadians(270));
    final Pose ToPark2Control = new Pose(70.56781193490055,8.59312839059675);
    @Override
    public void initialize() {
        super.initialize();
        pathBuilding();
    }
    PathChain toSpiecmanPC, toParkPC, toBucketPC, to1SamplePC, to2SamplePC, to3SamplePC, toPark2PC,toBucketPCthird,toBucketPCfirst,toBucketPCsecond;

    public Spiceman2Sample(VerticalSliderSYS verticalSliderSYS, WheelBaseSYS wheelBaseSYS, ZXSYS zxsys) {
       addCommands(
               new ParallelCommandGroup(
                       new VeshSpiceman(verticalSliderSYS),
                       new FollowPath(follower, toSpiecmanPC,true)
               ),
               new ParallelCommandGroup(
                       new VeshSpicemanDown(verticalSliderSYS),
                       new FollowPath(follower, toBucketPCfirst, true)
               ),
               new ZaxvatSample(zxsys),
               new FollowPath(follower, toBucketPCsecond, true),
               new BucketUP(verticalSliderSYS),
               new ParallelCommandGroup(
                       new BucketDown(verticalSliderSYS),
                       new FollowPath(follower, to2SamplePC, true)
               ),
               new FollowPath(follower, toBucketPCsecond, true),
               new ParallelCommandGroup(
                       new FollowPath(follower, toPark2PC, true),
                       new SequentialCommandGroup(
                               new BucketDown(verticalSliderSYS),
                               new Park2(verticalSliderSYS)
                       )
               )
       );
       addRequirements(verticalSliderSYS,wheelBaseSYS,zxsys);
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
                .addPath(
                        new BezierLine(
                                new Point(to1Sample),
                                new Point(to1SampleEnd)
                        )
                ).build();
        to2SamplePC = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(toBucket),

                                new Point(to2Sample)
                        )
                )
                .setLinearHeadingInterpolation(toBucket.getHeading(), to2Sample.getHeading()).build();
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
                )).setLinearHeadingInterpolation(toBucket.getHeading(), toPark2.getHeading()).setPathEndTimeoutConstraint(1500).build();
    }
}
