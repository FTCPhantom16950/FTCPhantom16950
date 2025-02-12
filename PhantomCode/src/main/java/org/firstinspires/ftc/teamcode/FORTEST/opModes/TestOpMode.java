package org.firstinspires.ftc.teamcode.FORTEST.opModes;

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
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.brat;

import android.provider.SyncStateContract;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.util.Constants;
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
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;
import org.firstinspires.ftc.teamcode.own.Utils.Color;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;


@TeleOp
@Config
public class TestOpMode extends LinearOpMode {
    public final Pose startPose = new Pose(135.35, 34.19, Math.toRadians(90));
    public double y;
    public double spin;
    public double x;
    double rbump = 0;
    double lbump = 0;
    Color color = new Color();
    ElapsedTime timer = new ElapsedTime();
    HorizontSlider horizontSlider = new HorizontSlider(this);
    LynxModule lynxModule = new LynxModule(this);
    VerticalSlider verticalSlider  = new VerticalSlider(this);
    Zx zx = new Zx(this);
    WheelBase wheelBase = new WheelBase(this);
    Podves podves = new Podves(this);
    Follower follower;

    Thread horSlider = new Thread(() -> {
        while (opModeIsActive()){
            if (horizontSlider.inited){
                horizontSlider.moving();
            }
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
            x = (gamepad1.left_stick_x) + (gamepad1.right_stick_x) * 0.7;
            y = -(gamepad1.left_stick_y) + (gamepad1.right_stick_y) * 0.7;
            spin = (gamepad1.right_trigger) + (-gamepad1.left_trigger) + rbump - lbump;
            follower.setTeleOpMovementVectors(y, x, spin);
        }
    });
    @Override
    public void runOpMode() throws InterruptedException {
        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(this.hardwareMap);
        follower.setStartingPose(startPose);
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
        zX.start();
        wheelBasethr.start();
        follower.startTeleopDrive();
        while (opModeIsActive()) {

            follower.update();
//            verticalSlider.preSet2();
//            podves.run();
           // zx.autoKrut();
            telemetry.addData("VerxDS", verticalSlider.verx_color.getDistance(DistanceUnit.MM));
            telemetry.addData("RED", Zx.colorSensor.getNormalizedColors().red );
            telemetry.addData("GREEN", Zx.colorSensor.getNormalizedColors().green);
            telemetry.addData("BLUE", Zx.colorSensor.getNormalizedColors().blue);
            telemetry.addData("DistanceColor1", Zx.colorSensor.getDistance(DistanceUnit.MM));
            telemetry.addData("Color",color.color(Zx.colorSensor.getNormalizedColors().red,Zx.colorSensor.getNormalizedColors().green,Zx.colorSensor.getNormalizedColors().blue));
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
            telemetry.addData("zx pos:", Zx.zx.getPower());
            telemetry.addData("krut pos:", brat.getPower());
            telemetry.addData("krut2 pos:", Zx.brat2.getPower());
            telemetry.addData("position", pod.getCurrentPosition());
            telemetry.addData("podves1", podv1.getPower());
            telemetry.addData("podves2", podv2.getPower());
            telemetry.update();
        }

    }
}
