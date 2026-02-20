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

    @Override
    public void execute() throws InterruptedException {
        Robot.INSTANCE.addData("shoot", false);
        Robot.INSTANCE.addData("positionSpin", position);
        while (Robot.INSTANCE.opMode.opModeIsActive()) {
            position = Robot.INSTANCE.getData(Positions.class, "positionSpin");
            boolean mem1 = Robot.INSTANCE.gamepadOperator.a && Robot.INSTANCE.getData(Boolean.class, "shooting") && Robot.INSTANCE.getData(Double.class, "Shooter velocity") >= 3300;
            boolean mem = (Robot.INSTANCE.getData(Double.class, "colorDist") <= 26 & Robot.INSTANCE.balls.size() < 3 & !Robot.INSTANCE.getData(Boolean.class, "shooting"));
            Robot.INSTANCE.addData("Mem", mem);
            Robot.INSTANCE.addData("Mem1", mem1);
            if (Robot.INSTANCE.gamepadOperator.right_bumper) {
                switch (position) {
                    case LEFT -> {
                        Robot.INSTANCE.addData("once", false);
                        Robot.INSTANCE.addData("positionSpin", Positions.CENTER);
                        Robot.INSTANCE.opMode.sleep(500);
                        break;
                    }
                    case RIGHT -> {
                        Robot.INSTANCE.addData("once", false);
                        Robot.INSTANCE.addData("positionSpin", Positions.LEFT);
                        Robot.INSTANCE.opMode.sleep(500);
                        break;
                    }
                    case CENTER -> {
                        Robot.INSTANCE.addData("once", false);
                        Robot.INSTANCE.addData("positionSpin", Positions.RIGHT);
                        Robot.INSTANCE.opMode.sleep(500);
                        break;
                    }
                }
            }
//            else if (Robot.INSTANCE.getData(Double.class, "colorDist") <= 24.3 & Robot.INSTANCE.balls.size() < 3 & !Robot.INSTANCE.getData(Boolean.class, "shooting")) {
//                Robot.INSTANCE.opMode.sleep(1500);
//                Colors colors = Colors.UNKONOWN;
//                switch (position) {
//                    case LEFT -> {
//                        Robot.INSTANCE.addData("once", false);
//                        if (!Robot.INSTANCE.balls.containsKey(Positions.CENTER)) {
//                            Robot.INSTANCE.addData("positionSpin", Positions.CENTER);
//                        } else if (!Robot.INSTANCE.balls.containsKey(Positions.RIGHT)) {
//                            Robot.INSTANCE.addData("positionSpin", Positions.RIGHT);
//                        }
//                        Robot.INSTANCE.balls.put(Positions.LEFT, colors);
//                        Robot.INSTANCE.opMode.sleep(800);
//                        break;
//                    }
//                    case RIGHT -> {
//                        Robot.INSTANCE.addData("once", false);
//                        if (!Robot.INSTANCE.balls.containsKey(Positions.CENTER)) {
//                            Robot.INSTANCE.addData("positionSpin", Positions.CENTER);
//                        } else if (!Robot.INSTANCE.balls.containsKey(Positions.LEFT)) {
//                            Robot.INSTANCE.addData("positionSpin", Positions.LEFT);
//                        }
//                        Robot.INSTANCE.balls.put(Positions.RIGHT, colors);
//                        Robot.INSTANCE.opMode.sleep(800);
//                        break;
//                    }
//                    case CENTER -> {
//                        Robot.INSTANCE.addData("once", false);
//                        if (!Robot.INSTANCE.balls.containsKey(Positions.RIGHT)) {
//                            Robot.INSTANCE.addData("positionSpin", Positions.RIGHT);
//                        } else if (!Robot.INSTANCE.balls.containsKey(Positions.LEFT)) {
//                            Robot.INSTANCE.addData("positionSpin", Positions.LEFT);
//                        }
//                        Robot.INSTANCE.balls.put(Positions.CENTER, colors);
//                        Robot.INSTANCE.opMode.sleep(800);
//                        break;
//                    }
//                }

//            }
        else if (Robot.INSTANCE.gamepadOperator.a && Robot.INSTANCE.getData(Boolean.class, "shooting") && Robot.INSTANCE.getData(Double.class, "Shooter velocity") >= 3300) {
                Robot.INSTANCE.addData("once", false);
                Robot.INSTANCE.addData("positionSpin", Positions.CENTER);
                Robot.INSTANCE.opMode.sleep(1500);
                Robot.INSTANCE.addData("once", false);
                Robot.INSTANCE.addData("positionSpin", Positions.LEFT);
                Robot.INSTANCE.opMode.sleep(1500);
                Robot.INSTANCE.addData("once", false);
                Robot.INSTANCE.addData("positionSpin", Positions.RIGHT);
                Robot.INSTANCE.opMode.sleep(1500);
            } else if (Robot.INSTANCE.getData(Boolean.class, "removed") && Robot.INSTANCE.getData(Boolean.class, "shooting") && Robot.INSTANCE.getData(Double.class, "Shooter velocity") >= 3300) {
                Robot.INSTANCE.addData("removed", false);
                for (Positions pos : Robot.INSTANCE.balls.keySet()) {
                    Robot.INSTANCE.addData("positionSpin", pos);
                    break;
                }
            }
            Robot.INSTANCE.addData("balls", Robot.INSTANCE.balls.keySet().toString());
        }

    }


}
