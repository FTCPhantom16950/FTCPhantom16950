package org.firstinspires.ftc.teamcode.own.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.ThreeWheelConstants;
import com.pedropathing.ftc.localization.constants.ThreeWheelIMUConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .forwardZeroPowerAcceleration(-24.44)
            .lateralZeroPowerAcceleration(-61.08)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.18,0,0.02,0.01))
            .headingPIDFCoefficients(new PIDFCoefficients(3.25,0,0.295,0.03))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.015,0,0,0.6,0.0015))
            .useSecondaryTranslationalPIDF(true)
            .secondaryTranslationalPIDFCoefficients(new PIDFCoefficients(0.4,0,0.02,0.01))
            .centripetalScaling(0.0005)
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

    public static ThreeWheelConstants localizerConstants = new ThreeWheelConstants()
            .forwardTicksToInches(0.003)
            .strafeTicksToInches(0.00293)
            .turnTicksToInches(0.00197)
            .leftPodY(6.56)
            .rightPodY(-6.56)
            .strafePodX(-6.4)
            .leftEncoder_HardwareMapName("lb")
            .rightEncoder_HardwareMapName("rb")
            .strafeEncoder_HardwareMapName("lf")
            .leftEncoderDirection(Encoder.FORWARD)
            .rightEncoderDirection(Encoder.REVERSE)
            .strafeEncoderDirection(Encoder.FORWARD);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);
    public static Follower createFollower(HardwareMap hardwareMap)throws RuntimeException {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .threeWheelLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .build();
    }
}
