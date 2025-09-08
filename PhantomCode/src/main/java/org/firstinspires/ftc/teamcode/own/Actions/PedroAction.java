package org.firstinspires.ftc.teamcode.own.Actions;

import com.pedropathing.follower.Follower;
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

    public PedroAction(PhantomOpMode OpMode, Mechanism mechanism, PathChain line) {
        super(OpMode);
        this.opMode = OpMode;
        this.line = line;
    }

    private static final Follower FOLLOWER = FollowerMechanism.follower;

    @Override
    public void execute() {
        if (!FOLLOWER.isBusy() && opMode.opModeIsActive()) {
            FOLLOWER.followPath(line, holdEnd);
        }
        while (opMode.opModeIsActive() && !FOLLOWER.atPose(line.endPose(), 0.5, 0.5, 0.5)) {
            FOLLOWER.update();
        }

    }
}
