package org.firstinspires.ftc.teamcode.own.actions.auto;

import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.psilynx.psikit.core.wpi.math.Pose3d;
import org.psilynx.psikit.core.wpi.math.Rotation3d;

public class ImuLogAction implements Action {
    @Override
    public void execute() throws InterruptedException {
        IMU imu = Robot.INSTANCE.getRobotDevice("imu", IMU.class);
        while(!Thread.currentThread().isInterrupted()){
            Robot.INSTANCE.addTelemetryData("Pose", imu.getRobotYawPitchRollAngles().getYaw());
            Robot.INSTANCE.addLoggerData("POSE", new Pose3d(0,0,0,new Rotation3d(imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.RADIANS), imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.RADIANS), imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS))));
        }
    }
}
