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
    public static CRServo vrash, klesh, sample;
    public static DcMotorEx pod;
    int i = 0, g = 0, lasti;
    // мне лень писать 3 переменне поэтому 0 - мотор 1 - клешнят 2 - поворот
    public static double podPower = 0, vrashPower = -0.5, kleshPower = 0;
    public static boolean not;
    //аналогично

    public void init(){
        hw = opMode.hardwareMap;

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

//    public void test(){
//        pod.setPower(RunPowers[0]);
//        if(opMode.gamepad2.dpad_up){
//            if (RunPowers[0] <= 1){
//                RunPowers[0] = 1;
//            } else {
//                RunPowers[0] = 1;
//            }
//        } else if (opMode.gamepad2.dpad_down) {
//            if (RunPowers[0] >= -1){
//                RunPowers[0] = -1;
//            } else {
//                RunPowers[0] = -1;
//            }
//        } else {
//            RunPowers[0] = 0.012;
//        }
//
//    }

    public void run(){
        lasti = i;
        klesh.setPower(kleshPower);

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
            pod.setPower(0.022);
        }
        if(opMode.gamepad2.dpad_right){
            vrashPower = Range.clip(vrashPower + 0.02, -0.95,1);
            vrash.setPower(vrashPower);
        } else if (opMode.gamepad2.dpad_left) {
            vrashPower = -0.93;
            vrash.setPower(vrashPower);
        }
        if(opMode.gamepad2.left_bumper){
            kleshPower  = -0.3;
//        } else if (opMode.gamepad2.b) {
//            not = true;
//            VerticalSlider.kleshPower = -0.3;
//            opMode.sleep(200);
//            vrashPower = -0.9;
//            opMode.sleep(200);
//            VerticalSlider.kleshPower = 0;
//            opMode.sleep(200);
//            vrashPower = 0;
//            opMode.sleep(200);
//            not = false;
        }else if (!not){
            kleshPower = 0;
        }
        if (opMode.gamepad2.b){
            vrashPower = 1;
            vrash.setPower(vrashPower);
        }

        if (opMode.gamepad1.y){
            sample.setPower(0.73);
        } else if (opMode.gamepad1.a){
            sample.setPower(-0.75);}
//        } else {
//            sample.setPower(0);
//        }

    }
}
