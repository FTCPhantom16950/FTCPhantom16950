package org.firstinspires.ftc.teamcode.own.Mechanism;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.message.redux.ReceiveGamepadState;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomIMU;

public class WheelBase{

    /*
    ОБЪЯВЛЯЕМ ПЕРЕМЕННЫЕ
     */
    // объявляем моторы через DcMotorEx
    public DcMotorEx rightFront, leftFront, rightBack, leftBack;
    // объявляем опмод для считывания данных
    LinearOpMode opMode;

    // конструктор для получения режима
    public WheelBase(LinearOpMode opMode) {
        this.gamepad1 = opMode.gamepad1;
        this.opMode = opMode;
    }
    //скорости для моторов
    public double rfSpeed, rbSpeed, lfSpeed, lbSpeed;
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
    Config config = new Config();

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
    Gamepad gamepad1;
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
    public void gamepads(){
        gamepad1 = opMode.gamepad1;
        if (opMode.gamepad2.right_stick_x == 1 && opMode.gamepad2.left_stick_x  == -1){
          config.setAUTOMODE(!config.isAUTOMODE());
        }
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
    public void driveFieldCentric(){
        //  считываем данные с геймпадов
        gamepads();
        phantomIMU.valueGetter();
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
        rfSpeed = (resultY - resultX - spin) / denominator;
        rbSpeed = (resultY + resultX - spin) / denominator;
        lfSpeed = (resultY + resultX + spin) / denominator;
        lbSpeed = (resultY - resultX + spin) / denominator;

        // Устанавливаем скорость моторам
        rightFront.setPower(rfSpeed);
        rightBack.setPower(rbSpeed);
        leftFront.setPower(lfSpeed);
        leftBack.setPower(lbSpeed);

    }

    public void vpered(int pos, double power){
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setTargetPosition(pos);
        leftBack.setTargetPosition(pos);
        leftFront.setTargetPosition(pos);
        rightBack.setTargetPosition(pos);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        leftFront.setPower(power);
        while(rightFront.isBusy() && rightBack.isBusy() && leftFront.isBusy() && leftBack.isBusy() && opMode.opModeIsActive()){
            opMode.telemetry.addData("rbtick", rightBack.getCurrentPosition());
            opMode.telemetry.addData("rftick", rightFront.getCurrentPosition());
            opMode.telemetry.addData("lbtick", leftBack.getCurrentPosition());
            opMode.telemetry.addData("lftick", leftFront.getCurrentPosition());
            opMode.telemetry.update();
        }
        rightBack.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        leftFront.setPower(0);
    }
    public void nazad(int pos, double power){
        vpered(-pos, power);
    }
    public void vpravo(int pos, double power) {

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(-pos);
        rightBack.setTargetPosition(-pos);
        rightFront.setTargetPosition(pos);
        leftBack.setTargetPosition(-pos);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setPower(power);
        rightBack.setPower(power);
        rightFront.setPower(power);
        leftBack.setPower(power);

        while (opMode.opModeIsActive() && (leftFront.isBusy()) && (rightFront.isBusy()) && (rightBack.isBusy()) && (leftBack.isBusy())) {

        }

        // Stop all motion;
        rightBack.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        leftFront.setPower(0);
        opMode.sleep(100);

    }
    public void vlevo(int pos, double power){
        vpravo(-pos, power);
    }


    public void driveEasy()  {
        //активируем геймпады
        gamepads();
        //https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html#deriving-mecanum-control-equations смотреть векторы
        rfSpeed = (y - spin - x);
        rbSpeed = (y - spin + x);
        lfSpeed = (y + spin + x);
        lbSpeed = (y + spin - x);
        // выводим всю возможную телеметрию

        opMode.telemetry.update();
        // подстановка в моторы
        rightFront.setPower(rfSpeed);
        rightBack.setPower(rbSpeed);
        leftFront.setPower(lfSpeed);
        leftBack.setPower(lbSpeed);
    }

    public void start(){
        if (!config.TELEOPIMU){
            driveEasy();
        } else if (config.TELEOPIMU){
            driveFieldCentric();
        }
    }
}



