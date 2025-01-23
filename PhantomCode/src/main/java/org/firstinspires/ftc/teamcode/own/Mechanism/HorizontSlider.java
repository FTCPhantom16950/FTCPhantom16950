package org.firstinspires.ftc.teamcode.own.Mechanism;


import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.klesh;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.brat;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.brat2;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krut2_start_power;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krut_start_power;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krutgo;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krutpos;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.zx;
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
    static HorSliderPos.HorPos horPos;
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
                sL.setPower(0.48);
                sR.setPower(-0.48);
                horGO = false;

        }
    }


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
        }
    }

    public  void vidvig(){
        horPos = HorSliderPos.HorPos.VIDVIG;
        krutpos = ZxPos.KRUT.ZAXVAT;
        zxpos = ZxPos.ZX.OTPUSK;
        krutgo = true;
        zxgo = true;
        horGO = true;
    }
    public static void vidvigAuto(){
        sL.setPower(0.43);
        sR.setPower(-sL.getPower());
    }
    public static void nepolniVidvig(){
        sL.setPower(0.32);
        sR.setPower(-sL.getPower());
}
    public static void sloz(){
        sL.setPower(0);
        sR.setPower(0);
    }

    public void zaxvat(){
        zx.setPower(0.23);
        opMode.sleep(200);
        brat.setPower(krut_start_power);
        brat2.setPower(krut2_start_power);
        sL.setPower(0- 0.01);
        sR.setPower(0);
        opMode.sleep(1000);
        klesh.setPower(0);
        brat.setPower(-0.15);
        brat2.setPower(-0.9);
        opMode.sleep(750);
        zx.setPower(-0.33);
        opMode.sleep(600);
        klesh.setPower(-0.35);
        krutgo = false;
        brat.setPower(krut_start_power);
        brat2.setPower(krut2_start_power);
    }

}

