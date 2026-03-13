package org.firstinspires.ftc.teamcode.own.actions.util;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

public class EncoderCheckerAction implements Action {
    DcMotorEx lf, lb, rf, rb;
    Gamepad gamepad1;

    @Override
    public void execute() throws InterruptedException {
        gamepad1 = Robot.INSTANCE.getRobotData("Gamepad1", Gamepad.class);
        lf = Robot.INSTANCE.getRobotDevice("lf", DcMotorEx.class);
        lb = Robot.INSTANCE.getRobotDevice("lb", DcMotorEx.class);
        rf = Robot.INSTANCE.getRobotDevice("rf", DcMotorEx.class);
        rb = Robot.INSTANCE.getRobotDevice("rb", DcMotorEx.class);
        while (!Thread.currentThread().isInterrupted()){
            if (gamepad1.y){
                lf.setPower(1);
            }
            else if (gamepad1.a){
                lb.setPower(1);
            }
            else if (gamepad1.x){
                rf.setPower(1);
            }
            else if (gamepad1.b){
                rb.setPower(1);
            } else {
                lf.setPower(0);
                lb.setPower(0);
                rf.setPower(0);
                rb.setPower(0);
            }
            Robot.INSTANCE.addTelemetryData("lfEncoder", lf.getCurrentPosition());
            Robot.INSTANCE.addTelemetryData("lbEncode", lb.getCurrentPosition());
            Robot.INSTANCE.addTelemetryData("rfEncoder", rf.getCurrentPosition());
            Robot.INSTANCE.addTelemetryData("rbEncoder", rb.getCurrentPosition());
        }
    }
}
