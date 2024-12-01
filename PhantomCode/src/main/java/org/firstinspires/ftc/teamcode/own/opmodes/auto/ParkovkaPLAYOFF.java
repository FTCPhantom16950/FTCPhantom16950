package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;

@Autonomous
public class ParkovkaPLAYOFF extends LinearOpMode {
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
            wheelBase.nazad_timing(300, 0.5);
            sleep(500);
            verticalSlider.pod.setPower(1);
            sleep(2500);
            verticalSlider.pod.setPower(0.01);
            verticalSlider.vrash.setPower(1);
            sleep(1000);
            verticalSlider.klesh.setPower(-0.3);
            sleep(1000);
            verticalSlider.vrash.setPower(-0.5);
            sleep(100);
            verticalSlider.klesh.setPower(0);
            verticalSlider.pod.setPower(-1);
            sleep(2000);
            verticalSlider.pod.setPower(0.1);
            sleep(200);
            wheelBase.vpred_Taiming(5000, 0.3);

            sleep(30000);
        }
    }
}
