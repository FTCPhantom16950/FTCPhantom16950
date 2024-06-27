package org.firstinspires.ftc.teamcode.Mechanism;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class WheelBase {
    MotorEx rightFront, rightBack, leftFront, leftBack;
    public double countFOdo, countSOdo, countTOdo;
    public PIDFController pidfController = new PIDFController(0,0,0,0);
    MotorGroup wheels = new MotorGroup(leftFront, rightFront, leftBack, rightBack);
    public MecanumDrive wheelbase = new MecanumDrive(leftFront,rightFront,leftBack,rightBack);
    public void initWheelBase(HardwareMap hw){
        rightBack = new MotorEx(hw, "rb", Motor.GoBILDA.RPM_312);
        rightFront = new MotorEx(hw, "rf", Motor.GoBILDA.RPM_312);
        leftBack = new MotorEx(hw,"lb", Motor.GoBILDA.RPM_312);
        leftFront = new MotorEx(hw, "lf", Motor.GoBILDA.RPM_312);
        wheels.setRunMode(Motor.RunMode.VelocityControl);
        wheels.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        wheels.stopAndResetEncoder();
        leftFront.encoder = rightFront.encoder;
    }
    public void OdoCounter(HardwareMap hw){
        countFOdo = leftFront.encoder.getRevolutions();
        countSOdo = rightBack.encoder.getRevolutions();
        countTOdo = leftBack.encoder.getRevolutions();
    }

    public void turn(){

    }

}
