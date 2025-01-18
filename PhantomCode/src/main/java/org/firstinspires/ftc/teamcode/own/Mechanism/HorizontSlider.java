package org.firstinspires.ftc.teamcode.own.Mechanism;


import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krutgo;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krutpos;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.zxgo;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.zxpos;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.positions.HorSliderPos;
import org.firstinspires.ftc.teamcode.own.positions.ZxPos;

public class HorizontSlider {
    LinearOpMode opMode;
    public static CRServo sL, sR;
    HardwareMap hw;
    public boolean inited = false;
    HorSliderPos.HorPos horPos;
    public HorizontSlider(LinearOpMode opMode) {
        this.opMode = opMode;
        // this.setDaemon(true);
    }
    public static double startLeftPower = 0, startRightPower = 0, sl_power, sr_power;
    public static boolean horGO = false;
    public void init() {
        hw = opMode.hardwareMap;
        sL = opMode.hardwareMap.get(CRServo.class, "horL");
        sR = opMode.hardwareMap.get(CRServo.class, "horR");
        sL.setPower(startLeftPower);
        sR.setPower(startRightPower);
        sl_power = startLeftPower;
        sr_power = startRightPower;
        inited = true;
        horPos = HorSliderPos.HorPos.SLOZ;
    }
    public void play(){
            if (horPos == HorSliderPos.HorPos.SLOZ && horGO){
                sL.setPower(0- 0.01);
                sR.setPower(0);
                horGO = false;
            } else if(horPos == HorSliderPos.HorPos.VIDVIG && horGO){
                sL.setPower(0.45);
                sR.setPower(-0.45);
                horGO = false;

        }}


    public void moving(){
        play();
        if (opMode.gamepad2.right_stick_button){
            horPos = HorSliderPos.HorPos.SLOZ;
            opMode.sleep(200);
            horGO = true;
        } else if (opMode.gamepad2.left_stick_button){
            horPos = HorSliderPos.HorPos.VIDVIG;
            opMode.sleep(200);
            horGO = true;
        }
//       - 0.05  if (opMode.gamepad2.right_stick_x > 0.1 && !horGO){
//            double previous_power = sl_power;
//            if (previous_power <= opMode.gamepad2.right_stick_x * 0.45){
//                sl_power = opMode.gamepad2.right_stick_x * 0.45;
//            }
//            sr_power = -sl_power;
//            sL.setPower(sl_power);
//            sR.setPower(sr_power - 0.05);
//        } else if (opMode.gamepad2.right_stick_x < 0.1 && !horGO) {
//            double previous_power = sl_power;
//            if (previous_power >= opMode.gamepad2.right_stick_x * 0.45){
//                sl_power = opMode.gamepad2.right_stick_x * 0.45;
//            }
//            sr_power = -sl_power;
//            sL.setPower(sl_power);
//            sR.setPower(sr_power - 0.05);
//        }
        if (opMode.gamepad2.right_trigger!= 0.0){
            vidvig();
        } else if (opMode.gamepad2.left_trigger!= 0.0) {
            zaxvat();
        } else if (opMode.gamepad2.x) {
            horPos = HorSliderPos.HorPos.SLOZ;
            horGO = true;
            zxpos = ZxPos.ZX.ZAXVAT;
            krutpos = ZxPos.KRUT.AUto;
            zxgo = true;
            krutgo = true;
            opMode.sleep(1000);
            krutpos = ZxPos.KRUT.PEREDACHA;
            zxgo = true;
            krutgo = true;
            opMode.sleep(600);
            zxpos = ZxPos.ZX.OTPUSK;
            zxgo = true;
            krutgo = true;
        }
    }

    public void vidvig(){
        horPos = HorSliderPos.HorPos.VIDVIG;
        krutpos = ZxPos.KRUT.ZAXVAT;
        zxpos = ZxPos.ZX.OTPUSK;
        krutgo = true;
        zxgo = true;
        horGO = true;
    }
    public void zaxvat(){
        zxpos = ZxPos.ZX.ZAXVAT;
        horPos = HorSliderPos.HorPos.SLOZ;
        horGO = true;
        krutpos = ZxPos.KRUT.PEREDACHA;
        zxgo = true;
        krutgo = true;
        opMode.sleep(600);
        zxpos = ZxPos.ZX.OTPUSK;
        zxgo = true;
        krutgo = true;
    }

}

