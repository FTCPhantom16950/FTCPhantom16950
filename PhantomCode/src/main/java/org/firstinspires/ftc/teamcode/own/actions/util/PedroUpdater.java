package org.firstinspires.ftc.teamcode.own.actions.util;

import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class PedroUpdater implements Action {
    @Override
    public void execute() throws InterruptedException {
        Follower follower = Robot.INSTANCE.getRobotData("Follower", Follower.class);
        while (!Thread.currentThread().isInterrupted()){
            follower.update();
        }
    }
}
