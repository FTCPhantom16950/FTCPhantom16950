package org.firstinspires.ftc.teamcode.own.actions.auto;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.MotifState;

import java.util.Collections;
import java.util.List;

public class MotifFinder implements Action {
    List<LLResultTypes.FiducialResult> aprilTagRes;
    LLResult res;
    boolean aprilTagFound = false;

    @Override
    public void execute() throws InterruptedException {
        Limelight3A limelight3A = Robot.INSTANCE.getRobotDevice("limelight", Limelight3A.class);
        DcMotorEx rotate = Robot.INSTANCE.getRobotDevice("rotate", DcMotorEx.class);
        double currPose = rotate.getCurrentPosition();

        if (!Thread.currentThread().isInterrupted()) {
            res = limelight3A.getLatestResult();
            double targ = currPose + 750;
            while (rotate.getCurrentPosition() <= targ) {
                res = limelight3A.getLatestResult();
                if (res != null && res.isValid()) {
                    aprilTagRes = res.getFiducialResults();
                    aprilTagFound = true;
                    Robot.INSTANCE.addTelemetryData("res", res.isValid());

                    Robot.INSTANCE.addTelemetryData("Pos", rotate.getCurrentPosition());
                    rotate.setPower(0);
                    break;
                }
                Robot.INSTANCE.addTelemetryData("res", res.isValid());

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

                Robot.INSTANCE.addTelemetryData("Pos", rotate.getCurrentPosition());
                rotate.setPower(-0.3);
            }
            rotate.setPower(0);

        }
        if (aprilTagFound && aprilTagRes != null){

            for (LLResultTypes.FiducialResult result : aprilTagRes){
                switch (result.getFiducialId()){
                    case 21 -> {
                        Robot.motif = MotifState.LEFT;
                    }
                    case 22 -> {
                        Robot.motif = MotifState.CENTER;
                    }
                    case 23 -> {
                        Robot.motif = MotifState.RIGHT;
                    }
                }
            }
            Robot.INSTANCE.addTelemetryData("MOTIF", Robot.motif);
        }
    }
}
