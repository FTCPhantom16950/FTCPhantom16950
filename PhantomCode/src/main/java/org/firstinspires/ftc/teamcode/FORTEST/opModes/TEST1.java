package org.firstinspires.ftc.teamcode.FORTEST.opModes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Mechanism.LynxModule;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;

@TeleOp
public class TEST1 extends LinearOpMode {
    ElapsedTime time = new ElapsedTime();
    LynxModule lynxModule = new LynxModule();
    WheelBase wheelBase = new WheelBase(this);
    CRServo servo;
    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(CRServo.class, "servo");
        lynxModule.init_Lynx(hardwareMap);
        wheelBase.initWheelBase(hardwareMap);
        //wheelBase.gamepads.start();
        waitForStart();
        time.reset();
        while(opModeIsActive()){
            wheelBase.driveEasy(gamepad1);
            if (gamepad1.a){
                servo.setPower(0.2);
                servo.setPower(1);
            } else if (gamepad1.b){
                servo.setPower(-0.2);
                servo.setPower(1);
            }
        }

    }
}
