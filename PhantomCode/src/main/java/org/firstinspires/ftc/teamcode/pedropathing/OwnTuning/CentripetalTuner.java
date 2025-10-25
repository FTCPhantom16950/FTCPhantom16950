//package org.firstinspires.ftc.teamcode.pedropathing.OwnTuning;
//
//import static org.firstinspires.ftc.teamcode.own.Mechanism.FollowerMechanism.follower;
//import static org.firstinspires.ftc.teamcode.pedropathing.OwnTuning.Drawing.drawNow;
//import static org.firstinspires.ftc.teamcode.pedropathing.OwnTuning.TuningActions.CentripertalAction.forwards;
//
//
//import com.acmerobotics.dashboard.config.Config;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.own.Mechanism.FollowerMechanism;
//import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup;
//import org.firstinspires.ftc.teamcode.own.Utils.PhantomLogger;
//import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
//import org.firstinspires.ftc.teamcode.pedropathing.OwnTuning.TuningActions.CentripertalAction;
//import org.firstinspires.ftc.teamcode.pedropathing.Tuning;
//
//@Config
//@TeleOp
//public class CentripetalTuner extends PhantomOpMode {
//    public static double DISTANCE = 20;
//    CentripertalAction centripertalAction;
//
//
//
//    @Override
//    public void customOpModeSettings() {
//        mechanism.add(new FollowerMechanism(this.hardwareMap));
//        centripertalAction = new CentripertalAction(this);
//        actions = new ParallelGroup(this, centripertalAction);
//        PhantomLogger.addData("This will run the robot in a curve going " + DISTANCE + " inches to the left and the same number of inches forward.", true);
//        PhantomLogger.addData("The robot will go continuously along the path.", true);
//        PhantomLogger.addData("Make sure you have enough room.", true);
//        follower.update();
//        drawNow();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        if (opModeIsActive()){
//            follower.activateAllPIDFs();
//            Tuning.follower.followPath(forwards);
//        }
//    }
//}
