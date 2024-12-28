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
    public static CRServo zx, krut;
    HardwareMap hw;
    public Zx(LinearOpMode opMode){
        this.opMode = opMode;

    }
    private final double krut_start_power = 0.3;
    private final double zx_start_power = 0;
    private final double zx_power = 0.1;
    private final double krut_skid = -1.0;
    public static int i = 0;
    public static double g = 0;
    public static boolean not = false;

    public void init(){
        hw = opMode.hardwareMap;
        zx = opMode.hardwareMap.get(CRServo.class, "zx");
        krut= opMode.hardwareMap.get(CRServo.class, "krut");
        zx.setPower(zx_start_power);
        krut.setPower(krut_start_power);
        g = 0;
        thread.start();
    }
    Thread thread = new Thread(() -> {
        while (true){
            krut.setPower(g);
        }
    });
    public void run(){
//        if (i == 0){
//            zx.setPower(0);
//        } else if (i == 1){
//            zx.setPower(0.5);
//        }
        if (opMode.gamepad2.right_bumper){
//            if (i == 0){
//                i = 1;
//                opMode.sleep(200);
//            } else if (i == 1) {
//                i = 0;
//                opMode.sleep(200);
//            }
            zx.setPower(0.5);
        } else if (!not){
            zx.setPower(0);
        }
        if (opMode.gamepad2.y){
            g = Range.clip(g + 0.015, -0.57, 1);
            krut.setPower(g);
        } else if (opMode.gamepad2.a) {
            g = Range.clip(g - 0.015, -0.57, 1);
            krut.setPower(g);
        }
    } }
