package org.firstinspires.ftc.teamcode.own.mechanism;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;

public class LightingMechanism implements Mechanism {
    DigitalChannel yellow1, yellow2, greenLeft, greenRight, blue;
    @Override
    public void init() throws InterruptedException {
        yellow1 = Robot.INSTANCE.hw.get(DigitalChannel.class, "y1");
        yellow2 = Robot.INSTANCE.hw.get(DigitalChannel.class, "y2");
        greenLeft = Robot.INSTANCE.hw.get(DigitalChannel.class, "gL");
        greenRight = Robot.INSTANCE.hw.get(DigitalChannel.class, "gR");
        blue = Robot.INSTANCE.hw.get(DigitalChannel.class, "bl");

        yellow1.setMode(DigitalChannel.Mode.OUTPUT);
        yellow2.setMode(DigitalChannel.Mode.OUTPUT);
        greenLeft.setMode(DigitalChannel.Mode.OUTPUT);
        greenRight.setMode(DigitalChannel.Mode.OUTPUT);
        blue.setMode(DigitalChannel.Mode.OUTPUT);

        yellow1.setState(true);
        yellow2.setState(true);
        greenLeft.setState(true);
        greenRight.setState(true);
        blue.setState(true);

        Robot.INSTANCE.addOrUpdate(yellow1, "y1");
        Robot.INSTANCE.addOrUpdate(yellow2, "y2");
        Robot.INSTANCE.addOrUpdate(greenLeft, "gL");
        Robot.INSTANCE.addOrUpdate(greenRight, "gR");
        Robot.INSTANCE.addOrUpdate(blue, "bl");

    }

    @Override
    public void read() {
        Mechanism.super.read();
        if (yellow1 != null){
            Robot.INSTANCE.addData("yellow 1 state", yellow1.getState());
        }
        if (yellow2 != null){
            Robot.INSTANCE.addData("yellow2 state", yellow2.getState());
        }
        if (greenRight != null){
            Robot.INSTANCE.addData("greenRight state", greenRight.getState());
        }
        if (greenLeft != null){
            Robot.INSTANCE.addData("greenLeft state", greenLeft.getState());
        }
        if (blue != null){
            Robot.INSTANCE.addData("blue state", blue.getState());
        }
    }
}
