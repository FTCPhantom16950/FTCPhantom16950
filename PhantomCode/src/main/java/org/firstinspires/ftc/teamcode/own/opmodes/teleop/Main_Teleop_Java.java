package org.firstinspires.ftc.teamcode.own.opmodes.teleop;

import static org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider.sL;
import static org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider.sR;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Podves.podv1;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Podves.podv2;
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
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krut;

import android.graphics.Bitmap;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.LynxModule;
import org.firstinspires.ftc.teamcode.own.Mechanism.Podves;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;
import org.firstinspires.ftc.teamcode.own.Utils.Color;
import org.firstinspires.ftc.teamcode.own.Utils.PHTelemetry;


@TeleOp
@Config
public class Main_Teleop_Java extends LinearOpMode {
    public static double gain = 103;
    Color color = new Color();
    ElapsedTime timer = new ElapsedTime();
    HorizontSlider horizontSlider = new HorizontSlider(this);
    LynxModule lynxModule = new LynxModule(this);
    VerticalSlider verticalSlider  = new VerticalSlider(this);
    Zx zx = new Zx(this);
    WheelBase wheelBase = new WheelBase(this);
    Podves podves = new Podves(this);
    ColorRangeSensor colorSensor;
    Thread horSlider = new Thread(() -> {
        while (opModeIsActive()){
            horizontSlider.autoMoving();
            horizontSlider.manualMoving();

        }
    });
    Thread horSlider1 = new Thread(() -> {
        while (opModeIsActive()){
            horizontSlider.run_wiithout();
        }
    });
    Thread verticSlider = new Thread(() -> {

        while (opModeIsActive()){
            verticalSlider.run();
        }
    });
    Thread zX = new Thread(() -> {
        while (opModeIsActive()){
            zx.run();
        }
    });
    Thread wheelBasethr = new Thread(() -> {
        while (opModeIsActive()){
            wheelBase.start();
        }
    });
    @Override
    public void runOpMode() throws InterruptedException {
        colorSensor = hardwareMap.get(ColorRangeSensor.class, "color");
        wheelBase.initWheelBase(hardwareMap);
       // lynxModule.init_Lynx();
        horizontSlider.init();
        verticalSlider.init();
        zx.init();
        podves.init();
        timer.reset();
        waitForStart();
        horSlider.setDaemon(true);
        horSlider.start();
        verticSlider.start();
        horSlider1.start();
        zX.start();
        wheelBasethr.start();
        while (opModeIsActive()) {
            colorSensor.setGain((float)gain);

//            verticalSlider.preSet2();
            podves.run();
           // zx.autoKrut();
            colorSensor.enableLed(false);
            telemetry.addData("gain", gain );
            telemetry.addData("RED", colorSensor.getNormalizedColors().red );
            telemetry.addData("GREEN", colorSensor.getNormalizedColors().green);
            telemetry.addData("BLUE", colorSensor.getNormalizedColors().blue);
            telemetry.addData("Color",color.color(colorSensor.getNormalizedColors().red,colorSensor.getNormalizedColors().green,colorSensor.getNormalizedColors().blue));
            telemetry.addData("DISTANCE", colorSensor.getDistance(DistanceUnit.CM));
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
            telemetry.addData("zx pos:", Zx.zx.getPower());
            telemetry.addData("krut pos:", krut.getPower());
            telemetry.addData("krut2 pos:", Zx.krut2.getPower());
            telemetry.addData("position", pod.getCurrentPosition());
            telemetry.addData("podves1", podv1.getPower());
            telemetry.addData("podves2", podv2.getPower());
            telemetry.update();
        }

    }
}
