package org.firstinspires.ftc.teamcode.own.actions.utilactions;

import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.teamcode.own.utils.Motif;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

import java.util.List;

public class LimelightAction implements Action {
    Limelight3A limelight3A;

    @Override
    public void execute() throws InterruptedException {
        limelight3A = Robot.INSTANCE.get(Limelight3A.class, "LimeLight");
        while (Robot.INSTANCE.opMode.opModeIsActive()) {
            List<LLResultTypes.FiducialResult> res = limelight3A.getLatestResult().getFiducialResults();
            for (LLResultTypes.FiducialResult result : res){
                int id = result.getFiducialId();
                if (id == 21){
                    Robot.INSTANCE.motif = Motif.FIRST;
                    break;
                } else if (id == 22) {
                    Robot.INSTANCE.motif = Motif.SECOND;
                    break;
                } else if (id == 23) {
                    Robot.INSTANCE.motif = Motif.THIRD;
                    break;
                }
            }
            if (Robot.INSTANCE.motif != Motif.UNKNOWN){
                limelight3A.close();
                Robot.INSTANCE.addData("Motif", Robot.INSTANCE.motif);
                break;
            }

        }
    }
}
