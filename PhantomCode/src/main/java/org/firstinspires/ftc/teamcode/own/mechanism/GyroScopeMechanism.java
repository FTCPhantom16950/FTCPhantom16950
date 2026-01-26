package org.firstinspires.ftc.teamcode.own.mechanism;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;

public class GyroScopeMechanism implements Mechanism {
    @Override
    public void init() throws InterruptedException {
        IMU imu = Robot.INSTANCE.hw.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD, RevHubOrientationOnRobot.UsbFacingDirection.UP)));
        Robot.INSTANCE.imu = imu;
    }

    @Override
    public void read() {
        Mechanism.super.read();
        Robot.INSTANCE.rot = Robot.INSTANCE.imu.getRobotYawPitchRollAngles().getYaw();
    }
}
