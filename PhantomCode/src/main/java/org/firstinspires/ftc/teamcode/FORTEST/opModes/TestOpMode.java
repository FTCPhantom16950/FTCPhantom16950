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

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.Mechanism.ColorSensorClass;
import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.LedControl;
import org.firstinspires.ftc.teamcode.own.Mechanism.LynxModule;
import org.firstinspires.ftc.teamcode.own.Mechanism.Podves;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;
import org.firstinspires.ftc.teamcode.own.Utils.Color;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
@TeleOp
public class TestOpMode extends PhantomOpMode {
    WheelBase wheelBas = new WheelBase(this);
    ColorSensorClass colorSensorClass = new ColorSensorClass(this);
    HorizontSlider horizontSlider = new HorizontSlider(this);
    LynxModule lynxModule = new LynxModule(this);
    Podves podves = new Podves(this);
    LedControl ledControl = new LedControl(this);
    VerticalSlider verticalSlider = new VerticalSlider(this);
    Zx zx = new Zx(this);
    Color color = new Color();
    @Override
    public void afterWaitForStart() {
        playActionOpMode(horizontSlider.teleOpActions, colorSensorClass.teleOpActions, ledControl.teleOpActions, podves.teleOpActions, wheelBas.teleOpActions, verticalSlider.teleOpActions, zx.teleOpActions);
    }

    @Override
    public void initMechanism() {
        wheelBas.initWheelBase(hardwareMap);
        colorSensorClass.init();
        ledControl.init();
        horizontSlider.init();
        lynxModule.init_Lynx();
        podves.init();
        verticalSlider.init();
        zx.init();
    }

    @Override
    public void autoActions() {

    }

    @Override
    public void telemetryDebug() {
        telemetry.addData("VerxDS", VerticalSlider.verx_color.getDistance(DistanceUnit.MM));
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
    }

    @Override
    public void trajectory() {

    }
}
