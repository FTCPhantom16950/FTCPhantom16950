package org.firstinspires.ftc.teamcode.ftclib.SYS;


import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.positions.VerticalPOS;

public class VerticalSliderSYS extends SubsystemBase {
    public static CRServo vrash, klesh, sample;
    public static DcMotorEx pod;
    public static double podPower = 0, vrashPower = -0.5, kleshPower = 0;
    public  boolean kleshgo = false;
    VerticalPOS.KLESHPOS verticalPOS ;
    LinearOpMode opMode;
    public boolean inited = false;
    double  output = 0, targetPos = 0;
    // creation of the PID object
    HardwareMap hw;
    public VerticalSliderSYS(LinearOpMode opMode){
        this.opMode = opMode;
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
        sample.setPower(0.71);
        verticalPOS = VerticalPOS.KLESHPOS.ZAXVAT;
    }
    public void init(){
        pod.setPower(podPower);
        klesh.setPower(kleshPower);
        vrash.setPower(vrashPower);
    }
    public void poxod(){
        klesh.setPower(0);
        vrash.setPower(-0.1);
    }
    public void kleshOpen(){
        klesh.setPower(-0.3);
    }
    public void kleshClosed(){
        klesh.setPower(0);
    }
    public void vrashPerexvat(){
        vrash.setPower(-0.93);
    }
    public void vrashPodem(){
        vrash.setPower(-0.6);
    }
    public void vrashSkin(){
        vrash.setPower(1);
    }


}
