package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.pod;

import android.content.pm.LabeledIntent;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;
@Disabled

@Autonomous
public class VeshEncoder extends LinearOpMode {
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
        while (opModeIsActive()){
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
            wheelBase.vperedEncoder(7900, 0.2);
            wheelBase.razvarotEncoder(1500, -0.3);
            wheelBase.vperedEncoder(18000, 0.3);
            sleep(30000);
        }
    }}
