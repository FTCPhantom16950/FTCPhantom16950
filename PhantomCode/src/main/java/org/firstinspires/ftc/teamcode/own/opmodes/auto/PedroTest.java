package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@Autonomous
@Disabled
public class PedroTest extends LinearOpMode {
    HorizontSlider horizontSlider = new HorizontSlider(this);
    Follower follower;
    PathBuilder builder = new PathBuilder();


    @Override
    public void runOpMode() throws InterruptedException {
        PathChain to90 = builder
                .addPath(
                        // Line 1
                        new BezierLine(
                                new Point(75.000, 75.000, Point.CARTESIAN),
                                new Point(75.000, 75.000, Point.CARTESIAN)
                        )
                )
                .setTangentHeadingInterpolation()
                .addPath(
                        // Line 2
                        new BezierLine(
                                new Point(75.000, 75.000, Point.CARTESIAN),
                                new Point(75.000, 113.000, Point.CARTESIAN)
                        )
                )
                .setTangentHeadingInterpolation().build();
        horizontSlider.init();
        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);
        follower.setMaxPower(0.3);
        waitForStart();
        if (opModeIsActive()){
            follower.followPath(to90, true);
            follower.update();
            sleep(30000);
        }
    }
}
