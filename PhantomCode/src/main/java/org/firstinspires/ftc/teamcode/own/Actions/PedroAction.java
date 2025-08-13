package org.firstinspires.ftc.teamcode.own.Actions;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.own.Mechanism.FollowerMechanism;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;


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

    public PedroAction(PhantomOpMode OpMode, Mechanism mechanism, PathChain line) {
        super(mechanism);
        this.opMode = OpMode;
        this.line = line;
    }
    private static Follower follower = FollowerMechanism.follower;
    @Override
    public void execute() {
        if (!follower.isBusy() && opMode.opModeIsActive()) {
            follower.followPath(line, holdEnd);
        }
        while (opMode.opModeIsActive() && !follower.atPose(line.endPose(), 0.5, 0.5,0.5)){
            follower.update();
        }

    }
}
