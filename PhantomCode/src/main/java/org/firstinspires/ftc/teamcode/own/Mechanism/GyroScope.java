package org.firstinspires.ftc.teamcode.own.Mechanism;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.hw;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.imu;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

public class GyroScope implements Mechanism {
    @Override
    public boolean init() {
        imu = hw.get(IMU.class, "imu");
        imu.initialize(
                new IMU.Parameters(
                        new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP
                                ,RevHubOrientationOnRobot.UsbFacingDirection.RIGHT)
                )
        );
        imu.resetYaw();
        return true;
    }
}
