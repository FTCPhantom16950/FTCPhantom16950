package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.positions.ZxPos;

import java.io.LineNumberReader;

public class Zx {

    Config config = new Config();
    LinearOpMode opMode;
    public static CRServo zx, krut, krut2;
    HardwareMap hw;
    public Zx(LinearOpMode opMode){
        this.opMode = opMode;

    }
    ZxPos.KRUT krutpos = ZxPos.KRUT.POXOD;
    ZxPos.ZX zxpos = ZxPos.ZX.OTPUSK;
    ZxPos.ZX curr_pos = zxpos;
    private static final double krut_start_power = -0.3;
    private static final double krut2_start_power = -0.3;
    private static final double zx_start_power = 0;
    public static double g = 0;
    public static boolean not = false;


    public void init(){
        hw = opMode.hardwareMap;
        zx = opMode.hardwareMap.get(CRServo.class, "zx");
        krut= opMode.hardwareMap.get(CRServo.class, "krut");
        krut2 = opMode.hardwareMap.get(CRServo.class, "vrash2");
        krut2.setDirection(DcMotorSimple.Direction.REVERSE);
        zx.setPower(zx_start_power);
        krut.setPower(krut_start_power);
        krut2.setPower(krut2_start_power);
    }

    public void run(){
        boolean ready = true;
        curr_pos = zxpos;
        switch (krutpos){
            case PEREDACHA -> {

                krut.setPower(-0.4);
                krut2.setPower(-0.4);
                opMode.sleep(300);
                zxpos = ZxPos.ZX.OTPUSK;
                opMode.sleep(300);
            }
            case ZAXVAT -> {
                zxpos = ZxPos.ZX.OTPUSK;
                opMode.sleep(200);
                krut.setPower(0.75);
                krut2.setPower(0.4);
            }
            case null, default -> {
                krut.setPower(krut_start_power);
                krut2.setPower(krut2_start_power);}

        }
        switch (zxpos){
            case OTPUSK ->{
                zx.setPower(0);
            }
            case ZAXVAT ->{
                zx.setPower(0.25);
            }
        }
        if(opMode.gamepad2.right_bumper){
            if (zxpos == ZxPos.ZX.ZAXVAT){
                zxpos = ZxPos.ZX.OTPUSK;
                opMode.sleep(300);
            } else if (zxpos == ZxPos.ZX.OTPUSK) {
                zxpos = ZxPos.ZX.ZAXVAT;
                opMode.sleep(200);
            }
        }

        if(opMode.gamepad2.y){
            krutpos = ZxPos.KRUT.ZAXVAT;
            opMode.sleep(200);
        }
        // назад -
        else if(opMode.gamepad2.a){
            krutpos = ZxPos.KRUT.PEREDACHA;
            opMode.sleep(200);
        } else if (opMode.gamepad2.b) {
            krutpos = ZxPos.KRUT.POXOD;
            opMode.sleep(200);
        }


    }
}
