package org.firstinspires.ftc.teamcode.Mechanism;

import android.content.Context;
import android.widget.Toast;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorImplEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Utils.Config;
import org.firstinspires.ftc.teamcode.Utils.PhantomMath;

import javax.annotation.Nullable;

public class WheelBase {
    DcMotorEx rightFront, leftFront, rightBack, leftBack;
    Config config;
    Context context = new FtcRobotControllerActivity();
    /*
            |               |
            |pos2       pos1|
            |               |
            |               |
            |     pos3      |
    pos1, pos2 - энкодеры стоящие для прямого движения, параллельны обычным колесам
    pos3 - энкодер, перпендикулярный обычным колесам, движение вбок
     */


    MotorEx rf, lf, rr, lr;


    /**
     * инициализация всех моторов колесной базы
     * @param hw HardwareMap
     */
    public void initWheelBase(HardwareMap hw){
        rightFront = hw.get(DcMotorEx.class, "rf");
        leftFront = hw.get(DcMotorEx.class, "lf");
        rightBack = hw.get(DcMotorEx.class, "rb");
        leftBack = hw.get(DcMotorEx.class, "lb");

        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        rf = new MotorEx(hw, "rf", Motor.GoBILDA.RPM_312);
        lf = new MotorEx(hw, "lf", Motor.GoBILDA.RPM_312);
        rr = new MotorEx(hw, "rr", Motor.GoBILDA.RPM_312);
        lr = new MotorEx(hw, "lr", Motor.GoBILDA.RPM_312);
    }

    public class MecanumDrive{
        Telemetry telemetry;
        double forward;
        double spin;
        double side;
        double rfSpeed, rbSpeed, lfSpeed, lbSpeed;
        public MecanumDrive(double forward, double side, double spin) {
            this.forward = forward;
            this.side = side;
            this.spin = spin;
        }
        Thread update = new Thread(() -> {
            while (true){
                 rfSpeed = Range.clip(forward - spin - side, -1, 1);
                 rbSpeed = Range.clip(forward - spin + side, -1,1);
                 lfSpeed = Range.clip(forward + spin + side, -1, 1);
                 lbSpeed = Range.clip(forward + spin - side, -1,1);
            }
        });
        public void driveEasy() {
            update.start();
            rightFront.setPower(rfSpeed);
            rightBack.setPower(rbSpeed);
            leftFront.setPower(lfSpeed);
            leftBack.setPower(lbSpeed);
        }

        }
    }



