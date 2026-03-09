package org.firstinspires.ftc.teamcode.own.actions.test;

import com.qualcomm.hardware.rev.RevColorSensorV3;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class ColorTestAction implements Action {
    RevColorSensorV3 colorSpinner, colorBottom;

    @Override
    public void execute() throws InterruptedException {
        colorSpinner = Robot.INSTANCE.getRobotDevice("colorSpinner", RevColorSensorV3.class);
        colorBottom = Robot.INSTANCE.getRobotDevice("colorBottom", RevColorSensorV3.class);
        while (!Thread.currentThread().isInterrupted()){
            Robot.INSTANCE.addTelemetryData("spinnerRed", colorSpinner.getNormalizedColors().red);
            Robot.INSTANCE.addTelemetryData("spinnerGreen", colorSpinner.getNormalizedColors().green);
            Robot.INSTANCE.addTelemetryData("spinnerBlue", colorSpinner.getNormalizedColors().blue);

            Robot.INSTANCE.addTelemetryData("bottomRed", colorBottom.getNormalizedColors().red);
            Robot.INSTANCE.addTelemetryData("bottomGreen", colorBottom.getNormalizedColors().green);
            Robot.INSTANCE.addTelemetryData("bottomBlue", colorBottom.getNormalizedColors().blue);

        }
    }
}
