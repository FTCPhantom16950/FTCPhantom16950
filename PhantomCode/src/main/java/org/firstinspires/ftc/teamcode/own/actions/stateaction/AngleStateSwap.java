package org.firstinspires.ftc.teamcode.own.actions.stateaction;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.AngleState;

public class AngleStateSwap implements Action {
    AngleState angleState;
    CRServo angle;
    public static int angleStartDegree, angleUpDegree = 270;
    @Override
    public void execute() throws InterruptedException {
        angleStartDegree = Robot.INSTANCE.getRobotData("angleStartDegree", Integer.class);
        angle = Robot.INSTANCE.getRobotDevice("angle", CRServo.class);
        while (!Thread.currentThread().isInterrupted()) {
            angleState = Robot.INSTANCE.getRobotData("AngleState", AngleState.class);
            switch (angleState) {
                case UP -> {
                    angle.setPower(PhantomMath.servoCRPowerToDegrees(angleUpDegree, 270));
                    sleep(300);
                }
                case DOWN -> {
                    angle.setPower(PhantomMath.servoCRPowerToDegrees(angleStartDegree, 270));
                    sleep(300);
                }
            }
            sleep(10);
        }
    }
}
