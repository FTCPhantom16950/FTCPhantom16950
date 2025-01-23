package org.firstinspires.ftc.teamcode.own.Mechanism;


import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.brat;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.brat2;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.colorSensor;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krut2_start_power;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krut_start_power;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.positions.VerticalPOS;

public class VerticalSlider{
    ElapsedTime timer = new ElapsedTime();
    public static RevColorSensorV3 verx_color;
    boolean once;
    LinearOpMode opMode;
    public boolean colorState = false , previousColorState = colorState;
    double  output = 0, targetPos = 0;
    // creation of the PID object
    HardwareMap hw;
    public VerticalSlider(LinearOpMode opMode){
        this.opMode = opMode;
    }
    public static CRServo vrash, klesh, sample;
    public static DcMotorEx pod;
    public static double podPower = 0, vrashPower = -0.5, kleshPower = 0;
    public static boolean kleshgo = false;
    static VerticalPOS.KLESHPOS verticalPOS, prevPos ;
    boolean pressed = false;
    public void init(){
        hw = opMode.hardwareMap;
        verx_color = opMode.hardwareMap.get(RevColorSensorV3.class, "color_verx");
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

   public void play(){
            if (kleshgo && verticalPOS == VerticalPOS.KLESHPOS.ZAXVAT){
                kleshPower = -0.35;
                klesh.setPower(kleshPower);
                kleshgo = false;
            } else if (kleshgo && verticalPOS == VerticalPOS.KLESHPOS.OTPUSK) {
                kleshPower = 0;
                klesh.setPower(kleshPower);
                kleshgo = false;
            } else if (verx_color.getDistance(DistanceUnit.MM) <= 28 && kleshPower !=-0.25 && timer.milliseconds() <= 200){
                opMode.sleep(200);
                kleshPower = -0.35;
                klesh.setPower(kleshPower);
            }
            if (verx_color.getDistance(DistanceUnit.MM) <= 28 && once){
                timer.startTime();
                once = false;
            } else if (verx_color.getDistance(DistanceUnit.MM) >= 45){
                timer.reset();
                once = true;
            }
       if (opMode.gamepad1.a){
                sample.setPower(-0.85);
            }else if (opMode.gamepad1.y){
                sample.setPower(0.71);
            }
    }
    public void run(){
        play();
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
            vrashPower = Range.clip(vrashPower + 0.1, -0.49,1);
            vrash.setPower(vrashPower);
        } else if (opMode.gamepad2.dpad_left) {
            vrashPower = -0.49;
            vrash.setPower(vrashPower);
        }
        if(opMode.gamepad2.left_bumper) {
            if (verticalPOS == VerticalPOS.KLESHPOS.ZAXVAT){
                verticalPOS = VerticalPOS.KLESHPOS.OTPUSK;
                opMode.sleep(100);
                kleshgo = true;
            } else if (verticalPOS == VerticalPOS.KLESHPOS.OTPUSK) {
                verticalPOS = VerticalPOS.KLESHPOS.ZAXVAT;
                opMode.sleep(100);
                kleshgo = true;
            }
        }
    }
    public void podvesSpiecMan(){
        Config.ACTIONINWORK = true;
        pod.setPower(0.7);
        opMode.sleep(700);
        pod.setPower(0.13);
        opMode.sleep(200);
        Config.ACTIONINWORK = false;
    }
    public void podvesSample(){
        Config.ACTIONINWORK = true;
        kleshPower = -0.25;
        klesh.setPower(kleshPower);
        vrash.setPower(-0.93);
        opMode.sleep(500);
        kleshPower = 0;
        klesh.setPower(kleshPower);
        opMode.sleep(400);
        vrash.setPower(-0.5);
        pod.setPower(1);
        opMode.sleep(900);
        pod.setPower(0.15);
        vrash.setPower(1);
        opMode.sleep(500);
        kleshPower = -0.3;
        klesh.setPower(kleshPower);
        opMode.sleep(500);
        vrash.setPower(-0.5);
        opMode.sleep(500);
        Config.ACTIONINWORK = false;
    }
    public void podvesSamplelast(){
        Config.ACTIONINWORK = true;
        kleshPower = -0.25;
        klesh.setPower(kleshPower);
        vrash.setPower(-0.93);
        opMode.sleep(500);
        kleshPower = 0;
        klesh.setPower(kleshPower);
        opMode.sleep(400);
        vrash.setPower(-0.5);
        pod.setPower(1);
        opMode.sleep(900);
        pod.setPower(0.15);
        vrash.setPower(1);
        opMode.sleep(500);
        kleshPower = -0.3;
        klesh.setPower(kleshPower);
        Config.ACTIONINWORK = false;
    }
    public void perviPodem(){
        Config.ACTIONINWORK = true;
        vrash.setPower(-0.1);
        VerticalSlider.pod.setPower(1.0);
        opMode.sleep(300);
        pod.setPower(0.13);
        Config.ACTIONINWORK = false;
    }
    public void spuskPosleBucket(){
        Config.ACTIONINWORK = true;
        opMode.sleep(1000);
        pod.setPower(-0.9);
        opMode.sleep(900);
        pod.setPower(0.15);
        brat.setPower(krut_start_power);
        brat2.setPower(krut2_start_power);
        Config.ACTIONINWORK = false;
    }
}
