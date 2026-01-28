package org.firstinspires.ftc.teamcode.own.actions.teleactions;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class LightingAction implements Action {
    @Override
    public void execute() throws InterruptedException {
        DigitalChannel yellow1 = Robot.INSTANCE.get(DigitalChannel.class, "y1"),
        yellow2 = Robot.INSTANCE.get(DigitalChannel.class, "y2"),
        greenLeft = Robot.INSTANCE.get(DigitalChannel.class, "gL"),
        greenRight = Robot.INSTANCE.get(DigitalChannel.class, "gR"),
        blue = Robot.INSTANCE.get(DigitalChannel.class, "bl");
        while (Robot.INSTANCE.opMode.opModeIsActive()){
            turnOn(yellow1);
            turnOn(yellow2);
            turnOn(greenLeft);
            turnOn(greenRight);
            turnOn(blue);
//            Robot.INSTANCE.opMode.sleep(100);
//            turnOff(yellow1);
//            turnOn(yellow2);
//            turnOff(greenLeft);
//            turnOn(greenRight);
//            turnOff(blue);
//            Robot.INSTANCE.opMode.sleep(100);
//            if (Robot.INSTANCE.opMode.isStopRequested()){
//                turnOff(yellow1);
//                turnOff(yellow2);
//                turnOff(greenLeft);
//                turnOff(greenRight);
//                turnOff(blue);
//            }
        }


    }
    public void turnOn(DigitalChannel digitalChannel){
        digitalChannel.setState(false);
    }
    public void turnOff(DigitalChannel digitalChannel){
        digitalChannel.setState(true);
    }
}
