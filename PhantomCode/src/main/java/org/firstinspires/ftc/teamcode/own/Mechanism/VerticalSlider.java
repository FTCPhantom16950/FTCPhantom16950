package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.own.Utils.Config;

public class VerticalSlider extends Thread{
    LinearOpMode opMode;
    boolean AUTOMODE;
    HardwareMap hw;
    Config config = new Config();
    public VerticalSlider(LinearOpMode opMode){
        this.opMode = opMode;
        this.setDaemon(true);
    }
    CRServo kleshna, vrashat;
    DcMotorEx pod;
    int i = 0, g = 0, lasti;
    // мне лень писать 3 переменне поэтому 0 - мотор 1 - клешнят 2 - поворот
    private double[] StartPowers = new double[]{0,0,0};
    Zx zx ;
    //аналогично
    public double[] RunPowers = StartPowers;
    public void setPowers(){
        AUTOMODE = config.isAUTOMODE();
        pod.setPower(RunPowers[0]);
        vrashat.setPower(RunPowers[1]);
        kleshna.setPower(RunPowers[2]);
    }
    public void init(){
        hw = opMode.hardwareMap;
        pod = opMode.hardwareMap.get(DcMotorEx.class,"pod");
        vrashat = opMode.hardwareMap.get(CRServo.class, "klesh");
        kleshna = opMode.hardwareMap.get(CRServo.class, "vrash");
        pod.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pod.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pod.setPower(StartPowers[0]);
        vrashat.setPower(StartPowers[1]);
        kleshna.setPower(StartPowers[2]);
    }
    public void manualMotorRun(){
        if(opMode.gamepad2.dpad_up){
            if (RunPowers[0] <= 0.85){
                RunPowers[0] = RunPowers[0] + 0.2;
            } else {
                RunPowers[0] = 0.85;
            }
        } else if (opMode.gamepad2.dpad_down) {
            if (RunPowers[0] >= -0.85){
                RunPowers[0] = RunPowers[0] - 0.2;
            } else {
                RunPowers[0] = -0.85;
            }
        } else {
            RunPowers[0] = 0.01;
        }
    }
    public void automaticMotorRun(){
        logic();
        if (AUTOMODE && (g == 1 || g == 2)){
            pod.setTargetPosition(1000 * g);
            pod.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            pod.setPower(0.3);
            while (pod.isBusy()){}
            pod.setPower(0.01);
            g = 0;
        } else if (AUTOMODE && g == -1) {
            pod.setTargetPosition((int)(pod.getCurrentPosition() / 10) *10);
            pod.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            pod.setPower(-0.3);
            while (pod.isBusy()){}
            pod.setPower(0);
            g =0;
        }
    }
    public void logic(){
        lasti = i;
        if (opMode.gamepad2.dpad_up){
            i = i + 1;
            g = i;
        } else if (opMode.gamepad2.dpad_down) {
            i = -1;
            g = i;
        }
        if (i>2) {
            i = 0;
            g = i;
        } else if (i == lasti) {
            g = 0;
        }
    }
    // Only for test; Только для тестов
    public void manualKleshna(){
        if(opMode.gamepad2.left_bumper){
            RunPowers[1] = Range.clip(RunPowers[1] - 0.02,-1,1);
        } else if (opMode.gamepad2.right_bumper) {
            RunPowers[1] = Range.clip(RunPowers[1] + 0.02,-1,1);
        }
    }
    public void autoKleshna(){
        if(opMode.gamepad2.left_bumper){
            RunPowers[1]  = -0.3;
        } else {
            RunPowers[1] = 0;
        }
    }
    public void manualVrash(){
        if(opMode.gamepad2.dpad_right){
            RunPowers[2] = Range.clip(RunPowers[2] + 0.02, -1,1);
        } else if (opMode.gamepad2.dpad_left) {
            RunPowers[2] = Range.clip( RunPowers[2] -0.02, -1,1);
        }
    }
    //TODO: Create 3 positions; Создать 3 позиции
    public void autoVrash(){
        if (opMode.gamepad2.dpad_right){
            RunPowers[2] = StartPowers[2];
        } else if (opMode.gamepad2.dpad_left) {
            RunPowers[2] = 0.5;
        }
    }
    public void horAndVer(){
        zx = new Zx(opMode);
        if (opMode.gamepad2.b){
            zx.zx.setPower(0);
            RunPowers[1] = 0;
            opMode.sleep(100);
            zx.zx.setPower(0.3);
        }
    }

    public void preSet1(){
        if (opMode.opModeInInit()){
            init();
        }
        while(opMode.opModeIsActive()){
            setPowers();
            if (AUTOMODE){
                automaticMotorRun();
            } else if (!AUTOMODE) {
                manualMotorRun();
            }
            autoKleshna();
            manualVrash();
            horAndVer();
        }
    }
    public void preSet2(){
        setPowers();
        if (AUTOMODE){
            automaticMotorRun();
        } else if (!AUTOMODE) {
            manualMotorRun();
        }
        autoKleshna();
        manualVrash();
        horAndVer();
    }

    @Override
    public synchronized void start() {
        super.start();
        preSet1();
    }
}
