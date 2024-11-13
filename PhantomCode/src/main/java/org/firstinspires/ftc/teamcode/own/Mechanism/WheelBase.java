package org.firstinspires.ftc.teamcode.own.Mechanism;


import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomIMU;

public class WheelBase {
    /*
    ОБЪЯВЛЯЕМ ПЕРЕМЕННЫЕ
     */
    // объявляем моторы через DcMotorEx
    public DcMotorEx rightFront, leftFront, rightBack, leftBack;
    // объявляем опмод для считывания данных
    LinearOpMode opMode;

    // конструктор для получения режима
    public WheelBase(LinearOpMode opMode) {
        this.opMode = opMode;
    }
    //скорости для моторов
    double rfSpeed, rbSpeed, lfSpeed, lbSpeed;
    //проекция результируещего вектора на оси
    double resultX, resultY;


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

    // значения скоростей с геймпадов
    double y;
    double spin;
    double x;
    // число уменьшающее значение всех скоростей в рамки от 1 до -1
    double denominator;
    //значения правого и левого бампера
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

//        устанавливаем режим моторов
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // устанавливаем установки при подачи 0 питания
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // устанавливаем направление моторов
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        phantomIMU.initIMU(opMode.hardwareMap);
        phantomIMU.valueGetter();
    }
//    //TODO: сделать двжение вперед без роадраннера
//    public void moveForward(double pos){
//
//    }
//    //TODO: сделать двжение назад без роадраннера
//    public void moveBack(double pos){
//        moveForward(-pos);
//    }
    // остановка всех моторов
/*   public void stopAll(){
       leftBack.setPower(0);
       leftFront.setPower(0);
       rightBack.setPower(0);
       rightFront.setPower(0);
    }*/

    // считываем значения с геймпадов
    public void gamepads(@NonNull Gamepad gamepad1){
        //
        if (gamepad1.left_bumper){
            lbump = 0.3;
        } else{
            lbump = 0;
        }
        if (gamepad1.right_bumper){
            rbump = 0.3;
        }
        else{
            rbump = 0;
        }
            //
        y = -Range.clip(gamepad1.left_stick_x + gamepad1.right_stick_x * 0.4, -1, 1);
        x = Range.clip(gamepad1.left_stick_y + gamepad1.right_stick_y * 0.4, -1, 1);
        spin = Range.clip(-gamepad1.right_trigger + gamepad1.left_trigger - rbump + lbump, -1, 1);
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

    // движение относительно центра аоля
    public void driveFieldCentric(Gamepad gamepad){
        //  считываем данные с геймпадов
        gamepads(gamepad);
        // считываем значение изначального направления
        // нормализуем необходимый нам угол поворота робота и округляем от 1 до -1
//        currentAngel = (phantomIMU.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
//        angleDifference = AngleUnit.normalizeDegrees(currentAngel - spin);
//        rotation = Range.clip(angleDifference * 0.01, -1,1);
//        if (rotation <= 0.01 && rotation >= -0.01){rotation = 0;}
        // https://matthew-brett.github.io/teaching/rotation_2d.html здесь объяснение
        resultX = x * Math.cos(-phantomIMU.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS)) - y * Math.sin(-phantomIMU.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));
        resultY = x * Math.sin(-phantomIMU.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS)) + y * Math.cos(-phantomIMU.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));
        // максимальное значение скорости моторов
        denominator = Math.max(Math.abs(resultY) + Math.abs(resultX) + Math.abs(spin), 1);
        //https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html#deriving-mecanum-control-equations смотреть векторы
        //TODO: поиграться с вектором rotation
        rfSpeed = (resultY - resultX - spin) / denominator;
        rbSpeed = (resultY + resultX - spin) / denominator;
        lfSpeed = (resultY + resultX + spin) / denominator;
        lbSpeed = (resultY - resultX + spin) / denominator;
        // выводим всю возможную телеметрию
        opMode.telemetry.addData("rbspeed", rbSpeed);
        opMode.telemetry.addData("rfspeed", rfSpeed);
        opMode.telemetry.addData("lbspeed", lbSpeed);
        opMode.telemetry.addData("lfspeed", lfSpeed);
        opMode.telemetry.addData("denominator", denominator);
        opMode.telemetry.addData("resultY", resultY);
        opMode.telemetry.addData("resultX", resultX);
        opMode.telemetry.addData("spin", spin);
        opMode.telemetry.addData("rightBack", rightBack.getPower());
        opMode.telemetry.addData("leftBack", leftBack.getPower());
        opMode.telemetry.addData("rightFront", rightFront.getPower());
        opMode.telemetry.addData("leftFront", leftFront.getPower());
        opMode.telemetry.addData("rightBackCurr", rightBack.getCurrent(CurrentUnit.AMPS));
        opMode.telemetry.addData("leftBackCurr", leftBack.getCurrent(CurrentUnit.AMPS));
        opMode.telemetry.addData("rightFrontCurr", rightFront.getCurrent(CurrentUnit.AMPS));
        opMode.telemetry.addData("leftFrontCurr", leftFront.getCurrent(CurrentUnit.AMPS));
        opMode.telemetry.addData("CurrAngle", phantomIMU.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        opMode.telemetry.update();
        // Устанавливаем скорость моторам
        rightFront.setPower(rfSpeed);
        rightBack.setPower(rbSpeed);
        leftFront.setPower(lfSpeed);
        leftBack.setPower(lbSpeed);

    }


    public void driveEasy(Gamepad gamepad)  {
        //активируем геймпады
        gamepads(gamepad);
        //https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html#deriving-mecanum-control-equations смотреть векторы
        rfSpeed = (y - spin - x);
        rbSpeed = (y - spin + x);
        lfSpeed = (y + spin + x);
        lbSpeed = (y + spin - x);
        // выводим всю возможную телеметрию
        opMode.telemetry.addData("rbspeed", rbSpeed);
        opMode.telemetry.addData("rfspeed", rfSpeed);
        opMode.telemetry.addData("lbspeed", lbSpeed);
        opMode.telemetry.addData("lfspeed", lfSpeed);
        opMode.telemetry.addData("rbtick", rightBack.getCurrentPosition());
        opMode.telemetry.addData("rftick", rightFront.getCurrentPosition());
        opMode.telemetry.addData("lbtick", leftBack.getCurrentPosition());
        opMode.telemetry.addData("lftick", leftFront.getCurrentPosition());
        opMode.telemetry.addData("rightBack", rightBack.getPower());
        opMode.telemetry.addData("leftBack", leftBack.getPower());
        opMode.telemetry.addData("rightFront", rightFront.getPower());
        opMode.telemetry.addData("leftFront", leftFront.getPower());
        opMode.telemetry.addData("rightBackCurr", rightBack.getCurrent(CurrentUnit.AMPS));
        opMode.telemetry.addData("leftBackCurr", leftBack.getCurrent(CurrentUnit.AMPS));
        opMode.telemetry.addData("rightFrontCurr", rightFront.getCurrent(CurrentUnit.AMPS));
        opMode.telemetry.addData("leftFrontCurr", leftFront.getCurrent(CurrentUnit.AMPS));
        opMode.telemetry.update();
        // подстановка в моторы
        rightFront.setPower(rfSpeed);
        rightBack.setPower(rbSpeed);
        leftFront.setPower(lfSpeed);
        leftBack.setPower(lbSpeed);
    }

}



