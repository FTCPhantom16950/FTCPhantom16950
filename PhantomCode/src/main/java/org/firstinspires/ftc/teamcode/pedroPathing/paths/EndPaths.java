package org.firstinspires.ftc.teamcode.pedroPathing.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;

public class EndPaths {
    public Follower follower;

    public EndPaths(Follower follower) {
        this.follower = follower;
    }

    public PathChain toPark1fromBUCKET, toPark1fromSPICEMAN, toPark2fromBucket, toPark2fromSPICEMAN;
    public PathBuilder builder = new PathBuilder();
    public void buildMiddlePaths(){
        toPark1fromBUCKET = builder
                .addPath(
                        // Line 1
                        new BezierCurve(
                                new Point(13.754, 128.589, Point.CARTESIAN),
                                new Point(21.873, 96.608, Point.CARTESIAN),
                                new Point(62.000, 95.000, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(90))
                .build();
        toPark1fromSPICEMAN = builder
                .addPath(
                        // Line 1
                        new BezierCurve(
                                new Point(37.284, 80.700, Point.CARTESIAN),
                                new Point(10.274, 107.710, Point.CARTESIAN),
                                new Point(62.000, 96.000, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(90))
                .build();
        toPark2fromBucket = builder
                .addPath(
                        // Line 1
                        new BezierCurve(
                                new Point(13.754, 128.589, Point.CARTESIAN),
                                new Point(47.061, 124.446, Point.CARTESIAN),
                                new Point(39.604, 5.137, Point.CARTESIAN),
                                new Point(8.451, 8.285, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(0))
                .build();
        toPark2fromSPICEMAN = builder
                .addPath(
                        // Line 1
                        new BezierCurve(
                                new Point(36.621, 72.083, Point.CARTESIAN),
                                new Point(36.621, 38.776, Point.CARTESIAN),
                                new Point(40.101, 3.148, Point.CARTESIAN),
                                new Point(5.965, 9.445, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                .build();

    }
}
