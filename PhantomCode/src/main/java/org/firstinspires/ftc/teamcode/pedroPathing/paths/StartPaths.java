package org.firstinspires.ftc.teamcode.pedroPathing.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

public class StartPaths {
    public PathChain toBucket, toSpiceMan;
    public PathBuilder builder = new PathBuilder();
    public void buildStartPaths(){

        toSpiceMan = builder
                .addPath(
                        // Line 1
                        new BezierLine(
                                new Point(7.800, 70.000, Point.CARTESIAN),
                                new Point(38.500, 72.000, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-180))
                .build();
        toBucket = builder
                .addPath(
                        // Line 1
                        new BezierLine(
                                new Point(9.000, 97.000, Point.CARTESIAN),
                                new Point(13.754, 128.589, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-45))
                .build();

    }
}
