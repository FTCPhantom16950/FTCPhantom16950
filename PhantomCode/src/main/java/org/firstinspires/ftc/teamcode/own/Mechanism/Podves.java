package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorImplEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.TeleOpActions;

public class Podves {
    com.qualcomm.robotcore.hardware.CRServo left, right;
    LinearOpMode opMode;
    double runPowers1 = -1;
    double runPowers2 = 1;
    public Podves(LinearOpMode opMode) {
        this.opMode = opMode;
    }
    public boolean inited = false;
    HardwareMap hw;
    public static DcMotorEx podv1, podv2;
    public void init(){
        hw = opMode.hardwareMap;
        left = hw.get(com.qualcomm.robotcore.hardware.CRServo.class, "lP");
        right = hw.get(com.qualcomm.robotcore.hardware.CRServo.class, "rP");
        podv1 = hw.get(DcMotorEx.class, "podv1");
        podv2 = hw.get(DcMotorImplEx.class, "podv2");
        podv1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        podv2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        podv1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        podv2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        podv2.setDirection(DcMotorSimple.Direction.REVERSE);
        left.setDirection(DcMotorSimple.Direction.REVERSE);
        left.setPower(-0.5);
        right.setPower(-0.5);
        inited = true;
    }
    public TeleOpActions teleOpActions = new TeleOpActions() {
        @Override
        public void play() {
            Podves.this.run();
        }
    };
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
        if(opMode.gamepad1.dpad_up){
            left.setPower(0.5);
            right.setPower(0.5);
        } else if (opMode.gamepad1.dpad_down){
            left.setPower(-0.8);
            right.setPower(-0.8);
        }
        if(opMode.gamepad1.dpad_left){
            left.setPower(0.3);
            right.setPower(0.3);
        } else if(opMode.gamepad1.dpad_right){
            left.setPower(-0.3);
            right.setPower(-0.3);
        }
    }

}
