package org.firstinspires.ftc.teamcode.own.Mechanism;


import android.provider.SyncStateContract;

import androidx.annotation.NonNull;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomIMU;
import org.firstinspires.ftc.teamcode.own.Utils.TeleOpActions;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;


public class WheelBase{
    Follower follower;
    PathBuilder builder;

    final Pose toPark2 = new Pose(80.20253164556962,47.132007233273065, Math.toRadians(270));
    /*
    ОБЪЯВЛЯЕМ ПЕРЕМЕННЫЕ
     */
    // объявляем моторы через DcMotorEx
    public static DcMotorEx rightFront, leftFront, rightBack, leftBack;
    // объявляем опмод для считывания данных
    LinearOpMode opMode;

    // конструктор для получения режима
    public WheelBase(@NonNull LinearOpMode opMode) {
        this.gamepad1 = opMode.gamepad1;
        this.opMode = opMode;
    }
    //скорости для моторов
    public static double rfSpeed, rbSpeed, lfSpeed, lbSpeed;
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
    public double y;
    public double spin;
    public double x;
    // число уменьшающее значение всех скоростей в рамки от 1 до -1
    double denominator;
    //значения правого и левого бампера
    double rbump = 0;
    double lbump = 0;
    public boolean inited = false;
    Gamepad gamepad1;
    /**
     * инициализация всех моторов колесной базы
     * @param hw HardwareMap
     */
    public void initWheelBase(@NonNull HardwareMap hw){
        Constants.setConstants(FConstants.class, LConstants.class);
//        follower = new Follower(opMode.hardwareMap);
//        builder  = new PathBuilder();
        // инициализируем моторы
        gamepad1 = opMode.gamepad1;
        rightFront = hw.get(DcMotorEx.class, "rf");
        leftFront = hw.get(DcMotorEx.class, "lf");
        rightBack = hw.get(DcMotorEx.class, "rb");
        leftBack = hw.get(DcMotorEx.class, "lb");

        // сбрасываем энкодеры
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        follower = new Follower(hw, FConstants.class, LConstants.class);
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
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        phantomIMU.initIMU(opMode.hardwareMap);
        phantomIMU.valueGetter();
        inited = true;
        follower.setStartingPose(toPark2);
    }
    public Thread gamepad = new Thread(() ->{
        while (opMode.opModeIsActive()){
            gamepads();
        }
    });
    // считываем значения с геймпадов
    public void gamepads(){

//        if (opMode.gamepad2.right_stick_x == 1 && opMode.gamepad2.left_stick_x  == -1){
//          Config.AUTOMODE = !Config.AUTOMODE;
//        }
        //
        if (gamepad1.left_bumper){
            lbump = 0.6;
        } else{
            lbump = 0;
        }
        if (gamepad1.right_bumper){
            rbump = 0.6;
        }
        else{
            rbump = 0;
        }

        x = (smoothing(gamepad1.left_stick_x) + smoothing(gamepad1.right_stick_x) * 0.7);
        y = -(smoothing(gamepad1.left_stick_y) + smoothing(gamepad1.right_stick_y) * 0.7);
        spin = smoothing(gamepad1.right_trigger) + smoothing(-gamepad1.left_trigger) + rbump - lbump;
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
    public void vpravoEnvoder(double pos, double power){
        levoEncoder(-pos, -power);
    }
    public void levoEncoder(double pos, double power){
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setPower(-power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        leftFront.setPower(-power);
        double encdoerpos = 0;
        opMode.sleep(100);
        while(encdoerpos <= pos && opMode.opModeIsActive()){
            encdoerpos = leftFront.getCurrentPosition() ;
            opMode.telemetry.addData("rb", leftFront.getCurrentPosition());
            opMode.telemetry.addData("lb", leftBack.getCurrentPosition());
            opMode.telemetry.addData("rf", rightFront.getCurrentPosition());
            opMode.telemetry.addData("avg",encdoerpos);
            opMode.telemetry.update();
        }
        rightBack.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        leftFront.setPower(0);
        opMode.sleep(100);
    }
    public void vperedEncoder(double pos, double power){
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setPower(-power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        leftFront.setPower(-power);
        double encdoerpos = 0;
        opMode.sleep(100);
        while(encdoerpos <= pos && opMode.opModeIsActive()){
            encdoerpos = (rightFront.getCurrentPosition() + leftBack.getCurrentPosition()) / 2;
            opMode.telemetry.addData("rb", leftFront.getCurrentPosition());
                opMode.telemetry.addData("lb", leftBack.getCurrentPosition());
                opMode.telemetry.addData("rf", rightFront.getCurrentPosition());
                opMode.telemetry.addData("avg",encdoerpos);
                opMode.telemetry.update();
        }
        rightBack.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        leftFront.setPower(0);
        opMode.sleep(100);
    }
    public void razvarotEncoder(double pos, double power){
        pos = -pos;
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setPower(power);
        leftBack.setPower(-power);
        rightFront.setPower(power);
        leftFront.setPower(-power);
        opMode.sleep(100);
        double encdoerpos = 0;
        opMode.sleep(100);
        while(encdoerpos >= pos && opMode.opModeIsActive()){
            encdoerpos = (rightFront.getCurrentPosition() - leftBack.getCurrentPosition()) / 2;
            opMode.telemetry.addData("rb", leftFront.getCurrentPosition());
            opMode.telemetry.addData("lb", leftBack.getCurrentPosition());
            opMode.telemetry.addData("rf", rightFront.getCurrentPosition());
            opMode.telemetry.addData("avg",encdoerpos);
            opMode.telemetry.update();
        }
        rightBack.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        leftFront.setPower(0);
        opMode.sleep(100);
    }

    public void nazadEncoder(double pos, double power){
        pos = - pos;
        power = - power;
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setPower(-power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        leftFront.setPower(-power);
        double encdoerpos = 0;
        opMode.sleep(100);
        while(encdoerpos >= pos && opMode.opModeIsActive()){
            encdoerpos = (rightFront.getCurrentPosition() + leftBack.getCurrentPosition()) / 2;
            opMode.telemetry.addData("rb", leftFront.getCurrentPosition());
            opMode.telemetry.addData("lb", leftBack.getCurrentPosition());
            opMode.telemetry.addData("rf", rightFront.getCurrentPosition());
            opMode.telemetry.addData("avg",encdoerpos);
            opMode.telemetry.update();
        }
        rightBack.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        leftFront.setPower(0);
        opMode.sleep(100);
    }
    public void vpred_Taiming(long time, double power){
        ElapsedTime tiner = new ElapsedTime();
        rightBack.setPower(-power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        leftFront.setPower(-power);
        tiner.reset();
        if (opMode.opModeIsActive()){

            opMode.sleep(time);

        }
        rightBack.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        leftFront.setPower(0);
    }
    public void diag(long time, double power){
        ElapsedTime tiner = new ElapsedTime();
        rightBack.setPower(-power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        leftFront.setPower(-power);
        tiner.reset();
        if (opMode.opModeIsActive()){
            opMode.sleep(time);
        }
        rightBack.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        leftFront.setPower(0);
    }
    public void nazad_timing(long time, double power){
        vpred_Taiming(time, -power);
    }



    /*
    разварот - rf, rb, -lf, -lb

     */
    public void razvarot(long time, double power){
        ElapsedTime tiner = new ElapsedTime();
        rightBack.setPower(power);
        leftBack.setPower(-power);
        rightFront.setPower(power);
        leftFront.setPower(-power);
        tiner.reset();
        if (opMode.opModeIsActive()){
            opMode.sleep(time);
        }
        rightBack.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        leftFront.setPower(0);
    }
    // движение относительно центра аоля
    public void driveFieldCentric(){
        //  считываем данные с геймпадов
        gamepads();
        // https://matthew-brett.github.io/teaching/rotation_2d.html здесь объяснение
        resultX = x * Math.cos(-phantomIMU.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS)) - y * Math.sin(-phantomIMU.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));
        resultY = x * Math.sin(-phantomIMU.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS)) + y * Math.cos(-phantomIMU.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));
        // максимальное значение скорости моторов
        denominator = Math.max(Math.abs(resultY) + Math.abs(resultX) + Math.abs(spin), 1);
        //https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html#deriving-mecanum-control-equations смотреть векторы
        if (denominator > 1.0) {
            rfSpeed = (resultY - resultX - spin) / denominator;
            rbSpeed = (resultY + resultX - spin) / denominator;
            lfSpeed = (resultY + resultX + spin) / denominator;
            lbSpeed = (resultY - resultX + spin) / denominator;
        }
        // Устанавливаем скорость моторам
        rightFront.setPower(rfSpeed);
        rightBack.setPower(rbSpeed);
        leftFront.setPower(lfSpeed);
        leftBack.setPower(lbSpeed);

    }
    public void driveEasy()  {
        //активируем геймпады
        if(!gamepad.isAlive()){
            gamepad.start();
        }

        //https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html#deriving-mecanum-control-equations смотреть векторы
        rfSpeed = smoothing(y - spin - x);
        rbSpeed = smoothing(y - spin + x);
        lfSpeed = smoothing(y + spin + x);
        lbSpeed = smoothing(y + spin - x);
        double max_powers = Math.max(Math.abs(lfSpeed), Math.max(Math.abs(rfSpeed),
                Math.max(Math.abs(lbSpeed), Math.abs(rbSpeed))));
        if (max_powers > 1) {
            rfSpeed /= max_powers;
            rbSpeed /= max_powers;
            lfSpeed /= max_powers;
            lbSpeed /= max_powers;
        }
//        if (ds.getDistance(DistanceUnit.MM) <= 410){
//            rbSpeed = Range.clip(rbSpeed, -0.15, 1);
//            lbSpeed = Range.clip(lbSpeed, -0.15, 1);
//            rfSpeed = Range.clip(rfSpeed, -0.15, 1);
//            lfSpeed = Range.clip(lfSpeed, -0.15, 1);
//        }
        // подстановка в моторы
        rightFront.setPower(rfSpeed);
        rightBack.setPower(rbSpeed);
        leftFront.setPower(lfSpeed);
        leftBack.setPower(lbSpeed);
    }
    private double smoothing(double input){
        return Math.pow(input, 3);
    }

    private double to_target_speed(){
        return 0;
    }

    public void stop() {
        rightFront.setPower(0);
        rightBack.setPower(0);
        leftFront.setPower(0);
        leftBack.setPower(0);
    }
    public void to90degrees(){
        PathChain pathChain1 = follower.pathBuilder()
                .addPath(
                        // Line 1
                        new BezierLine(
                                new Point(follower.getPose()),
                                new Point(follower.getPose())
                        )
                )
                .setLinearHeadingInterpolation(follower.getPose().getHeading(), Math.toRadians(90))
                .build();
        PathChain pathChain2 = follower.pathBuilder()
                .addPath(
                        // Line 1
                        new BezierLine(
                                new Point(follower.getPose()),
                                new Point(follower.getPose())
                        )
                )
                .setLinearHeadingInterpolation(follower.getPose().getHeading(), Math.toRadians(270))
                .build();
        PathChain pathChain3 = follower.pathBuilder()
                .addPath(
                        // Line 1
                        new BezierLine(
                                new Point(follower.getPose()),
                                new Point(follower.getPose())
                        )
                )
                .setLinearHeadingInterpolation(follower.getPose().getHeading(), Math.toRadians(180))
                .build();
        if (gamepad1.left_stick_button){
            follower.followPath(pathChain1, false);

        } else if (gamepad1.right_stick_button){
            follower.followPath(pathChain2, false);
        } else if (gamepad1.right_stick_button && gamepad1.left_stick_button){
            follower.followPath(pathChain3, false);
        }
    }
    public void start(){
        if (Config.PEDROTELEOP && Config.TELEOPIMU){
            follower.setTeleOpMovementVectors(y, x, spin, true);
            to90degrees();
        } else if (Config.PEDROTELEOP && !Config.TELEOPIMU) {
            follower.setTeleOpMovementVectors(y, x, spin, false);
            to90degrees();
        } else if (!Config.TELEOPIMU){
            driveEasy();
        } else if (Config.TELEOPIMU){
            driveFieldCentric();
        }
        //to90degrees();
    }
    public TeleOpActions teleOpActions = new TeleOpActions() {
        @Override
        public void play() {
            driveEasy();
        }
    };
}



