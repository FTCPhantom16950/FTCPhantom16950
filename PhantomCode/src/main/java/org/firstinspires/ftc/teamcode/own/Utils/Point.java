package org.firstinspires.ftc.teamcode.own.Utils;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathBuilder;
import com.pedropathing.paths.PathChain;

public class Point {
    static PathBuilder pathBuilder;

    public Point(PathBuilder pathBuilder) {
        this.pathBuilder = pathBuilder;
    }
    public static PathChain start = pathBuilder
            .addPath(
                    // Path 1
                    new BezierLine(new Pose(60.152, 4.166), new Pose(40.362, 29.165))
            )
            .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
            .build();
    public static PathChain firstToStart = pathBuilder
            .addPath(
                    // Path 2
                    new BezierLine(new Pose(40.362, 29.165), new Pose(61.193, 4.166))
            )
            .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(56))
            .build();
    public static PathChain startToSecond = pathBuilder
            .addPath(
                    // Path 3
                    new BezierLine(new Pose(61.193, 4.166), new Pose(42.184, 54.684))
            )
            .setLinearHeadingInterpolation(Math.toRadians(56), Math.toRadians(180))
            .build();
    public static PathChain secondToEnd = pathBuilder
            .addPath(
                // Path 4
                    new BezierLine(new Pose(42.184, 54.684), new Pose(50.778, 93.222))
            )
            .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(37))
            .build();
    public static PathChain endToThird = pathBuilder
            .addPath(
                    // Path 5
                    new BezierLine(new Pose(50.778, 93.222), new Pose(40.882, 80.984))
            )
            .setLinearHeadingInterpolation(Math.toRadians(37), Math.toRadians(180))
            .build();
    public static PathChain thirdToEnd = pathBuilder
            .addPath(
                    // Path 6
                    new BezierLine(new Pose(40.882, 80.984), new Pose(49.996, 95.566))
            )
            .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(38))
            .build();
}
