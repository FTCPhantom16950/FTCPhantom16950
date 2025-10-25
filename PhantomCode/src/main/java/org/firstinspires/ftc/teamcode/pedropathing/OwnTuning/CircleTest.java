//package org.firstinspires.ftc.teamcode.pedropathing.OwnTuning;
//
//import static org.firstinspires.ftc.teamcode.own.Mechanism.FollowerMechanism.follower;
//import static org.firstinspires.ftc.teamcode.pedropathing.OwnTuning.Drawing.drawNow;
//import static org.firstinspires.ftc.teamcode.pedropathing.OwnTuning.TuningActions.CircleAction.RADIUS;
//import static org.firstinspires.ftc.teamcode.pedropathing.OwnTuning.TuningActions.CircleAction.circle;
//
//
//import com.acmerobotics.dashboard.config.Config;
//import com.pedropathing.geometry.BezierCurve;
//import com.pedropathing.geometry.Pose;
//import com.pedropathing.paths.HeadingInterpolator;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.own.Mechanism.FollowerMechanism;
//import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup;
//import org.firstinspires.ftc.teamcode.own.Utils.PhantomLogger;
//import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
//import org.firstinspires.ftc.teamcode.pedropathing.OwnTuning.TuningActions.CentripertalAction;
//import org.firstinspires.ftc.teamcode.pedropathing.OwnTuning.TuningActions.CircleAction;
//import org.firstinspires.ftc.teamcode.pedropathing.Tuning;
//
//@Config
//@TeleOp
//public class CircleTest extends PhantomOpMode {
//    public static double DISTANCE = 20;
//    CircleAction action;
//
//
//
//    @Override
//    public void customOpModeSettings() {
//        mechanism.add(new FollowerMechanism(this.hardwareMap));
//        action = new CircleAction(this);
//        actions = new ParallelGroup(this, action);
//        PhantomLogger.addData("This will run in a roughly circular shape of radius " + RADIUS + ", starting on the right-most edge. ", true);
//        PhantomLogger.addData("So, make sure you have enough space to the left, front, and back to run the OpMode.", true);
//        PhantomLogger.addData("It will also continuously face the center of the circle to test your heading and centripetal correction.", true);
//        follower.update();
//        drawNow();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        circle = Tuning.follower.pathBuilder()
//                .addPath(new BezierCurve(new Pose(0, 0), new Pose(RADIUS, 0), new Pose(RADIUS, RADIUS)))
//                .setHeadingInterpolation(HeadingInterpolator.facingPoint(0, RADIUS))
//                .addPath(new BezierCurve(new Pose(RADIUS, RADIUS), new Pose(RADIUS, 2 * RADIUS), new Pose(0, 2 * RADIUS)))
//                .setHeadingInterpolation(HeadingInterpolator.facingPoint(0, RADIUS))
//                .addPath(new BezierCurve(new Pose(0, 2 * RADIUS), new Pose(-RADIUS, 2 * RADIUS), new Pose(-RADIUS, RADIUS)))
//                .setHeadingInterpolation(HeadingInterpolator.facingPoint(0, RADIUS))
//                .addPath(new BezierCurve(new Pose(-RADIUS, RADIUS), new Pose(-RADIUS, 0), new Pose(0, 0)))
//                .setHeadingInterpolation(HeadingInterpolator.facingPoint(0, RADIUS))
//                .build();
//        Tuning.follower.followPath(circle);
//    }
//}