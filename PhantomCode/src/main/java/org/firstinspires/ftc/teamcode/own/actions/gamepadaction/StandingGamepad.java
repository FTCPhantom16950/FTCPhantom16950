package org.firstinspires.ftc.teamcode.own.actions.gamepadaction;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.StandingState;

public class StandingGamepad implements Action {
    @Override
    public void execute() throws InterruptedException {
        StandingState standingState = StandingState.STOP;
        Robot.INSTANCE.addData("StandingState", standingState);
        Gamepad gamepad1 = Robot.INSTANCE.getRobotData("Gamepad 1", Gamepad.class);
        while (!Thread.currentThread().isInterrupted()){
            standingState = Robot.INSTANCE.getRobotData("StandingState", StandingState.class);
            if (gamepad1.dpad_up){
                standingState = StandingState.STOP;
                sleep(300);
            } else if (gamepad1.dpad_down) {
                standingState = StandingState.DOWN;
                sleep(300);
            } else if (gamepad1.options) {
                standingState = StandingState.UP;
                sleep(300);
            }
            Robot.INSTANCE.addData("StandingState", standingState);
            sleep(10);
        }
    }
}
