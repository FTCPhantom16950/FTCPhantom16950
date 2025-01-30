package org.firstinspires.ftc.teamcode.own.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.LED;
@Disabled

@TeleOp
public class LEDTEST extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        LED led = hardwareMap.led.get("led");
        waitForStart();
        while (opModeIsActive()){
            if (gamepad1.a){
                led.on();
            } else if (gamepad1.y){
                led.off();
            } else if (gamepad1.b) {
                led.enable(true);
            } else if (gamepad1.x) {
                led.enable(false);
            }
        }
    }
}
