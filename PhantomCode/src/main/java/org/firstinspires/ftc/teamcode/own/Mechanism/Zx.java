package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.hardware.DcMotorSimple;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.positions.ZxPos;



public class Zx {

    Config config = new Config();
    LinearOpMode opMode;
    public static CRServo zx, krut, krut2;
    HardwareMap hw;
    public Zx(LinearOpMode opMode){
        this.opMode = opMode;

    }
    public static ZxPos.KRUT krutpos;
    public static ZxPos.ZX zxpos;
    private static final double krut_start_power = -0.3;
    private static final double krut2_start_power = -0.3;
    private static final double zx_start_power = 0;
    public static double g = 0;
    double krut2_power, krut_power;
    public static boolean not = false;
    public static boolean zxgo, krutgo;


    public void init(){
        hw = opMode.hardwareMap;
        zx = opMode.hardwareMap.get(CRServo.class, "zx");
        krut= opMode.hardwareMap.get(CRServo.class, "krut");
        krut2 = opMode.hardwareMap.get(CRServo.class, "vrash2");
        krut2.setDirection(DcMotorSimple.Direction.REVERSE);
        zx.setPower(zx_start_power);
        krut.setPower(krut_start_power);
        krut2.setPower(krut2_start_power);
        krutpos = ZxPos.KRUT.POXOD;
        zxpos = ZxPos.ZX.OTPUSK;
    }

    public void run(){
        krut_power = krut2.getPower();

        if (opMode.gamepad2.right_bumper){
            if (zxpos == ZxPos.ZX.ZAXVAT){
                zxpos = ZxPos.ZX.OTPUSK;
                opMode.sleep(300);
                zxgo = true;
            } else if (zxpos == ZxPos.ZX.OTPUSK) {
                zxpos = ZxPos.ZX.ZAXVAT;
                opMode.sleep(300);
                zxgo = true;
            }
        }
        if (zxpos == ZxPos.ZX.ZAXVAT && zxgo){
            zx.setPower(0.23);
            zxgo = false;
        }   else if (zxpos == ZxPos.ZX.OTPUSK && zxgo) {
            zx.setPower(-0.33);
            zxgo = false;
        }

        if(opMode.gamepad2.y){
            krutpos = ZxPos.KRUT.ZAXVAT;
            zxpos = ZxPos.ZX.OTPUSK;
            opMode.sleep(200);
            zxgo = true;
            krutgo = true;
        }
        else if(opMode.gamepad2.a){
            krutpos = ZxPos.KRUT.PEREDACHA;
            opMode.sleep(200);
            krutgo = true;
        } else if (opMode.gamepad2.b && !(krutpos == ZxPos.KRUT.ZAXVAT)) {
            krutpos = ZxPos.KRUT.POXOD;
            opMode.sleep(200);
            krutgo = true;
        }
        if (krutpos == ZxPos.KRUT.AUto && krutgo){
            krut.setPower(0.67);
            krut2.setPower(-0.3);
            krutgo = false;
        }
        else if (krutpos == ZxPos.KRUT.ZAXVAT && krutgo){
            krut.setPower(0.67);
            krut2.setPower(0.42);
            krutgo = false;
        } else if (krutpos == ZxPos.KRUT.PEREDACHA && krutgo){
            krut.setPower(-0.4);
            krut2.setPower(-0.4);
            krutgo = false;
        } else if (krutpos == ZxPos.KRUT.POXOD && krutgo){
            krut.setPower(krut_start_power);
            krut2.setPower(krut2_start_power);
            krutgo = false;
        }
        if (opMode.gamepad2.b && krutpos == ZxPos.KRUT.ZAXVAT) {
            krut2_power = Range.clip(krut2_power + 0.1, -1, 0.5);
            krut_power = Range.clip(krut_power + 0.2, -1, 1);
            krut2.setPower(krut2_power);
            krut.setPower(krut_power);
            opMode.sleep(300);
        } else if (opMode.gamepad2.x && krutpos == ZxPos.KRUT.ZAXVAT) {
            krut2_power = Range.clip(krut2_power - 0.1, -1, 0.5);
            krut_power = Range.clip(krut_power - 0.2, -1, 1);
            krut2.setPower(krut2_power);
            krut.setPower(krut_power);
            opMode.sleep(300);
        }


    }
}
