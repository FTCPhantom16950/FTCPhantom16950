package org.firstinspires.ftc.teamcode.own.actions.util;

import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class IMUTest implements Action {
    @Override
    public void execute() throws InterruptedException {
        IMU imu = Robot.INSTANCE.getRobotDevice("imu", IMU.class);
        while (!Thread.currentThread().isInterrupted()){
            Robot.INSTANCE.addTelemetryData("Roll", imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.DEGREES));
            Robot.INSTANCE.addTelemetryData("Yaw", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
            Robot.INSTANCE.addTelemetryData("Pitch", imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES));
        }

    }
}
