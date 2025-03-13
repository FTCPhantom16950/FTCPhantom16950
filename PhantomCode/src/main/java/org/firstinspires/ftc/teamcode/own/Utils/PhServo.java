package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class PhServo extends Thread{
    CRServo servo;
    LinearOpMode opMode;
    String name;
    Double startPower;
    double maxAngle;

    public PhServo(LinearOpMode opMode, String name, double maxAngle) {
        this.opMode = opMode;
        this.name = name;
        this.maxAngle = maxAngle;
    }

    public PhServo(LinearOpMode opMode, String name, Double startPower, double maxAngle) {
        this.opMode = opMode;
        this.name = name;
        this.startPower = startPower;
        this.maxAngle = maxAngle;
    }

    public void init(){
        HardwareMap hw = opMode.hardwareMap;
        servo = hw.get(CRServo.class, name);
        if (startPower != null){
            servo.setPower(startPower);
        }
    }
    public void setPower(double power){
        servo.setPower(PhMath.fromDegreesToPower(power, maxAngle));
    }
    public void setDirection(DcMotorSimple.Direction Direction){
        servo.setDirection(Direction);
    }

    public CRServo getServo() {
        return servo;
    }
}
