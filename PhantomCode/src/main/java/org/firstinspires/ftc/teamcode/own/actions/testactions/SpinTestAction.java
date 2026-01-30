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
    public Positions position = Positions.CENTER;
    public static int centerDegree = 270, leftDegree = 135, rightDegree = 0;

    @Override
    public void execute() throws InterruptedException {
        Robot.INSTANCE.addData( "shoot", false);
        Robot.INSTANCE.addData("positionSpin", position);
        SfCrServo spin = Robot.INSTANCE.get(SfCrServo.class, "spinServo");
        boolean once = false;
        while (Robot.INSTANCE.opMode.opModeIsActive()) {
            position = Robot.INSTANCE.getData(Positions.class,"positionSpin");
            if (position == Positions.LEFT && !once) {
                once = true;
                spin.setPower(PhantomMath.servoCRPowerToDegrees(leftDegree, 270));
                Robot.INSTANCE.opMode.sleep(500);
                if (Robot.INSTANCE.getData(Boolean.class, "shooting")){
                    Robot.INSTANCE.addData( "shoot", true);
                    Robot.INSTANCE.opMode.sleep(200);
                    Robot.INSTANCE.addData( "shoot", false);
                    Robot.INSTANCE.opMode.sleep(300);
                }
            } else if (position == Positions.RIGHT && !once) {
                once = true;
                spin.setPower(PhantomMath.servoCRPowerToDegrees(rightDegree, 270));
                Robot.INSTANCE.opMode.sleep(500);
                if (Robot.INSTANCE.getData(Boolean.class, "shooting")){
                    Robot.INSTANCE.addData( "shoot", true);
                    Robot.INSTANCE.opMode.sleep(200);
                    Robot.INSTANCE.addData( "shoot", false);
                    Robot.INSTANCE.opMode.sleep(300);
                }

            } else if (position == Positions.CENTER && !once) {
                once = true;
                spin.setPower(PhantomMath.servoCRPowerToDegrees(leftDegree, 270));
                Robot.INSTANCE.opMode.sleep(300);
                spin.setPower(PhantomMath.servoCRPowerToDegrees(centerDegree, 270));
                Robot.INSTANCE.opMode.sleep(500);
                if (Robot.INSTANCE.getData(Boolean.class, "shooting")){
                    Robot.INSTANCE.addData( "shoot", true);
                    Robot.INSTANCE.opMode.sleep(200);
                    Robot.INSTANCE.addData( "shoot", false);
                    Robot.INSTANCE.opMode.sleep(300);
                }
            }
            boolean mem1 = Robot.INSTANCE.gamepadOperator.right_bumper;
            boolean mem = (Robot.INSTANCE.getData(Double.class,"colorDist") <= 26 & Robot.INSTANCE.balls.size() < 3 & !Robot.INSTANCE.getData(Boolean.class,"shooting"));
            Robot.INSTANCE.addData("Mem", mem);
            if (mem1){
                switch (position) {
                    case LEFT -> {
                        once = false;
                        Robot.INSTANCE.addData("positionSpin", Positions.CENTER);
                        Robot.INSTANCE.opMode.sleep(500);
                        break;
                    }
                    case RIGHT -> {
                        once = false;
                        Robot.INSTANCE.addData("positionSpin", Positions.LEFT);
                        Robot.INSTANCE.opMode.sleep(500);
                        break;
                    }
                    case CENTER -> {
                        once = false;
                        Robot.INSTANCE.addData("positionSpin", Positions.RIGHT);
                        Robot.INSTANCE.opMode.sleep(500);
                        break;
                    }
                }
            }
            else if (mem) {
                Colors colors = Colors.UNKONOWN;
                switch (position) {
                    case LEFT -> {
                        once = false;
                        if (!Robot.INSTANCE.balls.containsKey(Positions.CENTER)){
                            Robot.INSTANCE.addData("positionSpin", Positions.CENTER);
                        } else if (!Robot.INSTANCE.balls.containsKey(Positions.RIGHT)){
                            Robot.INSTANCE.addData("positionSpin", Positions.RIGHT);
                        }
                        Robot.INSTANCE.balls.put(Positions.LEFT, colors);
                        Robot.INSTANCE.opMode.sleep(800);
                        break;
                    }
                    case RIGHT -> {
                        once = false;
                        if (!Robot.INSTANCE.balls.containsKey(Positions.CENTER)){
                            Robot.INSTANCE.addData("positionSpin", Positions.CENTER);
                        } else if (!Robot.INSTANCE.balls.containsKey(Positions.LEFT)){
                            Robot.INSTANCE.addData("positionSpin", Positions.LEFT);
                        }
                        Robot.INSTANCE.balls.put(Positions.RIGHT, colors);
                        Robot.INSTANCE.opMode.sleep(800);
                        break;
                    }
                    case CENTER -> {
                        once = false;
                        if (!Robot.INSTANCE.balls.containsKey(Positions.RIGHT)){
                            Robot.INSTANCE.addData("positionSpin", Positions.RIGHT);
                        } else if (!Robot.INSTANCE.balls.containsKey(Positions.LEFT)){
                            Robot.INSTANCE.addData("positionSpin", Positions.LEFT);
                        }
                        Robot.INSTANCE.balls.put(Positions.CENTER, colors);
                        Robot.INSTANCE.opMode.sleep(800);
                        break;
                    }
                }

            }

            else if (Robot.INSTANCE.gamepadOperator.a && Robot.INSTANCE.getData(Boolean.class, "shooting") && Robot.INSTANCE.getData(Double.class, "Shooter velocity") >= 3300){
                spin.setPower(PhantomMath.servoCRPowerToDegrees(rightDegree, 270));
                Robot.INSTANCE.opMode.sleep(500);
                if (Robot.INSTANCE.getData(Boolean.class, "shooting")){
                    Robot.INSTANCE.addData( "shoot", true);
                    Robot.INSTANCE.opMode.sleep(200);
                    Robot.INSTANCE.addData( "shoot", false);
                    Robot.INSTANCE.opMode.sleep(300);
                }
                spin.setPower(PhantomMath.servoCRPowerToDegrees(leftDegree, 270));
                Robot.INSTANCE.opMode.sleep(500);
                if (Robot.INSTANCE.getData(Boolean.class, "shooting")){
                    Robot.INSTANCE.addData( "shoot", true);
                    Robot.INSTANCE.opMode.sleep(200);
                    Robot.INSTANCE.addData( "shoot", false);
                    Robot.INSTANCE.opMode.sleep(300);
                }
                spin.setPower(PhantomMath.servoCRPowerToDegrees(leftDegree, 270));
                Robot.INSTANCE.opMode.sleep(300);
                spin.setPower(PhantomMath.servoCRPowerToDegrees(centerDegree, 270));
                Robot.INSTANCE.opMode.sleep(500);
                if (Robot.INSTANCE.getData(Boolean.class, "shooting")){
                    Robot.INSTANCE.addData( "shoot", true);
                    Robot.INSTANCE.opMode.sleep(200);
                    Robot.INSTANCE.addData( "shoot", false);
                    Robot.INSTANCE.opMode.sleep(300);
                }
            }
            else if (Robot.INSTANCE.getData(Boolean.class, "removed") && !Robot.INSTANCE.balls.isEmpty() && Robot.INSTANCE.getData(Boolean.class, "shooting") && Robot.INSTANCE.getData(Double.class, "Shooter velocity") >= 3300){
                Robot.INSTANCE.addData("removed", false);
                for (Positions pos : Robot.INSTANCE.balls.keySet()){
                    Robot.INSTANCE.addData("positionSpin", pos);
                    break;
                }
            }
            Robot.INSTANCE.addData("balls", Robot.INSTANCE.balls.keySet().toString());
        }

    }


}
