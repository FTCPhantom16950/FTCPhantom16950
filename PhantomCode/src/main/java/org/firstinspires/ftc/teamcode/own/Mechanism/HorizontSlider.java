package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.Config;

public class HorizontSlider extends Thread {
    LinearOpMode opMode;
    CRServo sL, sR;
    HardwareMap hw;

    public HorizontSlider(LinearOpMode opMode){
        this.opMode = opMode;
    }
    Config config = new Config();
    boolean AUTOMODE = config.isAUTOMODE();
    double startLeftPower = 0, startRightPower = 0, i = 0, sl_power, sr_power;
    public void init() {
        hw = opMode.hardwareMap;
        sL = opMode.hardwareMap.get(CRServo.class, "horL");
        sR = opMode.hardwareMap.get(CRServo.class, "horR");
        sL.setPower(startLeftPower);
        sR.setPower(startRightPower);
    }
    @Override
    public void run(){
        AUTOMODE = config.isAUTOMODE();
        sl_power = i;
        sr_power = -i;
        sL.setPower(sl_power);
        sR.setPower(sr_power);
        if (opMode.gamepad2.right_trigger != 0.0 && i < 0.6 && !AUTOMODE){
            i += opMode.gamepad2.right_trigger * 0.05;
        } else if (i >= 0.6){
            i = 0.6;
        }
        if (opMode.gamepad2.left_trigger != 0.0 && i > 0 && !AUTOMODE){
            i -= opMode.gamepad2.left_trigger * 0.05;
        }
        else if (i <= 0){
            i = 0;
        }
        if (opMode.gamepad2.left_stick_button && AUTOMODE) {
            i = startLeftPower;
        } else if (opMode.gamepad2.right_stick_button && AUTOMODE) {
            i = 0.6;
        }

    }
}

