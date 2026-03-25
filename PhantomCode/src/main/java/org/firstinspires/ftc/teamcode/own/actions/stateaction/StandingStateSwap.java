package org.firstinspires.ftc.teamcode.own.actions.stateaction;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.StandingState;

public class StandingStateSwap implements Action{
    DcMotorEx motor;
    StandingState standingState;
    @Override
    public void execute() throws InterruptedException {
        motor = Robot.INSTANCE.getRobotDevice("stand", DcMotorEx.class);
        while (!Thread.currentThread().isInterrupted()){
            standingState = Robot.INSTANCE.getRobotData("StandingState", StandingState.class);
            switch (standingState){
                case UP -> {
                    motor.setPower(1);
                }
                case DOWN -> {
                    motor.setPower(-1);
                }
                case STOP -> {
                    motor.setPower(0);
                }
            }
            sleep(10);
        }

    }
}
