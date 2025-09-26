package org.firstinspires.ftc.teamcode.pedropathing;

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
            .mass(12)
            .forwardZeroPowerAcceleration(-34.90168374138196)
            .lateralZeroPowerAcceleration(-92.07477745019709)
            .centripetalScaling(0.0001)
            .automaticHoldEnd(true);

    public static MecanumConstants driveConstants = new MecanumConstants()
            .leftFrontMotorName("lf")
            .leftRearMotorName("lb")
            .rightFrontMotorName("rf")
            .rightRearMotorName("rb")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .xVelocity(73.13207972253893)
            .yVelocity(50.607968786099335);

    public static ThreeWheelIMUConstants localizerConstants =
            new ThreeWheelIMUConstants()
                    .forwardTicksToInches(0.003)
                    .strafeTicksToInches(0.0029)
                    .turnTicksToInches(0.0027)
                    .leftPodY(2.8)
                    .rightPodY(-2.8)
                    .strafePodX(-4.9)
                    .leftEncoder_HardwareMapName("rf")
                    .rightEncoder_HardwareMapName("lb")
                    .strafeEncoder_HardwareMapName("lf")
                    .leftEncoderDirection(Encoder.FORWARD)
                    .rightEncoderDirection(Encoder.REVERSE)
                    .strafeEncoderDirection(Encoder.REVERSE)
                    .IMU_HardwareMapName("imu")
                    .IMU_Orientation(
                            new RevHubOrientationOnRobot(
                                    RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                                    RevHubOrientationOnRobot.UsbFacingDirection.UP
                            )
                    );

    public static PathConstraints pathConstraints = new PathConstraints(
            0.995,
            0.1,
            0.1,
            0.007,
            300,
            4,
            10,
            1
    );

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .mecanumDrivetrain(driveConstants)
                .threeWheelIMULocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .build();
    }
}