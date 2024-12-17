package org.firstinspires.ftc.teamcode.own.Mechanism;

import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrash;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrashPower;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krut;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.zx;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.Config;

public class HorizontSlider {
    LinearOpMode opMode;
    public static CRServo sL, sR;
    HardwareMap hw;

    public HorizontSlider(LinearOpMode opMode){
        this.opMode = opMode;
       // this.setDaemon(true);
    }

    public double startLeftPower = 0, startRightPower = 0, i = 0, sl_power, sr_power;
    public void init() {
        hw = opMode.hardwareMap;
        sL = opMode.hardwareMap.get(CRServo.class, "horL");
        sR = opMode.hardwareMap.get(CRServo.class, "horR");
        sL.setPower(startLeftPower);
        sR.setPower(startRightPower);
        sl_power = startLeftPower;
        sr_power = startRightPower;
    }

//    @Override
//    public synchronized void start() {
//        super.start();
//        if (opMode.opModeInInit()){
//            init();
//        }
//        while(opMode.opModeIsActive()){
//            sl_power = i;
//            sr_power = -i;
//            sL.setPower(sl_power);
//            sR.setPower(sr_power);
//            manualMoving();
//            autoMoving();
//        }
//
//    }
    public void run_wiithout(){
        sl_power = i;
        sr_power = -i;
        sL.setPower(sl_power);
        sR.setPower(sr_power);
    }
    public void manualMoving(){
        if (opMode.gamepad2.right_trigger != 0.0 && i < 0.3){
            i += opMode.gamepad2.right_trigger * 0.05;
        } else if (i >= 0.3){
            i = 0.3;
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
        }
        if (opMode.gamepad2.right_stick_button){
            i = 0.3;
            opMode.sleep(100);
            krut.setPower(-0.95);
            Zx.i = 1;
        }
        if (opMode.gamepad2.x){
            Zx.i = 0;
            opMode.sleep(100);
            Zx.g = 0.3;
            opMode.sleep(100);
            i = startLeftPower;
            opMode.sleep(100);
            Zx.g = 0.5;
            opMode.sleep(100);
            Zx.i = 1;
            opMode.sleep(100);
            Zx.i = 0;
            Zx.g = 0.3;
            opMode.sleep(100);
            VerticalSlider.kleshPower = -0.3;
            vrashPower = -0.8;
            opMode.sleep(100);
            VerticalSlider.kleshPower = 0;
            opMode.sleep(100);
            vrashPower = 0;
        }
    }
}

