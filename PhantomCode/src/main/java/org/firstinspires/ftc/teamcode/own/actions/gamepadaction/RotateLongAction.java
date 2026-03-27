package org.firstinspires.ftc.teamcode.own.actions.gamepadaction;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.regulators.PIDPoseRegulator;

@Configurable
public class RotateLongAction implements Action {
    public static double kP = 0.1, kI = 0, kD = 0, kF = 0.0015, derFil = 0.7, tolerance = 1;
    PIDPoseRegulator controller = new PIDPoseRegulator(kP, kI, kD, kF, tolerance, derFil);
    double currImu = 0, prevImu = 0;
    DcMotorEx rotate;
    double out = 0;

    @Override
    public void execute() throws InterruptedException {
        rotate = Robot.INSTANCE.getRobotDevice("rotate", DcMotorEx.class);
        IMU imu = Robot.INSTANCE.getRobotDevice("imu", IMU.class);
        currImu = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        double motorCurrPos = rotate.getCurrentPosition() * Robot.COEFFICIENT_TO_DEGREE_LAUNCH;
        while (!Thread.currentThread().isInterrupted()) {
            Robot.INSTANCE.addTelemetryData("motorCurrPose", motorCurrPos);
            motorCurrPos = rotate.getCurrentPosition() * Robot.COEFFICIENT_TO_DEGREE_LAUNCH;
            controller.setkP(kP);
            controller.setkD(kD);
            controller.setkI(kI);
            controller.setkF(kF);
            controller.setTargetPose(prevImu - currImu);
            Robot.INSTANCE.addTelemetryData("delta", prevImu - currImu);
            controller.setCurrentPose(rotate.getCurrentPosition() * Robot.COEFFICIENT_TO_DEGREE_LAUNCH);
            if (out > 0){
                Robot.INSTANCE.addTelemetryData("napr", "plus");
            } else if (out < 0) {
                Robot.INSTANCE.addTelemetryData("napr", "minus");
            }

            // motorCurrPos <= 90 && motorCurrPos >= -180
            if (motorCurrPos <= -89 && (controller.update() < 0)) {
                out = 0;
            } else if (motorCurrPos >= 179 && (controller.update() > 0)) {
                out = 0;
            } else {
                out = controller.update();
            }
            rotate.setPower(out);
            Robot.INSTANCE.addTelemetryData("output", controller.update());
            prevImu = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        }
    }
}
