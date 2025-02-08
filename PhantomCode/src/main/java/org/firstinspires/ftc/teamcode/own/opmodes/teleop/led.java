package org.firstinspires.ftc.teamcode.own.opmodes.teleop;

import com.qualcomm.hardware.sparkfun.SparkFunLEDStick;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@TeleOp
public class led extends LinearOpMode {
    SparkFunLEDStick led;
    AnalogInput servo;
    CRServo crServo;
    DigitalChannel dg;
    @Override
    public void runOpMode() throws InterruptedException {
        led = hardwareMap.get(SparkFunLEDStick.class,"led2");
        servo = hardwareMap.get(AnalogInput.class, "led");
        crServo = hardwareMap.get(CRServo.class, "servo");
        dg = hardwareMap.get(DigitalChannel.class, "dg");
        waitForStart();
        Thread thread = new Thread(()->{
            while (true){
                telemetry.addData("info", servo.getVoltage());
                telemetry.addData("info", servo.getMaxVoltage());
                telemetry.addData("info", crServo.getPower());
                telemetry.update();
            }
        });
        thread.start();
        while (opModeIsActive()){
            dg.setMode(DigitalChannel.Mode.OUTPUT);
            led.setBrightness(31);
            led.setColor(3);
            dg.setState(true);
            sleep(1000);
            dg.setState(false);
            sleep(1000);
            crServo.setPower(1);
            crServo.setPower(0);
        }
    }
}
