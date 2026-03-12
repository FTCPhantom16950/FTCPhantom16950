package org.firstinspires.ftc.teamcode.own.actions.util;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class EncoderCheckerAction implements Action {
    DcMotorEx lf, lb, rf, rb;

    @Override
    public void execute() throws InterruptedException {
        lf = Robot.INSTANCE.getRobotDevice("lf", DcMotorEx.class);
        lb = Robot.INSTANCE.getRobotDevice("lb", DcMotorEx.class);
        rf = Robot.INSTANCE.getRobotDevice("rf", DcMotorEx.class);
        rb = Robot.INSTANCE.getRobotDevice("rb", DcMotorEx.class);
        while (!Thread.currentThread().isInterrupted()){
            Robot.INSTANCE.addTelemetryData("lfEncoder", lf.getCurrentPosition());
            Robot.INSTANCE.addTelemetryData("lbEncode", lb.getCurrentPosition());
            Robot.INSTANCE.addTelemetryData("rfEncoder", rf.getCurrentPosition());
            Robot.INSTANCE.addTelemetryData("rbEncoder", rb.getCurrentPosition());
        }
    }
}
