package org.firstinspires.ftc.teamcode.own.actions.teleactions;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.own.utils.Colors;
import org.firstinspires.ftc.teamcode.own.utils.Positions;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

import java.util.Set;

public class LightingAction implements Action {
    @Override
    public void execute() throws InterruptedException {
        DigitalChannel yellow1 = Robot.INSTANCE.get(DigitalChannel.class, "y1"),
                yellow2 = Robot.INSTANCE.get(DigitalChannel.class, "y2"),
                greenLeft = Robot.INSTANCE.get(DigitalChannel.class, "gL"),
                greenRight = Robot.INSTANCE.get(DigitalChannel.class, "gR"),
                blue = Robot.INSTANCE.get(DigitalChannel.class, "bl");
        while (Robot.INSTANCE.opMode.opModeIsActive()) {
            if (Robot.INSTANCE.imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES) >= 0.1f){
                turnOn(yellow1);
                turnOn(yellow2);
                Robot.INSTANCE.addData("pitch", Robot.INSTANCE.imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES));
            } else {
                turnOff(yellow1);
                turnOff(yellow2);
            }
            if (Robot.INSTANCE.balls.containsValue(Colors.PURPLE)){
                turnOn(blue);
            }
            else{
                turnOff(blue);
            }

            for(Positions pos: Robot.INSTANCE.balls.keySet()){
                switch (pos){
                    case LEFT -> {
                        if (Robot.INSTANCE.balls.get(Positions.LEFT) == Colors.GREEN){
                            turnOn(greenLeft);
                        } else{
                            turnOff(greenLeft);
                        }
                    }
                    case RIGHT -> {
                        if (Robot.INSTANCE.balls.get(Positions.RIGHT) == Colors.GREEN){
                            turnOn(greenRight);
                        } else{
                            turnOff(greenRight);
                        }
                    }
                    case CENTER -> {
                        if (Robot.INSTANCE.balls.get(Positions.CENTER) == Colors.GREEN){
                            turnOn(greenRight);
                        } else{
                            turnOff(greenRight);
                        }
                    }

                }
            }

        }


    }

    public void turnOn(DigitalChannel digitalChannel) {
        digitalChannel.setState(false);
    }

    public void turnOff(DigitalChannel digitalChannel) {
        digitalChannel.setState(true);
    }
}
