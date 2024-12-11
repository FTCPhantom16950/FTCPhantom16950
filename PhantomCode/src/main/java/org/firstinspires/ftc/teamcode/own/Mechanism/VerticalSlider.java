package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID;
import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.PIDEx;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficientsEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.Range;

public class VerticalSlider{

    LinearOpMode opMode;
    double  output = 0, targetPos = 0;
    // creation of the PID object
    PIDCoefficients coefficients = new PIDCoefficients(0,0,0);
    BasicPID controller = new BasicPID(coefficients);
    HardwareMap hw;
    public VerticalSlider(LinearOpMode opMode){
        this.opMode = opMode;
    }
    public CRServo vrash, klesh;
    public DcMotorEx pod;
    int i = 0, g = 0, lasti;
    // мне лень писать 3 переменне поэтому 0 - мотор 1 - клешнят 2 - поворот
    double[] StartPowers = new double[]{0,0,-0.5};
    //аналогично
    public double[] RunPowers = StartPowers;

    public void init(){
        hw = opMode.hardwareMap;
        pod = opMode.hardwareMap.get(DcMotorEx.class,"pod");
        klesh = opMode.hardwareMap.get(CRServo.class, "klesh");
        vrash = opMode.hardwareMap.get(CRServo.class, "vrash");
        pod.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pod.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pod.setPower(StartPowers[0]);
        klesh.setPower(StartPowers[1]);
        vrash.setPower(StartPowers[2]);

    }
    public Thread pidThread = new Thread(() -> {
        while(opMode.opModeIsActive()){
            if (!opMode.gamepad2.dpad_up && !opMode.gamepad2.dpad_down){
                output = controller.calculate(targetPos, pod.getCurrentPosition());
                pod.setPower(output);
            } else {
                targetPos = pod.getCurrentPosition();
            }
        }
    });

    public void test(){
        if(opMode.gamepad2.dpad_up){
            if (RunPowers[0] <= 1){
                RunPowers[0] = 1;
            } else {
                RunPowers[0] = 1;
            }
        } else if (opMode.gamepad2.dpad_down) {
            if (RunPowers[0] >= -1){
                RunPowers[0] = -1;
            } else {
                RunPowers[0] = -1;
            }
        } else {
            RunPowers[0] = 0.015;
        }
        opMode.telemetry.addData("position", pod.getCurrentPosition());
        opMode.telemetry.addData("error", targetPos - pod.getCurrentPosition());
        opMode.telemetry.addData("targpos", targetPos);
        opMode.telemetry.update();
    }

    public void run(){
        lasti = i;
        pod.setPower(RunPowers[0]);
        klesh.setPower(RunPowers[1]);
        vrash.setPower(RunPowers[2]);
        if(opMode.gamepad2.dpad_up){
            if (RunPowers[0] <= 1){
                RunPowers[0] = 1;
            } else {
                RunPowers[0] = 1;
            }
        } else if (opMode.gamepad2.dpad_down) {
            if (RunPowers[0] >= -1){
                RunPowers[0] = -1;
            } else {
                RunPowers[0] = -1;
            }
        } else {
            RunPowers[0] = 0.01;
        }

        if(opMode.gamepad2.dpad_right){
            RunPowers[2] = Range.clip(RunPowers[2] + 0.02, -1,1);
        } else if (opMode.gamepad2.dpad_left) {
            RunPowers[2] = Range.clip( RunPowers[2] -0.02, -1,1);
        }
        // открытие
//        if(opMode.gamepad2.left_bumper){
//            RunPowers[1] = Range.clip(RunPowers[1] - 0.02,-1,1);
//        } else if (opMode.gamepad2.right_bumper) {
//            RunPowers[1] = Range.clip(RunPowers[1] + 0.02,-1,1);
      //  }// это пока не робит(все что с условием AUTOMODE)
//        if (opMode.gamepad2.dpad_up ){
//            i = i + 1;
//            g = i;
//        } else if (opMode.gamepad2.dpad_down) {
//            i = -1;
//            g = i;
//        }
//        if (i>2) {
//            i = 0;
//            g = i;
//        } else if (i == lasti) {
//            g = 0;
//        }

//        if (opMode.gamepad2.dpad_right ){
//            RunPowers[2] = StartPowers[2];
//        } else if (opMode.gamepad2.dpad_left ) {
//            RunPowers[2] = 0.5;
//        }

        if(opMode.gamepad2.left_bumper){
            RunPowers[1]  = -0.3;
        } else {
            RunPowers[1] = 0;
        }

//        if (AUTOMODE && (g == 1 || g == 2)){
//            pod.setTargetPosition(1000 * g);
//            pod.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            pod.setPower(0.3);
//            while (pod.isBusy()){}
//            pod.setPower(0.01);
//            g = 0;
//        } else if (AUTOMODE && g == -1) {
//            pod.setTargetPosition((int)(pod.getCurrentPosition() / 10) *10);
//            pod.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            pod.setPower(-0.3);
//            while (pod.isBusy()){}
//            pod.setPower(0);
//            g =0;
//        }

    }
}
