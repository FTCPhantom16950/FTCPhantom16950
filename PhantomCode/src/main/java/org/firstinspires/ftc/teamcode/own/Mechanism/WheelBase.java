package org.firstinspires.ftc.teamcode.own.Mechanism;

import androidx.lifecycle.GenericLifecycleObserver;

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
import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomIMU;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomMath;

public class WheelBase {
    // объявляем конфиг
    Config config = new Config();
    // объявляем моторы через DcMotorEx
    public DcMotorEx rightFront, leftFront, rightBack, leftBack;
    // объявляем опмод для считывания данных
    LinearOpMode opMode;
    // конструктор для получения режима

    public WheelBase(LinearOpMode opMode) {
        this.opMode = opMode;
    }
    double rot;
    double rfSpeed, rbSpeed, lfSpeed, lbSpeed;
    double resultX, resultY, heading;
    double rotation;

    // создаем иму
    PhantomIMU phantomIMU = new PhantomIMU();
    // создаем класс расчетов



    /*
            |               |
            |pos2       pos1|
            |               |
            |               |
            |     pos3      |
    pos1, pos2 - энкодеры стоящие для прямого движения, параллельны обычным колесам
    pos3 - энкодер, перпендикулярный обычным колесам, движение вбок
     */

    // объявляем моторы через MotorEx
    MotorEx rf, lf, rr, lr;
    // значения скоростей
    double y;
    double spin;
    double x;
    double denominator;
    double currentAngel;
    double angleDifference;
    double rbump = 0;
    double lbump = 0;


    /**
     * инициализация всех моторов колесной базы
     * @param hw HardwareMap
     */
    public void initWheelBase(HardwareMap hw){
        // инициализируем моторы
        rightFront = hw.get(DcMotorEx.class, "rf");
        leftFront = hw.get(DcMotorEx.class, "rb");
        rightBack = hw.get(DcMotorEx.class, "lf");
        leftBack = hw.get(DcMotorEx.class, "lb");

        // сбрасываем энкодеры
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // устанавливаем режим моторов
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // устанавливаем установки при подачи 0 питания
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // устанавливаем направление моторов
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        phantomIMU.initIMU(opMode.hardwareMap);
//        // создаем моторы из библиотеки FTCLib
//        rf = new MotorEx(hw, config.right_front, Motor.GoBILDA.RPM_312);
//        lf = new MotorEx(hw, config.left_front, Motor.GoBILDA.RPM_312);
//        rr = new MotorEx(hw, config.right_back, Motor.GoBILDA.RPM_312);
//        lr = new MotorEx(hw, config.left_front, Motor.GoBILDA.RPM_312);
    }
    public void moveForward(double pos){
        //
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //

    }
    //
    public void moveBack(double pos){
        moveForward(-pos);
    }
    //
    public void stopAll(){
        //
        leftBack.setPower(0);
        leftFront.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);
    }

    //
    public void gamepads(Gamepad gamepad1){
        //
        if (gamepad1.left_bumper){
            lbump = 0.4;
        } else{
            lbump = 0;
        }
        if (gamepad1.right_bumper){
            rbump = 0.4;
        }
        else{
            rbump = 0;
        }
            //
        y = -Range.clip(gamepad1.left_stick_y + gamepad1.right_stick_y * 0.4, -1, 1);
        x = Range.clip(gamepad1.left_stick_x + gamepad1.right_stick_x * 0.4, -1, 1);
        spin = Range.clip(gamepad1.right_trigger - gamepad1.left_trigger + rbump - lbump, -1, 1);
        if (x <= 0.1 && x >= -0.1){
            x = 0;
        }
        if (y <= 0.1 && y >= -0.1){
            y = 0;
        }
        if (spin <= 0.1 && spin >= -0.1){
            spin = 0;
        }
    }

    //
    public void driveFieldCentric(Gamepad gamepad){
        //  считываем данные с геймпадов
        gamepads(gamepad);
        // Объявляем переменные и гироскоп
        IMU imu = phantomIMU.imu;
        // считываем значение изначального направления
        heading = -phantomIMU.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        // нормализуем необходимый нам угол поворота робота и округляем от 1 до -1
        currentAngel = (imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES) * 0.01);
        angleDifference = AngleUnit.normalizeDegrees(currentAngel - spin);
        rotation = Range.clip(angleDifference * 0.5, -1,1);
        if (rotation <= 0.01 && rotation >= -0.01){
            rotation = 0;
        }
        // https://matthew-brett.github.io/teaching/rotation_2d.html здесь объяснение
        resultX = x * Math.cos(heading) - y * Math.sin(heading);
        resultY = x * Math.sin(heading) + y * Math.cos(heading);
        // максимальное значение скорости моторов
        denominator = Math.max(Math.abs(resultY) + Math.abs(resultX) + Math.abs(spin), 1);
        //https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html#deriving-mecanum-control-equations смотреть векторы
        //TODO: поиграться с вектором rotation
        rfSpeed = (resultY - resultX - rotation) / denominator;
        rbSpeed = (resultY + resultX - rotation) / denominator;
        lfSpeed = (resultY + resultX + rotation) / denominator;
        lbSpeed = (resultY - resultX + rotation) / denominator;
        opMode.telemetry.addData("rbspeed", rbSpeed);
        opMode.telemetry.addData("rfspeed", rfSpeed);
        opMode.telemetry.addData("lbspeed", lbSpeed);
        opMode.telemetry.addData("lfspeed", lfSpeed);
        opMode.telemetry.addData("denominator", denominator);
        opMode.telemetry.addData("resultY", resultY);
        opMode.telemetry.addData("resultX", resultX);
        opMode.telemetry.addData("rotation", rotation);
        opMode.telemetry.addData("heading", currentAngel);
        opMode.telemetry.addData("spin", spin);
        opMode.telemetry.addData("curr angle", currentAngel);
        opMode.telemetry.addData("differebce", angleDifference);
        opMode.telemetry.update();
        // Устанавливаем скорость моторам
        rightFront.setPower(rfSpeed);
        rightBack.setPower(rbSpeed);
        leftFront.setPower(lfSpeed);
        leftBack.setPower(lbSpeed);
    }
    // объявляем переменные скоростей


    public void driveEasy(Gamepad gamepad) {

        gamepads(gamepad);
        rfSpeed = Range.clip(y - spin - x, -1, 1);
        rbSpeed = Range.clip(y - spin + x, -1,1);
        lfSpeed = Range.clip(y + spin + x, -1, 1);
        lbSpeed = Range.clip(y + spin - x, -1,1);
        opMode.telemetry.addData("rbspeed", rbSpeed);
        opMode.telemetry.addData("rfspeed", rfSpeed);
        opMode.telemetry.addData("lbspeed", lbSpeed);
        opMode.telemetry.addData("lfspeed", lfSpeed);
        opMode.telemetry.update();
        // подстановка в моторы
        rightFront.setPower(rfSpeed);
        rightBack.setPower(rbSpeed);
        leftFront.setPower(lfSpeed);
        leftBack.setPower(lbSpeed);
        stopAll();
    }



    }



