package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.Config;

import java.io.LineNumberReader;

public class Zx extends Thread {
    Config config = new Config();
    LinearOpMode opMode;
    CRServo zx, krut;
    boolean AUTOMODE;
    HardwareMap hw;
    public Zx(LinearOpMode opMode){
        this.opMode = opMode;
        hw = opMode.hardwareMap;
    }
    private final double krut_start_power = 0.0;
    private final double zx_start_power = 0;
    private final double zx_power = 0.1;
    private final double krut_skid = -1.0;
    private int i = 0;


    public void init(){
        zx = opMode.hardwareMap.get(CRServo.class, "zx");
        krut= opMode.hardwareMap.get(CRServo.class, "krut");
        zx.setPower(zx_start_power);
        krut.setPower(krut_start_power);
    }
    @Override
    public void run(){
        AUTOMODE = config.isAUTOMODE();

        if (opMode.gamepad2.b) {
            i += 1;
        }

        if (i % 2 == 0) {
            zx.setPower(zx_start_power);
        } else if (i % 2 == 1) {
            zx.setPower(zx_power);
        }

        if (opMode.gamepad2.y) {
            krut.setPower(krut_skid);
        } else if (opMode.gamepad2.a) {
            krut.setPower(krut_start_power);
        }
    }
}
