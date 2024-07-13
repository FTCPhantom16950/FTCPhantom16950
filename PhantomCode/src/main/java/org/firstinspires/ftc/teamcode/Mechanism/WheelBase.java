package org.firstinspires.ftc.teamcode.Mechanism;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Utils.Config;
import org.firstinspires.ftc.teamcode.Utils.FTCcontroolers;
import org.firstinspires.ftc.teamcode.Utils.PhantomIMU;
import org.firstinspires.ftc.teamcode.Utils.PhantomMath;

public class WheelBase {
    public DcMotorEx rightFront, leftFront, rightBack, leftBack;
    Config config;
    LinearOpMode opMode;
    public WheelBase(LinearOpMode opMode) {
        this.opMode = opMode;
    }
    PhantomIMU phantomIMU = new PhantomIMU();
    PhantomMath math = new PhantomMath(opMode);
    FTCcontroolers ftcControolers = new FTCcontroolers(opMode);

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
    double forward;
    double spin;
    double side;


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
    public void moveForward(double pos){
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        ftcControolers.PIDFstarter(pos, rightFront);
        ftcControolers.PIDFstarter(pos, rightBack);
        ftcControolers.PIDFstarter(pos, leftFront);
        ftcControolers.PIDFstarter(pos, leftBack);
    }

    public void moveBack(double pos){
        moveForward(-pos);
    }
    public void stopAll(){
        leftBack.setPower(0);
        leftFront.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);
    }
    Thread gamepads = new Thread(() -> {
        Gamepad gamepad1 = opMode.gamepad1;
        double rbump = 0;
        double lbump = 0;
        while (opMode.opModeIsActive()){
            if (gamepad1.left_bumper){
                lbump = 0.4;
            }
            if (gamepad1.right_bumper){
                rbump = 0.4;
            }
            forward = Range.clip(gamepad1.left_stick_y + gamepad1.right_stick_y * 0.4, -1, 1);
            side = Range.clip(gamepad1.left_stick_x + gamepad1.right_stick_x * 0.4, -1, 1);
            spin = Range.clip(gamepad1.right_trigger - gamepad1.left_trigger + rbump - lbump, -1, 1);
        }
    });
    public void driveFieldCentic(){
        gamepads.start();
        double rot = spin;
        double rfSpeed, rbSpeed, lfSpeed, lbSpeed;
        IMU imu = phantomIMU.imu;

        double rotationX, rotationY, heading;
        heading = -imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double currentAngel = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        double angleDifference = math.normalizeAngles(currentAngel - rot);
        double rotaton = Range.clip(angleDifference, -1,1);

        rotationX = forward * Math.cos(heading) - side * Math.sin(heading);
        rotationY = forward * Math.sin(heading) + side * Math.cos(heading);

        double denominator = Math.max(Math.abs(side) + Math.abs(forward) + Math.abs(rot), 1);
        rfSpeed = (rotationY - rotationX - rotaton) / denominator;
        rbSpeed = (rotationY + rotationX - rotaton) / denominator;
        lfSpeed = (rotationY + rotationX + rotaton) / denominator;
        lbSpeed = (rotationY- rotationX + rotaton) / denominator;

        rightFront.setPower(rfSpeed);
        rightBack.setPower(rbSpeed);
        leftFront.setPower(lfSpeed);
        leftBack.setPower(lbSpeed);
        stopAll();
    }


    public  class MecanumDrive{


        double rfSpeed, rbSpeed, lfSpeed, lbSpeed;

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



