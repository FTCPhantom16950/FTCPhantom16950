package org.firstinspires.ftc.teamcode.own.actions.util;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.regulators.PIDFilteredController;
import org.firstinspires.ftc.teamcode.own.utils.regulators.PIDPoseRegulator;
import org.firstinspires.ftc.teamcode.own.utils.states.MotifState;

import java.util.List;
@Configurable
public class AprilTagFinder implements Action {
    public static double kP = 0.7,kI = 0,kD = 0.1, kF = 0, derFil = 0.5, tolerance = 1;
    PIDPoseRegulator controller = new PIDPoseRegulator(kP, kI, kD, kF, tolerance, derFil);
    DcMotorEx rotate;
    boolean aprilTagFound = false;
    Limelight3A limelight3A;
    List<LLResultTypes.FiducialResult> aprilTagRes;
    LLResult res;

    @Override
    public void execute() throws InterruptedException {
        ElapsedTime timer = new ElapsedTime();
        limelight3A = Robot.INSTANCE.getRobotDevice("limelight", Limelight3A.class);
        rotate = Robot.INSTANCE.getRobotDevice("rotate", DcMotorEx.class);
        double currPose = rotate.getCurrentPosition();
        IMU imu = Robot.INSTANCE.getRobotDevice("imu", IMU.class);

        timer.reset();

        while (!Thread.currentThread().isInterrupted() && !aprilTagFound && timer.seconds() <= 5) {
            res = limelight3A.getLatestResult();
            double targ = currPose + 750;
            while (rotate.getCurrentPosition() <= targ) {
                res = limelight3A.getLatestResult();
                if (res != null && res.isValid()) {
                    aprilTagRes = res.getFiducialResults();
                    aprilTagFound = true;
                    break;
                }
                Robot.INSTANCE.addTelemetryData("res", res.isValid());
                Robot.INSTANCE.addTelemetryData("timer", timer.seconds());
                Robot.INSTANCE.addTelemetryData("Pos", rotate.getCurrentPosition());
                rotate.setPower(0.3);
            }
            rotate.setPower(0);
            targ = currPose - 750;
            while (rotate.getCurrentPosition() >= targ) {
                res = limelight3A.getLatestResult();
                if (res != null && res.isValid()) {
                    aprilTagRes = res.getFiducialResults();
                    aprilTagFound = true;
                    break;
                }

                Robot.INSTANCE.addTelemetryData("res", res.isValid());
                Robot.INSTANCE.addTelemetryData("timer", timer.seconds());
                Robot.INSTANCE.addTelemetryData("Pos", rotate.getCurrentPosition());
                rotate.setPower(-0.3);
            }
            rotate.setPower(0);
            Robot.INSTANCE.addTelemetryData("timer", timer.seconds());
        }
        if (aprilTagFound && !Thread.currentThread().isInterrupted()) {
            for (LLResultTypes.FiducialResult res : aprilTagRes) {
                Robot.INSTANCE.addTelemetryData("id AprilTag num" + res.getFiducialId(), res.getFiducialId());
                switch (res.getFiducialId()) {
                    // blue
                    case 20 -> {
                        double xDegree = -res.getTargetXDegrees();
                        Robot.startCameraDegree = xDegree;
                        while (!controller.isStop()){
                            Robot.INSTANCE.addTelemetryData("targ", xDegree);
                            Robot.INSTANCE.addTelemetryData("curr degree", Robot.COEFFICIENT_TO_DEGREE_LAUNCH * rotate.getCurrentPosition());
                            controller.setkP(kP);
                            controller.setkD(kD);
                            controller.setkI(kI);
                            controller.setTargetPose(xDegree);
                            controller.setCurrentPose(rotate.getCurrentPosition() * Robot.COEFFICIENT_TO_DEGREE_LAUNCH);
                            rotate.setPower(controller.update());
                            Robot.INSTANCE.addTelemetryData("output", controller.update());
                        }
                        Robot.INSTANCE.addTelemetryData("CameraPosition", Robot.startCameraDegree);
                        double imust = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
                        sleep(5000);
                        double deltaImu = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES) - imust;
                        Robot.INSTANCE.addTelemetryData("delta", deltaImu);
                        controller.setkP(kP);
                        controller.setkD(kD);
                        controller.setkI(kI);
                        controller.setTargetPose(deltaImu);
                        controller.setCurrentPose(rotate.getCurrentPosition() * Robot.COEFFICIENT_TO_DEGREE_LAUNCH);
                        rotate.setPower(controller.update());
                        while (!controller.isStop()){
                            Robot.INSTANCE.addTelemetryData("targ", xDegree);
                            Robot.INSTANCE.addTelemetryData("curr degree", Robot.COEFFICIENT_TO_DEGREE_LAUNCH * rotate.getCurrentPosition());
                            controller.setkP(kP);
                            controller.setkD(kD);
                            controller.setkI(kI);
                            controller.setTargetPose(xDegree);
                            controller.setCurrentPose(rotate.getCurrentPosition() * Robot.COEFFICIENT_TO_DEGREE_LAUNCH);
                            rotate.setPower(controller.update());
                            Robot.INSTANCE.addTelemetryData("output", controller.update());
                        }
                    }
                    // first
                    case 21 -> {
                        Robot.motif = MotifState.LEFT;
                        Robot.INSTANCE.addTelemetryData("Motif curr ", Robot.motif);
                    }
                    //second
                    case 22 -> {
                        Robot.motif = MotifState.CENTER;
                        Robot.INSTANCE.addTelemetryData("Motif curr ", Robot.motif);
                    }
                    // third
                    case 23 -> {
                        Robot.motif = MotifState.RIGHT;
                        Robot.INSTANCE.addTelemetryData("Motif curr ", Robot.motif);
                    }
                    // red
                    case 24 -> {
                        Robot.INSTANCE.addTelemetryData("x ", res.getTargetXPixels());
                        Robot.INSTANCE.addTelemetryData("y ", res.getTargetYPixels());
                        Robot.INSTANCE.addTelemetryData("xDegree ", res.getTargetXDegrees());

                    }
                }
            }
        }
    }
}
