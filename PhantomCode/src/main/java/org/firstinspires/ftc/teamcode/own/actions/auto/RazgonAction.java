package org.firstinspires.ftc.teamcode.own.actions.auto;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.LauncherState;

public class RazgonAction implements Action {
    DcMotorEx launcher;
    LauncherState launcherState;
    @Override
    public void execute() throws InterruptedException {
        launcher = Robot.INSTANCE.getRobotDevice("launcher", DcMotorEx.class);
        if (!Thread.currentThread().isInterrupted()){
            launcherState = Robot.INSTANCE.getRobotData("LauncherState", LauncherState.class);
            Robot.INSTANCE.addData("LauncherState", LauncherState.LAUNCH);
            while (PhantomMath.convertToRPM(launcher.getVelocity(), 28) < 3000){
                Robot.INSTANCE.addTelemetryData("Wait for", "razgon");
            }
        }
    }
}
