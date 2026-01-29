package org.firstinspires.ftc.teamcode.own.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.ThreeWheelIMUConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .forwardZeroPowerAcceleration(-24.44)
            .lateralZeroPowerAcceleration(-61.08)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.4,0,0.01,0.01))
            .headingPIDFCoefficients(new PIDFCoefficients(2,0,0.1,0.05))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.1,0,0,0.6,0.02))
            .mass(15.5);

    public static MecanumConstants driveConstants = new MecanumConstants()
            .xVelocity(72)
            .yVelocity(52)
            .maxPower(1)
            .rightFrontMotorName("rf")
            .rightRearMotorName("rb")
            .leftRearMotorName("lb")
            .leftFrontMotorName("lf")
            .leftFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightRearMotorDirection(DcMotorSimple.Direction.REVERSE);

    public static ThreeWheelIMUConstants localizerConstants = new ThreeWheelIMUConstants()
            .forwardTicksToInches(0.00293505883819743138)
            .strafeTicksToInches(0.00305448362069)
            .turnTicksToInches(0.001988890305788905)
            .leftPodY(6.7)
            .rightPodY(-6.7)
            .strafePodX(-6.918)
            .leftEncoder_HardwareMapName("lb")
            .rightEncoder_HardwareMapName("rb")
            .strafeEncoder_HardwareMapName("lf")
            .leftEncoderDirection(Encoder.FORWARD)
            .rightEncoderDirection(Encoder.REVERSE)
            .strafeEncoderDirection(Encoder.REVERSE)
            .IMU_HardwareMapName("imu")
            .IMU_Orientation(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP));
    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);
    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .threeWheelIMULocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .build();
    }
}
