package org.firstinspires.ftc.teamcode.own.actions.utilactions;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class PathBuilderACtion implements Action {
    public PathChain Path1;
    @Override
    public void execute() throws InterruptedException {
        if (Robot.INSTANCE.opMode.opModeIsActive()){
            Path1 = Robot.INSTANCE.follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(20.878, 123.434),

                                    new Pose(56.000, 84.000)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(143))

                    .build();
            Robot.INSTANCE.addData("path1", Path1);
        }
    }
}
