package org.firstinspires.ftc.teamcode.own.actions.stateaction;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.RotateState;

public class RotateStateSwap implements Action {
    DcMotorEx rotate;
    RotateState rotateState;

    @Override
    public void execute() throws InterruptedException {
        rotate = Robot.INSTANCE.getRobotDevice("rotate", DcMotorEx.class);

        while (!Thread.currentThread().isInterrupted()) {
            rotateState = Robot.INSTANCE.getRobotData("RotateState", RotateState.class);

            switch (rotateState) {
                case LEFT -> {
                    rotate.setPower(1);
                    sleep(300);
                }
                case STOP -> {
                    rotate.setPower(0);
                    sleep(300);
                }
                case RIGHT -> {
                    rotate.setPower(-1);
                    sleep(300);
                }
            }
            sleep(10);
        }
    }
}
