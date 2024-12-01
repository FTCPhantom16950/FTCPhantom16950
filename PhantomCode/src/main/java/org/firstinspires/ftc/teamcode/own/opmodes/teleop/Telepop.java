package org.firstinspires.ftc.teamcode.own.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
@Disabled
@TeleOp
public class Telepop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        CRServo servo1 = hardwareMap.crservo.get("klesh");
        CRServo servo2 = hardwareMap.crservo.get("vraah");
        CRServo servo3 = hardwareMap.crservo.get("zx");
        CRServo servo4 = hardwareMap.crservo.get("krut");
        servo1.setPower(0);
        servo2.setPower(0);
        servo3.setPower(0);
        servo4.setPower(0);
        waitForStart();
        if (opModeIsActive()){
//
        }
    }
}
