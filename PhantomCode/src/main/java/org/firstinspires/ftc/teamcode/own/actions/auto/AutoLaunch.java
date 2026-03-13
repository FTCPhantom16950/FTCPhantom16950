package org.firstinspires.ftc.teamcode.own.actions.auto;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.actions.InterruptibleAction;
import org.firstinspires.ftc.teamcode.own.utils.states.LauncherState;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;
import org.firstinspires.ftc.teamcode.own.utils.states.UpperState;

public class AutoLaunch extends InterruptibleAction {
    DcMotorEx launcher;
    RevolverStates revolverStates, previousState;
    @Override
    public void run() throws InterruptedException {
        launcher = Robot.INSTANCE.getRobotDevice("launcher", DcMotorEx.class);
        Robot.INSTANCE.addTelemetryData("velocity", PhantomMath.convertToRPM(launcher.getVelocity(), 28) );
        revolverStates = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
        if (gamepad1.left_bumper){
            isInterrupted = true;
        }
        if (PhantomMath.convertToRPM(launcher.getVelocity(), 28) > 3500) {
            Robot.INSTANCE.queueCurrent.add("pusk_razresh");
            Robot.INSTANCE.addData("UpperState", UpperState.UP);
            sleep(800);
            Robot.INSTANCE.addData("UpperState", UpperState.DOWN);
            sleep(1500);
            switch (revolverStates) {
                case RIGHT -> {
                    Robot.INSTANCE.addData("RevolverState", RevolverStates.CENTER);
                    sleep(800);
                }
                case CENTER -> {
                    Robot.INSTANCE.addData("RevolverState", RevolverStates.LEFT);
                    sleep(800);
                }
                case LEFT -> {
                    Robot.INSTANCE.addData("RevolverState", RevolverStates.RIGHT);
                    sleep(800);
                }
            }

            sleep(300);
            previousState = revolverStates;
        }

    }

    @Override
    public void handleInterrupt() throws InterruptedException {
        revolverStates = previousState;
        Robot.INSTANCE.addData("UpperState", UpperState.UP);
        sleep(800);
        Robot.INSTANCE.addData("UpperState", UpperState.DOWN);
        if (gamepad1.left_bumper){
            isInterrupted = false;
        }
    }
}
