package org.firstinspires.ftc.teamcode.pedroPathing.pedroPathing;

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

    public static MecanumConstants driveConstants = new MecanumConstants()
            .leftFrontMotorName("lf")
            .leftRearMotorName("lb")
            .rightFrontMotorName("rf")
            .rightRearMotorName("rb")
            .xVelocity(62.91)
            .yVelocity(29.5)
            .leftFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightRearMotorDirection(DcMotorSimple.Direction.REVERSE);

    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(18.5)
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.0, 0.0, 0.0, 0.0, 0.0))
            .translationalPIDFCoefficients(new PIDFCoefficients(0.15,0,0.02,0.04))
            .headingPIDFCoefficients(new PIDFCoefficients(1.05,0,0.1,0.03))
            .useSecondaryHeadingPIDF(true)
            .secondaryHeadingPIDFCoefficients(new PIDFCoefficients(1.3,0,0,0))
            .secondaryTranslationalPIDFCoefficients(new PIDFCoefficients(0.18,0,0,0))
            .useSecondaryTranslationalPIDF(true);


    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static ThreeWheelIMUConstants threeWheelIMUConstants = new ThreeWheelIMUConstants()
            .forwardTicksToInches(0.0028)
            .turnTicksToInches(0.0028)
            .strafeTicksToInches(0.00197)
            .leftPodY(-6.79)
            .rightPodY(6.79)
            .strafePodX(6.4)
            .leftEncoder_HardwareMapName("lb")
            .rightEncoder_HardwareMapName("rb")
            .strafeEncoder_HardwareMapName("lf")
            .leftEncoderDirection(Encoder.FORWARD)
            .rightEncoderDirection(Encoder.REVERSE)
            .strafeEncoderDirection(Encoder.FORWARD)
            .IMU_HardwareMapName("imu")
            .IMU_Orientation( new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .threeWheelIMULocalizer(threeWheelIMUConstants)
                .build();
    }

}
