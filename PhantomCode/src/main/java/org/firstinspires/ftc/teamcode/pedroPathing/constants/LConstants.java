package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class LConstants {
    static {
        ThreeWheelIMUConstants.forwardTicksToInches = 0.003;
        ThreeWheelIMUConstants.strafeTicksToInches = 0.0029;
        ThreeWheelIMUConstants.turnTicksToInches = 0.0027;
        ThreeWheelIMUConstants.leftY = 2.8;
        ThreeWheelIMUConstants.rightY = -2.8;
        ThreeWheelIMUConstants.strafeX = -4.9;
        ThreeWheelIMUConstants.leftEncoder_HardwareMapName = "rf";
        ThreeWheelIMUConstants.rightEncoder_HardwareMapName = "lb";
        ThreeWheelIMUConstants.strafeEncoder_HardwareMapName = "lf";
        ThreeWheelIMUConstants.leftEncoderDirection = Encoder.FORWARD;
        ThreeWheelIMUConstants.rightEncoderDirection = Encoder.REVERSE;
        ThreeWheelIMUConstants.strafeEncoderDirection = Encoder.REVERSE;
        ThreeWheelIMUConstants.IMU_HardwareMapName = "imu";
        ThreeWheelIMUConstants.IMU_Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.LEFT, RevHubOrientationOnRobot.UsbFacingDirection.UP);
    }
}




