package org.firstinspires.ftc.teamcode.own.Mechanism;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.own.positions.VerticalPOS;

public class VerticalSlider{

    LinearOpMode opMode;
    public boolean inited = false;
    double  output = 0, targetPos = 0;
    // creation of the PID object
    HardwareMap hw;
    public VerticalSlider(LinearOpMode opMode){
        this.opMode = opMode;
    }
    public static CRServo vrash, klesh, sample;
    public static DcMotorEx pod;
    public static double podPower = 0, vrashPower = -0.5, kleshPower = 0;
    public  boolean kleshgo = false;
    VerticalPOS.KLESHPOS verticalPOS ;

    public void init(){
        hw = opMode.hardwareMap;
        inited = true;
        pod = opMode.hardwareMap.get(DcMotorEx.class,"pod");
        klesh = opMode.hardwareMap.get(CRServo.class, "klesh");
        vrash = opMode.hardwareMap.get(CRServo.class, "vrash");
        sample = opMode.hardwareMap.get(CRServo.class, "sample");
        pod.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pod.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pod.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pod.setPower(podPower);
        klesh.setPower(kleshPower);
        vrash.setPower(vrashPower);
        sample.setPower(0.73);
        verticalPOS = VerticalPOS.KLESHPOS.ZAXVAT;
        playLoop.start();
    }
    Thread playLoop = new Thread(() -> {
        while (opMode.opModeIsActive()){
            if (kleshgo && verticalPOS == VerticalPOS.KLESHPOS.OTPUSK){
                kleshPower = -0.3;
                klesh.setPower(kleshPower);
                kleshgo = false;
            } else if (kleshgo && verticalPOS == VerticalPOS.KLESHPOS.ZAXVAT) {
                kleshPower = 0;
                klesh.setPower(kleshPower);
                kleshgo = false;
            }
        }
    });
    public void run(){
        if(opMode.gamepad2.dpad_up){
            if (pod.getPower() <= 1){
                pod.setPower(1);
            } else {
                pod.setPower(1);
            }
        } else if (opMode.gamepad2.dpad_down) {
            if (pod.getPower() >= -1){
                pod.setPower(-1);
            } else {
                pod.setPower(-1);
            }
        } else {
            pod.setPower(0.025);
        }
        if(opMode.gamepad2.dpad_right){
            vrashPower = Range.clip(vrashPower + 0.02, -0.95,1);
            vrash.setPower(vrashPower);
        } else if (opMode.gamepad2.dpad_left) {
            vrashPower = -0.93;
            vrash.setPower(vrashPower);
        }
        if(opMode.gamepad2.left_bumper) {
            if (verticalPOS == VerticalPOS.KLESHPOS.ZAXVAT){
                verticalPOS = VerticalPOS.KLESHPOS.OTPUSK;
                opMode.sleep(200);
                kleshgo = true;
            } else if (verticalPOS == VerticalPOS.KLESHPOS.OTPUSK) {
                verticalPOS = VerticalPOS.KLESHPOS.ZAXVAT;
                opMode.sleep(200);
                kleshgo = true;
            }
        }
    }
    public void podvesSpiecMan(){
        opMode.sleep(500);
        pod.setPower(0.7);
        opMode.sleep(700);
        pod.setPower(0.13);
        opMode.sleep(500);
        pod.setPower(-0.7);
        opMode.sleep(700);
        pod.setPower(0.13);
    }
    public void podvesSample(){
        kleshgo = true;
        verticalPOS = VerticalPOS.KLESHPOS.OTPUSK;
        vrash.setPower(-0.93);
        opMode.sleep(200);
        kleshgo = true;
        verticalPOS = VerticalPOS.KLESHPOS.ZAXVAT;
        vrash.setPower(-0.5);
        pod.setPower(1);
        opMode.sleep(500);
        vrash.setPower(1);
        opMode.sleep(300);
        kleshgo = true;
        verticalPOS = VerticalPOS.KLESHPOS.OTPUSK;
        opMode.sleep(200);
        vrash.setPower(-0.5);
        pod.setPower(-1);
        opMode.sleep(500);
    }
}
