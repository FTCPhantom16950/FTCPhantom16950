package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.util.Range;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.Config;

import java.io.LineNumberReader;

public class Zx extends Thread {
    HorizontSlider horizontSlider;
    Config config = new Config();
    LinearOpMode opMode;
    public CRServo zx, krut;
    boolean AUTOMODE;
    HardwareMap hw;
    public Zx(LinearOpMode opMode){
        this.opMode = opMode;
        this.setDaemon(true);
    }
    private final double krut_start_power = 0;
    private final double zx_start_power = 0;
    private final double zx_power = 0.1;
    private final double krut_skid = -1.0;
    private int i = 0;
    private double g =0, f =0;


    public void init(){
        hw = opMode.hardwareMap;
        zx = opMode.hardwareMap.get(CRServo.class, "zx");
        krut= opMode.hardwareMap.get(CRServo.class, "krut");
        zx.setPower(zx_start_power);
        krut.setPower(krut_start_power);
    }

    @Override
    public synchronized void start() {
        super.start();
        if (opMode.opModeInInit()){
            init();
        }
        while (opMode.opModeIsActive()){
            autoZX();
            manualKRUT();
            autoKrut();
        }
    }

    public void manualZX(){
        if (opMode.gamepad2.x){
            zx.setPower(f);
            f = Range.clip(f + 0.02, -1, 1);
        } else if (opMode.gamepad2.b) {
            zx.setPower(f);
            f = Range.clip(f - 0.02, -1, 1);
        }
    }
    public void autoZX(){
        if (opMode.gamepad2.right_bumper){
            zx.setPower(0.3);
        } else {
            zx.setPower(0);
        }
    }
    public void manualKRUT(){
        if (opMode.gamepad2.y){
            krut.setPower(g);
            g = Range.clip(g + 0.02, -0.95, 1);
        } else if (opMode.gamepad2.a) {
            krut.setPower(g);
            g = Range.clip(g - 0.02, -0.95, 1);
        }
    }
    public void autoKrut(){
        horizontSlider = new HorizontSlider(opMode);
        if(opMode.gamepad2.x){
            zx.setPower(0);
            krut.setPower(-0.5);
            i = 0;
        }
    }
}
