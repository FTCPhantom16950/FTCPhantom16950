package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

public class HorizontSlider extends Thread {
    LinearOpMode opMode;
    CRServo slider;
    double i = 0;
    public HorizontSlider(LinearOpMode opMode) {
        this.opMode = opMode;
    }
    public void init(){
        slider = opMode.hardwareMap.get(CRServo.class, "horizon");
        slider.setPower(0);
    }
    @Override
    public void run(){
        if (opMode.gamepad2.dpad_left && i <= 0.6){
            slider.setPower(i);
            i = i + 0.1;
        } else if (opMode.gamepad2.dpad_right && i > -1){
            slider.setPower(i);
            i = i - 0.1;
        } else if(opMode.gamepad2.right_stick_button){
            slider.setPower(0.6);
        } else if (opMode.gamepad2.left_stick_button){
            slider.setPower(0);
        }

    }


}
