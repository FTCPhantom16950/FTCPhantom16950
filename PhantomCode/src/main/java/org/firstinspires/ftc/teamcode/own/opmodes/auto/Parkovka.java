package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import static org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider.sL;
import static org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider.sR;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.pod;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrashPower;

import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krut;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;
@Autonomous


public class Parkovka extends LinearOpMode {
    ElapsedTime timer = new ElapsedTime();
    WheelBase wheelBase = new WheelBase(this);
    HorizontSlider horizontSlider = new HorizontSlider(this);
    VerticalSlider verticalSlider = new VerticalSlider(this);
    Zx zx = new Zx(this);
    @Override
    public void runOpMode() throws InterruptedException {
        wheelBase.initWheelBase(hardwareMap);
        verticalSlider.init();
        horizontSlider.init();
        zx.init();
        waitForStart();
        timer.reset();
        if (opModeIsActive()){
            pod.setPower(1.0);
            sleep(300);
            pod.setPower(0.13);
            wheelBase.nazadEncoder(10000, 0.2);
            wheelBase.vperedEncoder(300, 0.2);
            sleep(500);
            pod.setPower(0.7);
            sleep(700);
            pod.setPower(0.13);
            sleep(500);
            wheelBase.vperedEncoder(200, 0.2);
            pod.setPower(-1);
            sleep(300);
            pod.setPower(0.13);
            wheelBase.vperedEncoder(500, 0.3);
            wheelBase.razvarotEncoder(4500, -0.3);
            wheelBase.vperedEncoder(3000, 0.3);
            wheelBase.razvarotEncoder(500, -0.3);
            horizontSlider.i = 0.45;
            sL.setPower(horizontSlider.i);
            sR.setPower(-horizontSlider.i - 0.05);
            sleep(500);
            zx.not = true;
            zx.zx.setPower(0.5);
            zx.g = 1;
            sleep(1000);
            zx.not = false;
            zx.g = -0.57;
            horizontSlider.i = 0;
            sleep(500);
            zx.not = true;
            zx.zx.setPower(0.5);
            zx.g = 0;
            zx.not = false;
            sleep(200);
            wheelBase.vperedEncoder(10000, 0.3);
            wheelBase.razvarotEncoder(2250, 0.3);
            wheelBase.nazadEncoder(2000,0.3);
            pod.setPower(-1);
            sleep(200);
            pod.setPower(0.13);
            verticalSlider.kleshPower = -0.3;
            sleep(200);
            vrashPower = -0.94;
            sleep(200);
            verticalSlider.kleshPower = 0;
            pod.setPower(1.0);
            sleep(800);
            pod.setPower(0.13);
            vrashPower = 1;
            sleep(500);
            verticalSlider.kleshPower = -0.3;
            sleep(200);
            vrashPower = -0.94;
            pod.setPower(-1);
            sleep(300);
            pod.setPower(0.13);
            wheelBase.razvarotEncoder(3500 - 2250, 0.3);
            wheelBase.nazadEncoder(3000, 0.3);
            wheelBase.razvarotEncoder(1500, 0.3);
            wheelBase.nazadEncoder(40000, 0.3);
            wheelBase.razvarotEncoder(1500, 0.3);
            wheelBase.nazadEncoder(3000, 0.3);
//            wheelBase.vperedEncoder(7900, 0.2);
 //         wheelBase.razvarotEncoder(1500, -0.3);
//            wheelBase.vperedEncoder(18000, 0.3);
            sleep(30000);
        }
    }
}
