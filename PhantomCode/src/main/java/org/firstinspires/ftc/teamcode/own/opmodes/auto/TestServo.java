package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.ServoImplEx;

@Autonomous
public class TestServo extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        CRServo servo = hardwareMap.get(CRServo.class, "servo"),
        servo1 = hardwareMap.get(CRServo.class,"servo1");
        waitForStart();
        Thread thread = new Thread(() ->{
            while (opModeIsActive()){
                telemetry.addData("power", servo.getPower());
                telemetry.addData("power1", servo1.getPower());
                telemetry.update();
            }
        });
        thread.start();
        while (opModeIsActive()){
            servo.setPower(1);
            servo1.setPower(1);
            sleep(3000);
            servo.setPower(-1);
            servo1.setPower(-1);
            sleep(3000);
        }
    }
}
