package org.firstinspires.ftc.teamcode.own.actions.utilactions;

import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.teamcode.own.mechanism.FollowerMechanism;
import org.firstinspires.ftc.teamcode.own.pedroPathing.Drawing;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class FollowerPosUpdate implements Action {
    Follower follower;
    @Override
    public void execute() throws InterruptedException {
        follower = Robot.INSTANCE.follower;
        while (Robot.INSTANCE.opMode.opModeIsActive() && !follower.isRobotStuck()){
            follower.update();
            Drawing.drawDebug(follower);
        }
    }
}
