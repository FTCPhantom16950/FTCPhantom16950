//package org.firstinspires.ftc.teamcode.pedropathing.OwnTuning.TuningActions;
//
//import static org.firstinspires.ftc.teamcode.pedropathing.OwnTuning.Drawing.drawNow;
//import static org.firstinspires.ftc.teamcode.pedropathing.Tuning.drawCurrentAndHistory;
//import static org.firstinspires.ftc.teamcode.pedropathing.Tuning.follower;
//
//import com.pedropathing.paths.PathChain;
//
//import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
//import org.firstinspires.ftc.teamcode.own.Utils.PhantomLogger;
//import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
//
//public class CircleAction extends Action {
//    PhantomOpMode opMode;
//    public static double RADIUS = 10;
//    public static PathChain circle;
//    public CircleAction(PhantomOpMode opMode) {
//        super(opMode);
//        this.opMode = opMode;
//    }
//
//    @Override
//    public void execute() {
//        while (opMode.opModeIsActive()){
//            follower.update();
//            drawNow();
//
//            if (follower.atParametricEnd()) {
//                follower.followPath(circle);
//            }
//        }
//    }
//}
