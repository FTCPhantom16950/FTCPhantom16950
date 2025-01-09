package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.Config;

import java.io.LineNumberReader;

public class Zx {

    Config config = new Config();
    LinearOpMode opMode;
    public static CRServo zx, krut, krut2;
    HardwareMap hw;
    public Zx(LinearOpMode opMode){
        this.opMode = opMode;

    }
    private static final double krut_start_power = -0.3;
    private static final double krut2_start_power = -0.3;
    private static final double zx_start_power = 0;
    private static double zx_power = zx_start_power;
    private static double krut_power = krut_start_power;
    private static double krut2_power = krut2_start_power;
    public static int i = 0;
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

        if(opMode.gamepad2.right_bumper){
            zx.setPower(0.5);
        } else{
            zx.setPower(0);
        }

        // вперед
        if(opMode.gamepad2.y){
            krut_power = krut_power + 0.01;
            krut.setPower(krut_power);
        }
        // назад
        if(opMode.gamepad2.a){
            krut_power = krut_power - 0.01;
            krut.setPower(krut_power);
        }
        // вперед
        if(opMode.gamepad2.b){
            krut2_power = krut2_power + 0.01;
            krut2.setPower(krut2_power);
        }
        // назад
        if(opMode.gamepad2.x){
            krut2_power = krut2_power - 0.01;
            krut2.setPower(krut2_power);
        }
    }
}
