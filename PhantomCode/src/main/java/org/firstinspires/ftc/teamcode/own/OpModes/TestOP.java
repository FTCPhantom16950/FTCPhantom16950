package org.firstinspires.ftc.teamcode.own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TestOP extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotorEx dcMotorEx = hardwareMap.get(DcMotorEx.class, "motor"),
        dcMotorEx1 = hardwareMap.get(DcMotorEx.class, "motor1");
        dcMotorEx.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        dcMotorEx1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double i = 0;
//        dcMotorEx.setDirection(DcMotorSimple.Direction.REVERSE);
        while(opModeInInit()){
            if(gamepad1.a){
                i +=0.1;
                sleep(300);
            } else if (gamepad1.b) {
                i -= 0.1;
                sleep(300);
            }
            dcMotorEx1.setPower(i);
            dcMotorEx.setPower(i);
            telemetry.addData("power", dcMotorEx.getPower());
            telemetry.addData("power", dcMotorEx1.getPower());
            telemetry.update();
        }
        waitForStart();
    }
}
