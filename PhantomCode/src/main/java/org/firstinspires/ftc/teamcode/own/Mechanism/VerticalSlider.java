package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class VerticalSlider {
    LinearOpMode opMode;
    HardwareMap hw;
    public VerticalSlider(LinearOpMode opMode){
        this.opMode = opMode;
        hw = opMode.hardwareMap;
    }
    CRServo vrash, klesh;
    DcMotorEx pod;
    // мне лень писать 3 переменне поэтому 0 - мотор 1 - клешнят 2 - поворот
    double[] StartPowers = new double[]{0,0,0};
    //аналогично
    double[] RunPowers = StartPowers;

    public void init(){
        pod = hw.get(DcMotorEx.class,"pod");
        klesh = hw.get(CRServo.class, "klesh");
        vrash = hw.get(CRServo.class, "vrash");
        pod.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pod.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pod.setPower(StartPowers[0]);
        klesh.setPower(StartPowers[1]);
        vrash.setPower(StartPowers[2]);
    }

    public void run(){
        pod.setPower(RunPowers[0]);
        klesh.setPower(RunPowers[1]);
        vrash.setPower(RunPowers[2]);
        if(opMode.gamepad2.dpad_up){
            if (RunPowers[0] <= 0.5){
                RunPowers[0] = RunPowers[0] + 0.02;
            } else {
                RunPowers[0] = 0.5;
            }
        } else if (opMode.gamepad2.dpad_down) {
            if (RunPowers[0] >= -0.5){
                RunPowers[0] = RunPowers[0] - 0.02;
            } else {
                RunPowers[0] = -0.5;
            }
        } else {
            RunPowers[0] = 0.01;
        }
    }
}
