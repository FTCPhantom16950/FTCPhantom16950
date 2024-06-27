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
    // создаем моторы
    MotorEx rightFront, rightBack, leftFront, leftBack;
    // Переменные для числа оборотов каждого мертвого колеса
    public double countFOdo, countSOdo, countTOdo;
    // Коэфициент ПИДФ регулятора
    public PIDFController pidfController = new PIDFController(0,0,0,0);
    // группа моторов, 1 - ведущий мотор
    MotorGroup wheels = new MotorGroup(leftFront, rightFront, leftBack, rightBack);
    // класс колесной базы для телеопа
    public MecanumDrive wheelbase = new MecanumDrive(leftFront,rightFront,leftBack,rightBack);

    /**
     * инициализация всех моторов колесной базы
     * @param hw HardwareMap
     */
    public void initWheelBase(HardwareMap hw){
        // привязка всех моторов к конфигу робота
        rightBack = new MotorEx(hw, "rb", Motor.GoBILDA.RPM_312);
        rightFront = new MotorEx(hw, "rf", Motor.GoBILDA.RPM_312);
        leftBack = new MotorEx(hw,"lb", Motor.GoBILDA.RPM_312);
        leftFront = new MotorEx(hw, "lf", Motor.GoBILDA.RPM_312);
        // привязываем энкодер левого переднего мотора к правому для связи с мертвыми колесами
        leftFront.encoder = rightFront.encoder;
        /*
        Устанавливаем режим для движения моторов
        RawSpeed - движение мотора просто по заднанной ему скорости
        PositionControl - движение при помощи П коэффинциента пид регулятора, более точное чем RawSpeed, но менее чем VelocityCOntrol
        VelocityControl - движение по ПИДФ и ФидФорвард контроллеров, наиболее точное, сложное в настройке
         */
        wheels.setRunMode(Motor.RunMode.VelocityControl);
        // устанавливаем поведение моторов при 0 подаче энергии
        wheels.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        // сбрасываем значения энкодеров
        wheels.stopAndResetEncoder();

    }

    /**
     * счетчик оборотов мертвых колес
     */
    public void OdoCounter(){
        countFOdo = leftFront.encoder.getRevolutions();
        countSOdo = rightBack.encoder.getRevolutions();
        countTOdo = leftBack.encoder.getRevolutions();
    }

    /**
     * метод для поворота
     * @param degrees градусы задаваеммые для поворота
     * @param power скорость с которой будет двигаться робот
     */
    public void turn(double degrees, double power){

    }

    /**
     * движение вперед
     * @param millimetre дистанция на которую проедет робот
     * @param power скорость с которой будет двигаться робот
     */
    public void vpered(double millimetre, double power){

    }

    /**
     * движение влево
     * @param millimetre дистанция на которую проедет робот
     * @param power скорость с которой будет двигаться робот
     */
    public void vlevo(double millimetre, double power){

    }

}
