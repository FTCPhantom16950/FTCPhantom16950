package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.Config;

public class HorizontSlider extends Thread {
    LinearOpMode opMode;
    public CRServo sL, sR, zx, krut;
    HardwareMap hw;

    public HorizontSlider(LinearOpMode opMode){
        this.opMode = opMode;
        this.setDaemon(true);
    }

    public double startLeftPower = 0, startRightPower = 0, i = 0, sl_power, sr_power;
    public void init() {
        hw = opMode.hardwareMap;
        sL = opMode.hardwareMap.get(CRServo.class, "horL");
        krut= opMode.hardwareMap.get(CRServo.class, "krut");
        sR = opMode.hardwareMap.get(CRServo.class, "horR");
        zx = opMode.hardwareMap.get(CRServo.class, "zx");
        sL.setPower(startLeftPower);
        sR.setPower(startRightPower);
        sl_power = startLeftPower;
        sr_power = startRightPower;
    }

    @Override
    public synchronized void start() {
        super.start();
        if (opMode.opModeInInit()){
            init();
        }
        while(opMode.opModeIsActive()){
            sl_power = i;
            sr_power = -i;
            sL.setPower(sl_power);
            sR.setPower(sr_power);
            manualMoving();
            autoMoving();
        }

    }
    public void run_wiithout(){
        sl_power = i;
        sr_power = -i;
        sL.setPower(sl_power);
        sR.setPower(sr_power);
    }
    public void manualMoving(){
        if (opMode.gamepad2.right_trigger != 0.0 && i < 0.4){
            i += opMode.gamepad2.right_trigger * 0.05;
        } else if (i >= 0.4){
            i = 0.4;
        }
        if (opMode.gamepad2.left_trigger != 0.0 && i > 0){
            i -= opMode.gamepad2.left_trigger * 0.05;
        }
        else if (i <= 0){
            i = 0;
        }
    }
    public void autoMoving(){
        if (opMode.gamepad2.left_stick_button) {
            i = startLeftPower;
        } else if (opMode.gamepad2.right_stick_button) {
            i = 0.4;
        }
        if (opMode.gamepad2.x){
            zx.setPower(0);
            opMode.sleep(100);
            krut.setPower(0.5);
            opMode.sleep(100);
            i = 0;
        }
    }
}

