package org.firstinspires.ftc.teamcode.pedroPathing.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

public class MiddlePaths {
    public Follower follower;

    public MiddlePaths(Follower follower) {
        this.follower = follower;
    }

    public PathChain toBucket, toSpiceMan, toFirstSample, toSecondSample;
    public PathBuilder builder = new PathBuilder();
    public void buildMiddlePaths(){

        toSpiceMan = builder
                .addPath(
                        // Line 1
                        new BezierLine(
                                new Point(follower.getPose()),
                                new Point(38.500, 72.000, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-180))
                .build();
        toBucket = builder
                .addPath(
                        // Line 1
                        new BezierLine(
                                new Point(follower.getPose()),
                                new Point(13.754, 128.589, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-45))
                .build();
        toFirstSample = builder
                .addPath(
                        // Line 1
                        new BezierCurve(
                                new Point(follower.getPose()),
                                new Point(4.474, 117.818, Point.CARTESIAN),
                                new Point(23.696, 119.972, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
        toSecondSample =  builder
                .addPath(
                        // Line 1
                        new BezierCurve(
                                new Point(follower.getPose()),
                                new Point(20.051, 111.687, Point.CARTESIAN),
                                new Point(24.359, 129.915, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(0))
                .build();

    }
}
