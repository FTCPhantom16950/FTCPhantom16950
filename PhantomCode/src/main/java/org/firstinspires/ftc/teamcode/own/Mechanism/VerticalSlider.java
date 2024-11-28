package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.own.Utils.Config;

public class VerticalSlider extends Thread{
    Config config = new Config();
    LinearOpMode opMode;
    boolean AUTOMODE;
    HardwareMap hw;
    public VerticalSlider(LinearOpMode opMode){

        this.opMode = opMode;
        this.setDaemon(true);
    }
    CRServo vrash, klesh;
    DcMotorEx pod;
    int i = 0, g = 0, lasti;
    // мне лень писать 3 переменне поэтому 0 - мотор 1 - клешнят 2 - поворот
    double[] StartPowers = new double[]{0,0,0};
    //аналогично
    double[] RunPowers = StartPowers;

    public void init(){
        hw = opMode.hardwareMap;
        pod = opMode.hardwareMap.get(DcMotorEx.class,"pod");
        klesh = opMode.hardwareMap.get(CRServo.class, "klesh");
        vrash = opMode.hardwareMap.get(CRServo.class, "vrash");
        pod.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pod.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pod.setPower(StartPowers[0]);
        klesh.setPower(StartPowers[1]);
        vrash.setPower(StartPowers[2]);
    }

    @Override
    public void run(){
        AUTOMODE = config.isAUTOMODE();
        lasti = i;
        pod.setPower(RunPowers[0]);
        klesh.setPower(RunPowers[1]);
        vrash.setPower(RunPowers[2]);
        if(opMode.gamepad2.dpad_up && !AUTOMODE){
            if (RunPowers[0] <= 0.85){
                RunPowers[0] = RunPowers[0] + 0.2;
            } else {
                RunPowers[0] = 0.85;
            }
        } else if (opMode.gamepad2.dpad_down && !AUTOMODE) {
            if (RunPowers[0] >= -0.85){
                RunPowers[0] = RunPowers[0] - 0.2;
            } else {
                RunPowers[0] = -0.85;
            }
        } else {
            RunPowers[0] = 0.01;
        }

        if(opMode.gamepad2.dpad_right && !AUTOMODE){
            RunPowers[2] = Range.clip(RunPowers[2] + 0.02, -1,1);
        } else if (opMode.gamepad2.dpad_left && !AUTOMODE) {
            RunPowers[2] = Range.clip( RunPowers[2] -0.02, -1,1);
        }
        // открытие
        if(opMode.gamepad2.left_bumper && AUTOMODE){
            RunPowers[1] = Range.clip(RunPowers[1] - 0.02,-1,1);
        } else if (opMode.gamepad2.right_bumper && AUTOMODE) {
            RunPowers[1] = Range.clip(RunPowers[1] + 0.02,-1,1);
        }// это пока не робит(все что с условием AUTOMODE)
        if (opMode.gamepad2.dpad_up && AUTOMODE){
            i = i + 1;
            g = i;
        } else if (opMode.gamepad2.dpad_down && AUTOMODE) {
            i = -1;
            g = i;
        }
        if (i>2) {
            i = 0;
            g = i;
        } else if (i == lasti) {
            g = 0;
        }

        if (opMode.gamepad2.dpad_right && AUTOMODE){
            RunPowers[2] = StartPowers[2];
        } else if (opMode.gamepad2.dpad_left && AUTOMODE) {
            RunPowers[2] = 0.5;
        }

        if(opMode.gamepad2.left_bumper){
            RunPowers[1]  = -0.3;
        } else {
            RunPowers[1] = 0;
        }

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
}
