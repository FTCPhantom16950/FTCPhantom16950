package org.firstinspires.ftc.teamcode.Own.Mechanism;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.Own.Utils.Robot;

public class GyroScopeMechanism implements Mechanism {
    @Override
    public void init() throws InterruptedException {
        IMU imu = Robot.INSTANCE.hw.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.RIGHT)));
        Robot.INSTANCE.imu = imu;
    }

    @Override
    public void read() {
        Mechanism.super.read();
        Robot.INSTANCE.rot = Robot.INSTANCE.imu.getRobotYawPitchRollAngles().getYaw();
    }
}
