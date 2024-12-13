package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorImplEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Podves {
    LinearOpMode opMode;
    double runPowers1 = -1;
    double runPowers2 = 1;
    public Podves(LinearOpMode opMode) {
        this.opMode = opMode;
    }
    HardwareMap hw;
    public DcMotorEx podv1, podv2;
    public void init(){
        hw = opMode.hardwareMap;
        podv1 = hw.get(DcMotorEx.class, "podv1");
        podv2 = hw.get(DcMotorImplEx.class, "podv2");
        podv1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        podv2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        podv1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        podv2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        podv2.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void run(){
        if(opMode.gamepad1.x){
            podv2.setPower(runPowers1);
            podv1.setPower(runPowers1);
        } else if (opMode.gamepad1.b){
            podv1.setPower(runPowers2);
            podv2.setPower(runPowers2);
        } else{
            podv1.setPower(0.01);
            podv2.setPower(0.01);
        }
    }

}
