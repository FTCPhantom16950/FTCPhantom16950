package org.firstinspires.ftc.teamcode.own.actions.util;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class LimelightAction implements Action {
    Limelight3A limelight3A;
    IMU imu;
    @Override
    public void execute() throws InterruptedException {
        limelight3A = Robot.INSTANCE.getRobotDevice("Limelight", Limelight3A.class);
        imu = Robot.INSTANCE.getRobotDevice("imu", IMU.class);
        if(!Thread.currentThread().isInterrupted()){
            LLResult res = limelight3A.getLatestResult();
            double robotYaw = imu.getRobotYawPitchRollAngles().getYaw();
            limelight3A.updateRobotOrientation(robotYaw);
            if (res != null && res.isValid()){
                Pose3D botPose = res.getBotpose_MT2();
                if (botPose != null){
                    Robot.INSTANCE.addTelemetryData("X", botPose.getPosition().x);
                    Robot.INSTANCE.addTelemetryData("Y", botPose.getPosition().y);
                }
            }
        }
    }
}
