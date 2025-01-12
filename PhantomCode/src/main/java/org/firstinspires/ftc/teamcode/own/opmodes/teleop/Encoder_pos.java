package org.firstinspires.ftc.teamcode.own.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class Encoder_pos extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotorEx rf, lf, rb, lb;
        rb = hardwareMap.get(DcMotorEx.class, "rb");
        lb = hardwareMap.get(DcMotorEx.class, "lb");
        rf = hardwareMap.get(DcMotorEx.class, "rf");
        lf = hardwareMap.get(DcMotorEx.class, "lf");
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();
        while (opModeIsActive()){
            if (gamepad2.a){
                rb.setPower(0.5);
            }
            if (gamepad2.y){
                rf.setPower(0.5);
            }
            if (gamepad2.x){
                lb.setPower(0.5);
            }
            if (gamepad2.b){
                lf.setPower(0.5);
            }
            telemetry.addData("rb pos", rb.getCurrentPosition());
            telemetry.addData("lb pos", lb.getCurrentPosition());
            telemetry.addData("rf pos", rf.getCurrentPosition());
            telemetry.addData("lf pos", lf.getCurrentPosition());
            telemetry.update();
            sleep(3000);
        }
    }
}
