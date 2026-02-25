package org.firstinspires.ftc.teamcode.own.actions.stateaction;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;

public class RevolverStateSwap implements Action {
    public static int centerDegree, leftDegree, rightDegree;
    CRServo revolver;
    RevolverStates revolverStates;
    @Override
    public void execute() throws InterruptedException {
        revolver = Robot.INSTANCE.getRobotDevice("rotator", CRServo.class);
        centerDegree = Robot.INSTANCE.getRobotData("centerDegree", Integer.class);
        leftDegree = centerDegree - 135;
        rightDegree = centerDegree + 135;
        while (!Thread.currentThread().isInterrupted()) {
            revolverStates = Robot.INSTANCE.getRobotData("RevolverState", RevolverStates.class);
            switch (revolverStates) {
                case LEFT -> {
                    revolver.setPower(PhantomMath.servoCRPowerToDegrees(leftDegree, 300));
                    sleep(500);
                }
                case CENTER -> {
                    revolver.setPower(PhantomMath.servoCRPowerToDegrees(centerDegree, 300));
                    sleep(500);
                }
                case RIGHT -> {
                    revolver.setPower(PhantomMath.servoCRPowerToDegrees(rightDegree, 300));
                    sleep(500);
                }
            }
            if (!Robot.INSTANCE.queueCurrent.contains("baraban")) {
                Robot.INSTANCE.queueCurrent.add("baraban");
            }
            sleep(10);
        }
    }
}
