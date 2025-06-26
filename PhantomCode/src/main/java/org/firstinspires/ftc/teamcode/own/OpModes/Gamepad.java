package org.firstinspires.ftc.teamcode.own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.own.Utils.LinearGroup;

public class Gamepad extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotorEx rf,rb,lf,lb;
        rf = hardwareMap.get(DcMotorEx.class,"rf");
        lf = hardwareMap.get(DcMotorEx.class, "lf");
        lb = hardwareMap.get(DcMotorEx.class,"lb");
        rb = hardwareMap.get(DcMotorEx.class, "rb");
        lb.setDirection(DcMotorSimple.Direction.REVERSE);
        lf.setDirection(DcMotorSimple.Direction.REVERSE);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();
        double x,y,spin;
        double rfpower,lfpower,rbpower,lbpower, denominator;
        while(opModeIsActive()){
            x = gamepad1.left_stick_x * 1.1 + gamepad1.right_stick_x * 0.3 * 1.1;
            y = -gamepad1.left_stick_y - gamepad1.right_stick_y * 0.3;
            spin = gamepad1.right_trigger - gamepad1.left_trigger;
            if (gamepad1.right_bumper) {
                spin = 0.4;
            } else if (gamepad1.left_bumper) {
                spin = -0.4;
            }
            denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(spin), 1);
            rfpower = (y - x - spin) / denominator;
            lfpower = (y + x + spin) / denominator;
            rbpower = (y + x - spin) / denominator;
            lbpower = (y - x + spin) / denominator;
            rf.setPower(rfpower);
            lf.setPower(lfpower);
            rb.setPower(rbpower);
            lb.setPower(lbpower);
        }
    }
}
