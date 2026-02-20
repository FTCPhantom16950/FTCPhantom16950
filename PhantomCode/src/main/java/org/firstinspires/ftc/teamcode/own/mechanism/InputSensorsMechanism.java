package org.firstinspires.ftc.teamcode.own.mechanism;

import android.graphics.Color;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.utils.Colors;
import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;

public class InputSensorsMechanism implements Mechanism {
    RevColorSensorV3 colorSensor;
    Rev2mDistanceSensor distanceSensor;
    Colors colors = Colors.UNKONOWN;
    @Override
    public void init() throws InterruptedException {
        colorSensor = Robot.INSTANCE.hw.get(RevColorSensorV3.class, "color");
        distanceSensor = Robot.INSTANCE.hw.get(Rev2mDistanceSensor.class, "dist");
        distanceSensor.initialize();
        colorSensor.initialize();
        Robot.INSTANCE.addData("colorDist", 0);
    }

    @Override
    public void read() throws InterruptedException {
        Mechanism.super.read();
        Robot.INSTANCE.addData("colorDist", colorSensor.getDistance(DistanceUnit.MM));
        Robot.INSTANCE.addData("red", colorSensor.red());
        Robot.INSTANCE.addData("green", colorSensor.green());
        Robot.INSTANCE.addData("blue", colorSensor.blue());
        Robot.INSTANCE.addData("currentColor", colors);
        Robot.INSTANCE.addData("rangeDist", distanceSensor.getDistance(DistanceUnit.MM));
    }
}
