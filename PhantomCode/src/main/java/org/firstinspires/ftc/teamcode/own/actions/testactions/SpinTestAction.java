package org.firstinspires.ftc.teamcode.own.actions.testactions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfCrServo;
@Configurable
@Config
public class SpinTestAction implements Action {
    public static Positions position = Positions.CENTER;
    public static double centerDegree = 135, leftDegree = 270, rightDegree = 0;
    @Override
    public void execute() throws InterruptedException {
        Robot.addData("positionSpin", position, true);
        SfCrServo spin = Robot.INSTANCE.get(SfCrServo.class, "spinServo");
        while (Robot.INSTANCE.opMode.opModeIsActive()){
            position = (Positions) Robot.INSTANCE.getData("positionSpin");
            switch (position){
                case LEFT -> {
                    spin.setPower(PhantomMath.servoCRPowerToDegrees(leftDegree, 270));
                    break;
                }
                case RIGHT ->{
                    spin.setPower(PhantomMath.servoCRPowerToDegrees(rightDegree, 270));
                    break;
                }
                case CENTER -> {
                    spin.setPower(PhantomMath.servoCRPowerToDegrees(centerDegree, 270));
                    break;
                }
            }
            if (Robot.INSTANCE.gamepadOperator.right_bumper){
                switch (position){
                    case LEFT -> {
                        Robot.addData("positionSpin", Positions.CENTER, true);
                        break;
                    }
                    case RIGHT ->{
                        Robot.addData("positionSpin", Positions.LEFT, true);
                        break;
                    }
                    case CENTER -> {
                        Robot.addData("positionSpin", Positions.RIGHT, true);
                        break;
                    }
                }
                Robot.INSTANCE.opMode.sleep(300);
            }
        }
    }

    public enum Positions{
        CENTER,
        LEFT,
        RIGHT
    }

}
