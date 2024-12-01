package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.util.Range;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.Config;

import java.io.LineNumberReader;

public class Zx {

    Config config = new Config();
    LinearOpMode opMode;
    public CRServo zx, krut, klesh;
    HardwareMap hw;
    public Zx(LinearOpMode opMode){
        this.opMode = opMode;

    }
    private final double krut_start_power = 0.3;
    private final double zx_start_power = 0;
    private final double zx_power = 0.1;
    private final double krut_skid = -1.0;
    private int i = 0;
    private double g =0, f =0;


    public void init(){
        hw = opMode.hardwareMap;
        zx = opMode.hardwareMap.get(CRServo.class, "zx");
        krut= opMode.hardwareMap.get(CRServo.class, "krut");
        klesh = opMode.hardwareMap.get(CRServo.class, "klesh");
        klesh.setPower(0);
        zx.setPower(zx_start_power);
        krut.setPower(krut_start_power);
    }

    public void run(){
        if (opMode.gamepad2.right_bumper){
            zx.setPower(0.3);
        } else {
            zx.setPower(0);
        }
        if (opMode.gamepad2.y){
            g = Range.clip(g + 0.02, -0.95, 1);
            krut.setPower(g);
        } else if (opMode.gamepad2.a) {
            g = Range.clip(g - 0.02, -0.95, 1);
            krut.setPower(g);
        }
//        if (opMode.gamepad2.b){
//            zx.setPower(0);
//            opMode.sleep(200);
//            klesh.setPower(0.3);
//            opMode.sleep(200);
//            klesh.setPower(0);
//            opMode.sleep(200);
//            zx.setPower(0.3);
//            krut.setPower(krut_start_power);
//            opMode.sleep(200);
//        }
//        if (opMode.gamepad2.x ){
//            zx.setPower(f);
//            f = Range.clip(f + 0.02, -1, 1);
//        } else if (opMode.gamepad2.b) {
//            zx.setPower(f);
//            f = Range.clip(f - 0.02, -1, 1);
//        }

//        if (opMode.gamepad2.y) {
//            krut.setPower(krut_skid);
//        } else if (opMode.gamepad2.a){
//            krut.setPower(-0.95);
//        }

    } }
