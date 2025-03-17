package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorImplEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.PhMath;
import org.firstinspires.ftc.teamcode.own.Utils.TeleOpActions;
import org.firstinspires.ftc.teamcode.own.positions.ZxPos;

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
        if (opMode.gamepad2.left_stick_y >= 0.1 && Math.abs(left.getPower()) > 0.65){
            podv1.setPower(Math.pow(opMode.gamepad2.left_stick_y, 3));
            podv2.setPower(Math.pow(opMode.gamepad2.left_stick_y, 3));
        }
        else if(opMode.gamepad1.dpad_left){
            Zxnew.krutpos = ZxPos.KRUT.Sputnik;
            Zxnew.krutgo = true;
            podv1.setPower(-1);
            podv2.setPower(-1);
            left.setPower(PhMath.fromDegreesToPower(230, 270));
            right.setPower(PhMath.fromDegreesToPower(230, 270));
            opMode.sleep(600);
            podv1.setPower(0.015);
            podv2.setPower(0.015);
        } else if(opMode.gamepad1.dpad_right){
            left.setPower(-0.6);
            right.setPower(-0.5);
        } else{
            podv1.setPower(0.015);
            podv2.setPower(0.015);
        }
    }

}
