package org.firstinspires.ftc.teamcode.own.mechanism;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;

public class ImuMechanism implements Mechanism {
    HardwareMap hw;
    IMU imu;

    public ImuMechanism(HardwareMap hw) {
        this.hw = hw;
    }

    @Override
    public void init() throws InterruptedException {
        imu = hw.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP)));
        Robot.INSTANCE.addRobotDevice("imu", imu);
    }
}
