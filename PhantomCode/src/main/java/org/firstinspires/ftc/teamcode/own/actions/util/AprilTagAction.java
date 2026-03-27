package org.firstinspires.ftc.teamcode.own.actions.util;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

import java.util.List;

public class AprilTagAction implements Action {
    Limelight3A limelight3A;
    List<LLResultTypes.FiducialResult> aprilTagRes;
    LLResult res;
    @Override
    public void execute() throws InterruptedException {
        limelight3A = Robot.INSTANCE.getRobotDevice("limelight", Limelight3A.class);
        IMU imu = Robot.INSTANCE.getRobotDevice("imu", IMU.class);
        while (!Thread.currentThread().isInterrupted()){
            res = limelight3A.getLatestResult();
            aprilTagRes = res.getFiducialResults();
            for (LLResultTypes.FiducialResult fidRes : aprilTagRes){
                Robot.INSTANCE.addTelemetryData("xDegree", fidRes.getTargetXDegrees());
                Robot.INSTANCE.addTelemetryData("yDegree", fidRes.getTargetYDegrees());
                Robot.INSTANCE.addTelemetryData("imuRoll", imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.DEGREES));
                Robot.INSTANCE.addTelemetryData("imu Yaw", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
                Robot.INSTANCE.addTelemetryData("imu Pitch", imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES));

            }
        }
    }
}
