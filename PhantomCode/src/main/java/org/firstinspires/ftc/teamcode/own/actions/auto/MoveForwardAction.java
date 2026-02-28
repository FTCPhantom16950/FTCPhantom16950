package org.firstinspires.ftc.teamcode.own.actions.auto;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class MoveForwardAction implements Action {
    DcMotorEx lf, lb, rf, rb;


    @Override
    public void execute() throws InterruptedException {
        lf = Robot.INSTANCE.getRobotDevice("lf", DcMotorEx.class);
        lb = Robot.INSTANCE.getRobotDevice("lb", DcMotorEx.class);
        rf = Robot.INSTANCE.getRobotDevice("rf", DcMotorEx.class);
        rb = Robot.INSTANCE.getRobotDevice("rb", DcMotorEx.class);
        while (!Thread.currentThread().isInterrupted()){
            lf.setPower(1);
            lb.setPower(1);
            rf.setPower(1);
            rb.setPower(1);
            sleep(300);
            lf.setPower(0);
            lb.setPower(0);
            rf.setPower(0);
            rb.setPower(0);
        }
    }
}
