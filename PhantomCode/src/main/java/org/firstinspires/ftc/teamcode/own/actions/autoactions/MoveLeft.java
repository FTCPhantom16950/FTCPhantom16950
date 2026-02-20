package org.firstinspires.ftc.teamcode.own.actions.autoactions;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;

public class MoveLeft implements Action {
    @Override
    public void execute() throws InterruptedException {
        SfMotor rf = Robot.INSTANCE.get(SfMotor.class, "rf"),
                rb = Robot.INSTANCE.get(SfMotor.class, "rb"),
                lb = Robot.INSTANCE.get(SfMotor.class, "lb"),
                lf = Robot.INSTANCE.get(SfMotor.class, "lf");
        if (Robot.INSTANCE.opMode.opModeIsActive()){
            double power = -0.3;
            rf.setPower(-power);
            lf.setPower(power);
            lb.setPower(-power);
            rb.setPower(power);
            Robot.INSTANCE.opMode.sleep(850);
            power = 0;
            rf.setPower(-power);
            lf.setPower(power);
            lb.setPower(-power);
            rb.setPower(-power);
        }
    }
}
