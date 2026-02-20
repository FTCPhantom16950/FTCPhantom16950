package org.firstinspires.ftc.teamcode.own.actions.autoactions;

import org.firstinspires.ftc.teamcode.own.utils.Colors;
import org.firstinspires.ftc.teamcode.own.utils.Motif;
import org.firstinspires.ftc.teamcode.own.utils.Positions;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class ShootAutoAction implements Action {
    @Override
    public void execute() throws InterruptedException {

        Robot.INSTANCE.balls.put(Positions.CENTER, Colors.GREEN);
        Robot.INSTANCE.balls.put(Positions.LEFT, Colors.PURPLE);
        Robot.INSTANCE.balls.put(Positions.RIGHT, Colors.PURPLE);
        if (Robot.INSTANCE.opMode.opModeIsActive()) {
            if (true){
                Robot.INSTANCE.addData("podem", true);
                Robot.INSTANCE.addData("shooting", true);
                for (Positions pos : Robot.INSTANCE.balls.keySet()){
                    if (Robot.INSTANCE.balls.get(pos) == Colors.GREEN){
                        Robot.INSTANCE.addData("positionSpin", pos);
                        Robot.INSTANCE.opMode.sleep(1000);
                        Robot.INSTANCE.addData("once", false);
                        Robot.INSTANCE.opMode.sleep(2000);
                        Robot.INSTANCE.addData("shoot", true);
                        Robot.INSTANCE.opMode.sleep(1000);
                        Robot.INSTANCE.addData("shoot", false);
                        Robot.INSTANCE.opMode.sleep(1000);
                    }
                }
                for (Positions pos : Robot.INSTANCE.balls.keySet()){
                    if (Robot.INSTANCE.balls.get(pos) == Colors.PURPLE){
                        Robot.INSTANCE.addData("positionSpin", pos);
                        Robot.INSTANCE.opMode.sleep(1000);
                        Robot.INSTANCE.addData("once", false);
                        Robot.INSTANCE.opMode.sleep(2000);
                        Robot.INSTANCE.addData("shoot", true);
                        Robot.INSTANCE.opMode.sleep(1000);
                        Robot.INSTANCE.addData("shoot", false);
                        Robot.INSTANCE.opMode.sleep(1000);
                    }
                }
                Robot.INSTANCE.addData("podem", false);
                Robot.INSTANCE.addData("shooting", false);
            } else if (Robot.INSTANCE.motif == Motif.THIRD) {
                for (Positions pos : Robot.INSTANCE.balls.keySet()){
                    if (Robot.INSTANCE.balls.get(pos) == Colors.PURPLE){
                        Robot.INSTANCE.addData("positionSpin", pos);
                        while (Robot.INSTANCE.getData(Double.class, "Shooter velocity") <= 3000){}
                        Robot.INSTANCE.addData("once", false);
                        Robot.INSTANCE.opMode.sleep(800);
                        Robot.INSTANCE.addData("shoot", true);
                        Robot.INSTANCE.opMode.sleep(1000);
                        Robot.INSTANCE.addData("shoot", false);
                    }
                }
                for (Positions pos : Robot.INSTANCE.balls.keySet()){
                    if (Robot.INSTANCE.balls.get(pos) == Colors.GREEN){
                        Robot.INSTANCE.addData("positionSpin", pos);

                        while (Robot.INSTANCE.getData(Double.class, "Shooter velocity") <= 3000){}
                        Robot.INSTANCE.addData("once", false);
                        Robot.INSTANCE.opMode.sleep(800);
                        Robot.INSTANCE.addData("shoot", true);
                        Robot.INSTANCE.opMode.sleep(1000);
                        Robot.INSTANCE.addData("shoot", false);

                    }
                }

            } else if (Robot.INSTANCE.motif == Motif.SECOND) {
                for (Positions pos : Robot.INSTANCE.balls.keySet()){
                    if (Robot.INSTANCE.balls.get(pos) == Colors.PURPLE){
                        Robot.INSTANCE.addData("positionSpin", pos);

                        while (Robot.INSTANCE.getData(Double.class, "Shooter velocity") <= 3000){}
                        Robot.INSTANCE.addData("once", false);
                        Robot.INSTANCE.opMode.sleep(800);
                        Robot.INSTANCE.addData("shoot", true);
                        Robot.INSTANCE.opMode.sleep(1000);
                        Robot.INSTANCE.addData("shoot", false);

                    }
                    break;
                }
                for (Positions pos : Robot.INSTANCE.balls.keySet()){
                    if (Robot.INSTANCE.balls.get(pos) == Colors.GREEN){
                        Robot.INSTANCE.addData("positionSpin", pos);

                        while (Robot.INSTANCE.getData(Double.class, "Shooter velocity") <= 3000){}
                        Robot.INSTANCE.addData("once", false);
                        Robot.INSTANCE.opMode.sleep(800);
                        Robot.INSTANCE.addData("shoot", true);
                        Robot.INSTANCE.opMode.sleep(1000);
                        Robot.INSTANCE.addData("shoot", false);

                    }
                }
                for (Positions pos : Robot.INSTANCE.balls.keySet()){
                    if (Robot.INSTANCE.balls.get(pos) == Colors.PURPLE){
                        Robot.INSTANCE.addData("positionSpin", pos);

                        while (Robot.INSTANCE.getData(Double.class, "Shooter velocity") <= 3000){}
                        Robot.INSTANCE.addData("once", false);
                        Robot.INSTANCE.opMode.sleep(800);
                        Robot.INSTANCE.addData("shoot", true);
                        Robot.INSTANCE.opMode.sleep(1000);
                        Robot.INSTANCE.addData("shoot", false);
                    }
                    break;
                }
            }

        }
    }
}


