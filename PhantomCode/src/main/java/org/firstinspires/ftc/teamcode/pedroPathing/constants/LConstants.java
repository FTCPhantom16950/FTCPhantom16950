package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class LConstants {
    static {
        ThreeWheelIMUConstants.forwardTicksToInches = 0.003;
        ThreeWheelIMUConstants.strafeTicksToInches = -0.003;
        ThreeWheelIMUConstants.turnTicksToInches = 0.0027;
        ThreeWheelIMUConstants.leftY = 2.8;
        ThreeWheelIMUConstants.rightY = -2.8;
        ThreeWheelIMUConstants.strafeX = -4.9;
        ThreeWheelIMUConstants.leftEncoder_HardwareMapName = "lb";
        ThreeWheelIMUConstants.rightEncoder_HardwareMapName = "rf";
        ThreeWheelIMUConstants.strafeEncoder_HardwareMapName = "rb";
        ThreeWheelIMUConstants.leftEncoderDirection = Encoder.REVERSE;
        ThreeWheelIMUConstants.rightEncoderDirection = Encoder.REVERSE;
        ThreeWheelIMUConstants.strafeEncoderDirection = Encoder.FORWARD;
        ThreeWheelIMUConstants.IMU_HardwareMapName = "imu";
        ThreeWheelIMUConstants.IMU_Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP);
    }
}




