package org.firstinspires.ftc.teamcode.Mechanism;

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
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Utils.Config;
import org.firstinspires.ftc.teamcode.Utils.PhantomMath;

public class WheelBase {
    public DcMotorEx rightFront, leftFront, rightBack, leftBack;
    Config config;
    /*
            |               |
            |pos2       pos1|
            |               |
            |               |
            |     pos3      |
    pos1, pos2 - энкодеры стоящие для прямого движения, параллельны обычным колесам
    pos3 - энкодер, перпендикулярный обычным колесам, движение вбок
     */
    double pos1, pos2, pos3;

    double rev1, rev2, rev3;

    public double distance1, distance2, distance3;

    final double odoLength = 48 * Math.PI;

    final int odoCRP = 2000;

    MotorEx rf, lf, rr, lr;

    public MecanumDrive mecanumDrive = new MecanumDrive(lf,rf,lr,rr);
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
    Thread getPos = new Thread(() -> {
        while (true){
            pos1 = rightFront.getCurrentPosition();
            pos2 = leftBack.getCurrentPosition();
            pos3 = rightBack.getCurrentPosition();
            rev1 = pos1 / odoCRP;
            rev2 = pos2 / odoCRP;
            rev3 = pos3 / odoCRP;
            distance1 =  rev1 * odoLength;
            distance2 = rev2 * odoLength;
            distance3 = rev3 * odoLength;
        }
    });
    public void PIDFtester(double power, double distance){
        getPos.start();
    }

    /**
     * счетчик оборотов мертвых колес
     */
    public void OdoCounter(){
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
