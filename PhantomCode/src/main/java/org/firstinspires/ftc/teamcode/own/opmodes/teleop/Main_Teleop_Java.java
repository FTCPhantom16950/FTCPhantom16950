package org.firstinspires.ftc.teamcode.own.opmodes.teleop;

import static org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider.sL;
import static org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider.sR;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Podves.podv1;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Podves.podv2;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.ds;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.klesh;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.pod;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrash;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.lbSpeed;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.leftBack;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.leftFront;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.lfSpeed;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.rbSpeed;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.rfSpeed;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.rightBack;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.rightFront;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.LynxModule;
import org.firstinspires.ftc.teamcode.own.Mechanism.Podves;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew;
import org.firstinspires.ftc.teamcode.own.Utils.Color;


@TeleOp

/***
 * Главный телеоп для матчей
 */
public class Main_Teleop_Java extends LinearOpMode {

    Color color = new Color();
    ElapsedTime timer = new ElapsedTime();
    HorizontSlider horizontSlider = new HorizontSlider(this);
    LynxModule lynxModule = new LynxModule(this);
    VerticalSlider verticalSlider  = new VerticalSlider(this);
    Zxnew zx = new Zxnew(this);
    WheelBase wheelBase = new WheelBase(this);
    Podves podves = new Podves(this);

    // создаём поток для управления горизонтальным слайдером
    Thread horSlider = new Thread(() -> {
        while (opModeIsActive()){
            if (horizontSlider.inited){
                horizontSlider.moving();
            }
        }
    });
    // создаем поток для управления вертикальным слайдером
    Thread verticSlider = new Thread(() -> {
        while (opModeIsActive()){
            verticalSlider.run();
        }
    });
    // создаём поток для управления нижним захвватом
    Thread zX = new Thread(() -> {
        while (opModeIsActive()){
            zx.run();
        }
    });
    // создаём поток для управления колесами
    Thread wheelBasethr = new Thread(() -> {
        while (opModeIsActive()){
            wheelBase.start();
        }
    });
    Thread podvThr = new Thread(() -> {
        while (opModeIsActive()){
            podves.run();
        }
    });
    @Override
    public void runOpMode() throws InterruptedException {
        // инициализируем все устройства
        wheelBase.initWheelBase(hardwareMap);
       // lynxModule.init_Lynx();
        horizontSlider.init();
        verticalSlider.init();
        zx.init();
        podves.init();
        timer.reset();
        waitForStart();
        // активируем потоки
        horSlider.setDaemon(true);
        horSlider.start();
        verticSlider.start();
        zX.start();
        wheelBasethr.start();
        podvThr.start();
//        wheelBase.followerthr.start();
        while (opModeIsActive()) {
//            verticalSlider.preSet2();

           // zx.autoKrut();
            // вывод телеметрии
//            telemetry.addData("VerxDS", verticalSlider.verx_color.getDistance(DistanceUnit.MM));
//            telemetry.addData("RED", Zx.colorSensor.getNormalizedColors().red );
//            telemetry.addData("GREEN", Zx.colorSensor.getNormalizedColors().green);
//            telemetry.addData("BLUE", Zx.colorSensor.getNormalizedColors().blue);
//            telemetry.addData("DistanceColor1", Zx.colorSensor.getDistance(DistanceUnit.MM));
//            telemetry.addData("Color",color.color(Zx.colorSensor.getNormalizedColors().red,Zx.colorSensor.getNormalizedColors().green,Zx.colorSensor.getNormalizedColors().blue));
            telemetry.addData("DISTANCE", ds.getDistance(DistanceUnit.MM));
            telemetry.addData("rbspeed", rbSpeed);
            telemetry.addData("rfspeed", rfSpeed);
            telemetry.addData("lbspeed", lbSpeed);
            telemetry.addData("lfspeed", lfSpeed);
            telemetry.addData("rbtick", rightBack.getCurrentPosition());
            telemetry.addData("rftick", rightFront.getCurrentPosition());
            telemetry.addData("lbtick", leftBack.getCurrentPosition());
            telemetry.addData("lftick", leftFront.getCurrentPosition());
            telemetry.addData("rightBack", rightBack.getPower());
            telemetry.addData("leftBack", leftBack.getPower());
            telemetry.addData("rightFront", rightFront.getPower());
            telemetry.addData("leftFront", leftFront.getPower());
            telemetry.addData("rightBackCurr", rightBack.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("leftBackCurr", leftBack.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("rightFrontCurr", rightFront.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("leftFrontCurr", leftFront.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("pod power:", pod.getPower());
            telemetry.addData("kleshna power:", klesh.getPower());
            telemetry.addData("vrash power:", vrash.getPower());
            telemetry.addData("horL power:", sL.getPower());
            telemetry.addData("horR power:", sR.getPower());
//            telemetry.addData("zx pos:", Zx.zx.getPower());
//            telemetry.addData("krut pos:", brat.getPower());
//            telemetry.addData("krut2 pos:", Zx.brat2.getPower());
            telemetry.addData("position", pod.getCurrentPosition());
            telemetry.addData("podves1", podv1.getPower());
            telemetry.addData("podves2", podv2.getPower());
            telemetry.addData("Zxpos", Zxnew.zxpos);
            telemetry.addData("opMode.gamepad2.right_stick_y", gamepad2.right_stick_y);
            telemetry.update();
        }

    }
}
