//package org.firstinspires.ftc.teamcode.pedropathing.OwnTuning.TuningActions;
//
//import static org.firstinspires.ftc.teamcode.pedropathing.OwnTuning.CentripetalTuner.DISTANCE;
//import static org.firstinspires.ftc.teamcode.pedropathing.Tuning.drawCurrentAndHistory;
//import static org.firstinspires.ftc.teamcode.pedropathing.Tuning.follower;
//
//import com.pedropathing.geometry.BezierCurve;
//import com.pedropathing.geometry.Pose;
//import com.pedropathing.paths.Path;
//
//import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
//import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
//
//public class CentripertalAction extends Action {
//    PhantomOpMode opMode;
//    public CentripertalAction(PhantomOpMode opMode) {
//        super(opMode);
//        this.opMode= opMode;
//        forwards = new Path(new BezierCurve(new Pose(), new Pose(Math.abs(DISTANCE),0), new Pose(Math.abs(DISTANCE),DISTANCE)));
//        backwards = new Path(new BezierCurve(new Pose(Math.abs(DISTANCE),DISTANCE), new Pose(Math.abs(DISTANCE),0), new Pose(0,0)));
//        backwards.setTangentHeadingInterpolation();
//        backwards.reverseHeadingInterpolation();
//    }
//    private boolean forward = true;
//    public static Path forwards;
//    private final Path backwards;
//    @Override
//    public void execute() {
//        while (opMode.opModeIsActive()){
//            follower.update();
//            drawCurrentAndHistory();
//            if (!follower.isBusy()) {
//                if (forward) {
//                    forward = false;
//                    follower.followPath(backwards);
//                } else {
//                    forward = true;
//                    follower.followPath(forwards);
//                }
//            }
//
//        }
//    }
//}
