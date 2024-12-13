package org.firstinspires.ftc.teamcode.own.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.LynxModule;
import org.firstinspires.ftc.teamcode.own.Mechanism.Podves;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;
import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.Utils.PHTelemetry;

@TeleOp
public class Main_Teleop_Java extends LinearOpMode {
    ElapsedTime timer = new ElapsedTime();
    HorizontSlider horizontSlider = new HorizontSlider(this);
    LynxModule lynxModule = new LynxModule(this);
    VerticalSlider verticalSlider  = new VerticalSlider(this);
    Zx zx = new Zx(this);
    WheelBase wheelBase = new WheelBase(this);
    Podves podves = new Podves(this);
    @Override
    public void runOpMode() throws InterruptedException {
        wheelBase.initWheelBase(hardwareMap);
        lynxModule.init_Lynx();
        horizontSlider.init();
        verticalSlider.init();
        zx.init();
        podves.init();
        timer.reset();
        waitForStart();
        while (opModeIsActive()) {
            wheelBase.start();
            horizontSlider.autoMoving();
            horizontSlider.manualMoving();
//            verticalSlider.preSet2();
            verticalSlider.run();
            zx.run();
            horizontSlider.run_wiithout();
            podves.run();
           // zx.autoKrut();
            telemetry.addData("rbspeed", wheelBase.rbSpeed);
            telemetry.addData("rfspeed", wheelBase.rfSpeed);
            telemetry.addData("lbspeed", wheelBase.lbSpeed);
            telemetry.addData("lfspeed", wheelBase.lfSpeed);
            telemetry.addData("rbtick", wheelBase.rightBack.getCurrentPosition());
            telemetry.addData("rftick", wheelBase.rightFront.getCurrentPosition());
            telemetry.addData("lbtick", wheelBase.leftBack.getCurrentPosition());
            telemetry.addData("lftick", wheelBase.leftFront.getCurrentPosition());
            telemetry.addData("rightBack", wheelBase.rightBack.getPower());
            telemetry.addData("leftBack", wheelBase.leftBack.getPower());
            telemetry.addData("rightFront", wheelBase.rightFront.getPower());
            telemetry.addData("leftFront", wheelBase.leftFront.getPower());
            telemetry.addData("rightBackCurr", wheelBase.rightBack.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("leftBackCurr", wheelBase.leftBack.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("rightFrontCurr", wheelBase.rightFront.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("leftFrontCurr", wheelBase.leftFront.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("pod power:", verticalSlider.RunPowers[0]);
            telemetry.addData("kleshna power:", verticalSlider.RunPowers[1]);
            telemetry.addData("vrash power:", verticalSlider.RunPowers[2]);
            telemetry.addData("horL power:", horizontSlider.sL.getPower());
            telemetry.addData("horR power:", horizontSlider.sR.getPower());
            telemetry.addData("zx pos:", zx.zx.getPower());
            telemetry.addData("krut pos:", zx.krut.getPower());
            telemetry.addData("position", verticalSlider.pod.getCurrentPosition());
            telemetry.addData("podves1", podves.podv1.getPower());
            telemetry.addData("podves2", podves.podv2.getPower());
            telemetry.update();
        }

    }
}
