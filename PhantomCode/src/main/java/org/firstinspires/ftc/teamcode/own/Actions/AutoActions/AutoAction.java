package org.firstinspires.ftc.teamcode.own.Actions.AutoActions;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

public class AutoAction extends Action {

    @Override
    public void execute() {
        if (opMode.opModeIsActive()){
            rb.setPower(0.5);
            rf.setPower(0.5);
            lf.setPower(0.5);
            lb.setPower(0.5);
            opMode.sleep(900);
            rb.setPower(-0.5);
            rf.setPower(-0.5);
            lf.setPower(-0.5);
            lb.setPower(-0.5);
            opMode.sleep(50);
            rb.setPower(0.001);
            rf.setPower(0.001);
            lf.setPower(0.001);
            lb.setPower(0.001);
        }
    }
}
