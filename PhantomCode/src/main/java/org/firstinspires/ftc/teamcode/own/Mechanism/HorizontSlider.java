package org.firstinspires.ftc.teamcode.own.Mechanism;

import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.not;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrash;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrashPower;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.g;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krut;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krutgo;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krutpos;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.zx;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.zxgo;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.zxpos;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.positions.ZxPos;

public class HorizontSlider {
    LinearOpMode opMode;
    public static CRServo sL, sR;
    HardwareMap hw;

    public HorizontSlider(LinearOpMode opMode){
        this.opMode = opMode;
       // this.setDaemon(true);
    }

    public static double startLeftPower = 0, startRightPower = 0, i = 0, sl_power, sr_power;
    public void init() {
        hw = opMode.hardwareMap;
        sL = opMode.hardwareMap.get(CRServo.class, "horL");
        sR = opMode.hardwareMap.get(CRServo.class, "horR");
        sL.setPower(startLeftPower);
        sR.setPower(startRightPower);
        sl_power = startLeftPower;
        sr_power = startRightPower;
    }


    public void run_wiithout(){
        sl_power = i;
        sr_power = -i;
        sL.setPower(sl_power);
        sR.setPower(sr_power - 0.05);
    }
    public void manualMoving(){
        if (opMode.gamepad2.left_stick_button&& i < 0.45){
            i = i + 0.05;
        } else if (i >= 0.45){
            i = 0.45;
        }
        if (opMode.gamepad2.right_stick_button  && i > 0){
            i = i - 0.05;
        }
        else if (i <= 0){
            i = 0;
        }
    }
    public void vidvig(){
        i = 0.45;
        krutpos = ZxPos.KRUT.ZAXVAT;
        zxpos = ZxPos.ZX.OTPUSK;
        krutgo = true;
        zxgo = true;
    }
    public void zaxvat(){
        zxpos = ZxPos.ZX.ZAXVAT;
        i = 0;
        krutpos = ZxPos.KRUT.PEREDACHA;
        zxgo = true;
        krutgo = true;
        opMode.sleep(400);
        zxpos = ZxPos.ZX.OTPUSK;
        zxgo = true;
        krutgo = true;
    }
    public void autoMoving(){
        if (opMode.gamepad2.right_trigger!= 0.0){
            vidvig();
        } else if (opMode.gamepad2.left_trigger!= 0.0) {
            zaxvat();
        } else if (opMode.gamepad2.x) {
            i = 0;
            zxpos = ZxPos.ZX.ZAXVAT;
            zxgo = true;
            krutgo = true;
            krutpos = ZxPos.KRUT.AUto;
            zxgo = true;
            krutgo = true;
            opMode.sleep(1000);
            krutpos = ZxPos.KRUT.PEREDACHA;
            zxgo = true;
            krutgo = true;
            opMode.sleep(400);
            zxpos = ZxPos.ZX.OTPUSK;
            zxgo = true;
            krutgo = true;
        }
    }
}

