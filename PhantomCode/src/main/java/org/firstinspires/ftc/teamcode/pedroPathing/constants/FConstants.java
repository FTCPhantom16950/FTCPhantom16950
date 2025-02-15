package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.localization.Localizers;
import com.pedropathing.util.KalmanFilterParameters;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class FConstants {
    private double RobotWidth = 14.6, RobotLength = 17.2;
    static {
        FollowerConstants.localizers = Localizers.THREE_WHEEL_IMU;

        FollowerConstants.leftFrontMotorName = "lf";
        FollowerConstants.leftRearMotorName = "lb";
        FollowerConstants.rightFrontMotorName = "rf";
        FollowerConstants.rightRearMotorName = "rb";

        FollowerConstants.leftFrontMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.leftRearMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.rightRearMotorDirection = DcMotorSimple.Direction.FORWARD;

        FollowerConstants.mass = 12;

        FollowerConstants.xMovement = 73.13207972253893;
        FollowerConstants.yMovement = 50.607968786099335;

        FollowerConstants.forwardZeroPowerAcceleration = -34.90168374138196;
        FollowerConstants.lateralZeroPowerAcceleration = -92.07477745019709;

        FollowerConstants.translationalPIDFCoefficients.setCoefficients(0.11,0,0.03,0.015);
        FollowerConstants.useSecondaryTranslationalPID = false;
        FollowerConstants.secondaryTranslationalPIDFCoefficients.setCoefficients(0.25,0,0.02,0.05); // Not being used, @see useSecondaryTranslationalPID

        FollowerConstants.headingPIDFCoefficients.setCoefficients(
                2,
                0,
                0.2,
                0.05);
        FollowerConstants.useSecondaryHeadingPID = true;
        FollowerConstants.secondaryHeadingPIDFCoefficients.setCoefficients(1.1,0,0.1,0.1); // Not being used, @see useSecondaryHeadingPID

        FollowerConstants.drivePIDFCoefficients.setCoefficients(0.0023,0,0.00008 ,0.6,0.01);
        FollowerConstants.useSecondaryDrivePID = false;
        FollowerConstants.secondaryDrivePIDFCoefficients.setCoefficients(0.1,0,0,0.6,0); // Not being used, @see useSecondaryDrivePID

        FollowerConstants.zeroPowerAccelerationMultiplier = 4;
        FollowerConstants.centripetalScaling = 0.0001;

        FollowerConstants.pathEndTimeoutConstraint = 300;
        FollowerConstants.pathEndTValueConstraint = 0.995;
        FollowerConstants.pathEndVelocityConstraint = 0.1;
        FollowerConstants.pathEndTranslationalConstraint = 0.1;
        FollowerConstants.pathEndHeadingConstraint = 0.007;

        FollowerConstants.useVoltageCompensationInAuto = true;
        FollowerConstants.useVoltageCompensationInTeleOp = true;
        FollowerConstants.nominalVoltage = 13.5;
        FollowerConstants.cacheInvalidateSeconds = 0.5;
        FollowerConstants.automaticHoldEnd = true;
    }
}
