package org.firstinspires.ftc.teamcode.pedroPathing;

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

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(18.5)
            .centripetalScaling(0.00005)
            .forwardZeroPowerAcceleration(-26)
            .lateralZeroPowerAcceleration(-59)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.6,0,0.05,0))
            .headingPIDFCoefficients(new PIDFCoefficients(1.5,0,0.13,0))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(1,0,0,0.5,0.008))
            .useSecondaryTranslationalPIDF(true)
            .useSecondaryHeadingPIDF(true)
            .useSecondaryDrivePIDF(false)
            .secondaryTranslationalPIDFCoefficients(new PIDFCoefficients(0.42,0,0.04,0.02))
            .secondaryHeadingPIDFCoefficients(new PIDFCoefficients(2,0,0.01,0.03))
            .secondaryDrivePIDFCoefficients(new FilteredPIDFCoefficients(0,0,0,0,0));

    public static MecanumConstants driveConstants = new MecanumConstants()
            .xVelocity(70)
            .yVelocity(49)
            .maxPower(1)
            .rightFrontMotorName("rf")
            .rightRearMotorName("rb")
            .leftRearMotorName("lb")
            .leftFrontMotorName("lf")
            .leftFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightRearMotorDirection(DcMotorSimple.Direction.REVERSE);
    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);
    public static ThreeWheelIMUConstants localizerConstants = new ThreeWheelIMUConstants()
            .forwardTicksToInches(0.003)
            .strafeTicksToInches(0.003)
            .turnTicksToInches(0.00197)
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
            .IMU_Orientation(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP));
    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .threeWheelIMULocalizer(localizerConstants)
                .build();
    }
}
