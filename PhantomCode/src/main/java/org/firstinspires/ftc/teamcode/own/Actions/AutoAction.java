package org.firstinspires.ftc.teamcode.own.Actions;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import com.qualcomm.robotcore.hardware.DcMotor;

public class AutoAction extends Action {

    @Override
    public void execute() {
        if (opMode.opModeIsActive()){
            rb.setPower(1);
            rf.setPower(1);
            lf.setPower(1);
            lb.setPower(1);
            opMode.sleep(500);
            rb.setPower(0);
            rf.setPower(0);
            lf.setPower(0);
            lb.setPower(0);
        }
    }
}
