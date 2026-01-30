package org.firstinspires.ftc.teamcode.own.mechanism;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;

public class InputSensorsMechanism implements Mechanism {
    RevColorSensorV3 colorSensor;
    Rev2mDistanceSensor distanceSensor;
    @Override
    public void init() throws InterruptedException {
        colorSensor = Robot.INSTANCE.hw.get(RevColorSensorV3.class, "color");
        distanceSensor = Robot.INSTANCE.hw.get(Rev2mDistanceSensor.class, "dist");
        distanceSensor.initialize();
        colorSensor.initialize();
    }

    @Override
    public void read() {
        Mechanism.super.read();
        Robot.INSTANCE.addData("colorDist", colorSensor.getDistance(DistanceUnit.MM));
        Robot.INSTANCE.addData("rangeDist", distanceSensor.getDistance(DistanceUnit.MM));
    }
}
