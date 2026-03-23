package org.firstinspires.ftc.teamcode.own.mechanism;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.broadcom.BroadcomColorSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
@Config
public class ColorSensorsMechanism implements Mechanism {
    public static float gain = 10;
    RevColorSensorV3 colorSpinner, colorBottom;
    HardwareMap hw;
    @Override
    public void init() throws InterruptedException {
        hw = Robot.INSTANCE.getRobotData("HardwareMap", HardwareMap.class);

        colorSpinner = hw.get(RevColorSensorV3.class, "spinner");
//        colorBottom = hw.get(RevColorSensorV3.class, "bottom");

        colorSpinner.setGain(gain);
//        colorBottom.setGain(gain);

        Robot.INSTANCE.addData("spinnerDistance", colorSpinner.getDistance(DistanceUnit.MM));

//        Robot.INSTANCE.addData("bottomRed", colorBottom.getNormalizedColors().red);
//        Robot.INSTANCE.addData("bottomGreen", colorBottom.getNormalizedColors().green);
//        Robot.INSTANCE.addData("bottomBlue", colorBottom.getNormalizedColors().blue);

        Robot.INSTANCE.addRobotDevice("colorSpinner", colorSpinner);
//        Robot.INSTANCE.addRobotDevice("colorBottom", colorBottom);
    }
}
