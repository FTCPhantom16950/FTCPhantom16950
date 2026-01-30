package org.firstinspires.ftc.teamcode.own.actions.teleactions;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class ColorGreenAction implements Action {
    float highRed, lowRed, highGreen, lowGreen, highBlue, lowBlue;
    @Override
    public void execute() throws InterruptedException {
        while (Robot.INSTANCE.opMode.opModeIsActive()){
            float r = Robot.INSTANCE.getData(Float.class,"red"),
                    g = Robot.INSTANCE.getData(Float.class,"green"),
                    b = Robot.INSTANCE.getData(Float.class,"blue");
        }
    }
}
