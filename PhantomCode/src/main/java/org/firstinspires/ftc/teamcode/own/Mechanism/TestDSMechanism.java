package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorREV2mDistance;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

public class TestDSMechanism implements Mechanism {

    @Override
    public boolean init() throws InterruptedException {
//        ModernRoboticsI2cRangeSensor modernRoboticsI2cRangeSensor = hw.get(ModernRoboticsI2cRangeSensor.class, "ds");
        Rev2mDistanceSensor sensorREV2mDistance = hw.get(Rev2mDistanceSensor.class, "ds1");
        Robot.addOrUpdate("ds1", sensorREV2mDistance);
//        Robot.addOrUpdate("ds", modernRoboticsI2cRangeSensor);
        return false;
    }
}
