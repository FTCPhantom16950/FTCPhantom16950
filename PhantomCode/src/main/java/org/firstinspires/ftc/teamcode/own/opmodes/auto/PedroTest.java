package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathBuilder;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;
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
        follower = new Follower(hardwareMap);
        follower.setMaxPower(0.3);
        waitForStart();
        if (opModeIsActive()){
            follower.followPath(to90, true);
            follower.update();
            sleep(30000);
        }
    }
}
