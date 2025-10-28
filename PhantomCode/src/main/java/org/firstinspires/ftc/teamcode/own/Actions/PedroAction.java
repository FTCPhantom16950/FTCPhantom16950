package org.firstinspires.ftc.teamcode.own.Actions;



import static org.firstinspires.ftc.teamcode.own.Mechanism.FollowerMechanism.follower;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.paths.HeadingInterpolator;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.own.Mechanism.FollowerMechanism;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

import java.util.function.Supplier;


public class PedroAction extends Action {
    PhantomOpMode opMode;
    PathChain line;
    private boolean holdEnd = true;

    public boolean isHoldEnd() {
        return holdEnd;
    }

    public void setHoldEnd(boolean holdEnd) {
        this.holdEnd = holdEnd;
    }

    public PedroAction(PhantomOpMode OpMode, PathChain line) {
        this.opMode = OpMode;
        this.line = line;
    }



    @Override
    public void execute() {
        if (opMode.opModeIsActive()) {
            follower.followPath(line, holdEnd);
        }
        while (opMode.opModeIsActive() && (!follower.atPose(line.endPose(), 0.5, 0.5, 0.5) || follower.isBusy()) && !follower.isRobotStuck()) {
            follower.update();
        }

    }
}
