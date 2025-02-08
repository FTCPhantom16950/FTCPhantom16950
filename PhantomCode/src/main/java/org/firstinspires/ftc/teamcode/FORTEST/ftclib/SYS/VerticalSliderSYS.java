package org.firstinspires.ftc.teamcode.FORTEST.ftclib.SYS;


import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.PIDControl;
import org.firstinspires.ftc.teamcode.own.positions.VerticalPOS;
@Config
public class VerticalSliderSYS extends SubsystemBase {
    public static double kP = 0, kD = 0, kI = 0;
    public static CRServo vrash, klesh, sample;
    public PIDControl control = new PIDControl();
    public static MotorEx pod;
    public static double vrashPower = -0.5, kleshPower = 0;
    VerticalPOS.KLESHPOS verticalPOS ;
    LinearOpMode opMode;

    HardwareMap hw;
    public VerticalSliderSYS(LinearOpMode opMode){
        this.opMode = opMode;
        hw = opMode.hardwareMap;
        pod = new MotorEx(hw, "pod");
        klesh = opMode.hardwareMap.get(CRServo.class, "klesh");
        vrash = opMode.hardwareMap.get(CRServo.class, "vrash");
        sample = opMode.hardwareMap.get(CRServo.class, "sample");
        klesh.setPower(kleshPower);
        vrash.setPower(vrashPower);
        sample.setPower(0.71);
        verticalPOS = VerticalPOS.KLESHPOS.ZAXVAT;
        pod.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        pod.stopAndResetEncoder();
        pod.setRunMode(Motor.RunMode.RawPower);
        control.setTolerance(100);
        control.setkD(kD);
        control.setkP(kP);
        control.setkI(kI);
    }


    public void init(){
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
