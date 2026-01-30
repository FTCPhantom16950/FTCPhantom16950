package org.firstinspires.ftc.teamcode.own.actions.testactions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.own.utils.Colors;
import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Positions;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfCrServo;

@Configurable
@Config
public class SpinTestAction implements Action {
    public static Positions position = Positions.CENTER;
    private Object object = new Object();
    public static int centerDegree = 270, leftDegree = 135, rightDegree = 0;

    @Override
    public void execute() throws InterruptedException {
        Robot.INSTANCE.addData("positionSpin", position);
        SfCrServo spin = Robot.INSTANCE.get(SfCrServo.class, "spinServo");
        boolean once = false;
        while (Robot.INSTANCE.opMode.opModeIsActive()) {
            position = Robot.INSTANCE.getData(Positions.class,"positionSpin");
            if (position == Positions.LEFT && !once) {
                once = true;
                spin.setPower(PhantomMath.servoCRPowerToDegrees(leftDegree, 270));
            } else if (position == Positions.RIGHT && !once) {
                once = true;
                spin.setPower(PhantomMath.servoCRPowerToDegrees(rightDegree, 270));
            } else if (position == Positions.CENTER && !once) {
                once = true;
                spin.setPower(PhantomMath.servoCRPowerToDegrees(leftDegree, 270));
                Robot.INSTANCE.opMode.sleep(300);
                spin.setPower(PhantomMath.servoCRPowerToDegrees(centerDegree, 270));
            }
            boolean mem1 = Robot.INSTANCE.gamepadOperator.right_bumper;
//            boolean mem =  ((double) Robot.INSTANCE.getData("colorDist") <= 26 & Robot.INSTANCE.balls.keySet().size() < 3 & !(boolean) Robot.INSTANCE.getData("shooting"));
//            Robot.INSTANCE.addData("mem", mem);
            Robot.INSTANCE.addData("mem1", mem1);
            if (mem1 | false) {
                switch (position) {
                    case LEFT -> {
                        once = false;
                        Robot.INSTANCE.addData("positionSpin", Positions.RIGHT);
                        Robot.INSTANCE.balls.put(Positions.LEFT, Colors.UNKONOWN);
                        Robot.INSTANCE.opMode.sleep(700);
                        break;
                    }
                    case RIGHT -> {
                        once = false;
                        Robot.INSTANCE.addData("positionSpin", Positions.CENTER);
                        Robot.INSTANCE.balls.put(Positions.RIGHT, Colors.UNKONOWN);
                        Robot.INSTANCE.opMode.sleep(700);
                        break;
                    }
                    case CENTER -> {
                        once = false;
                        Robot.INSTANCE.addData("positionSpin", Positions.LEFT);
                        Robot.INSTANCE.balls.put(Positions.CENTER, Colors.UNKONOWN);
                        Robot.INSTANCE.opMode.sleep(700);
                        break;
                    }
                }

            }
            Robot.INSTANCE.addData("shutka", Robot.INSTANCE.balls.keySet().size());
        }

    }


}
