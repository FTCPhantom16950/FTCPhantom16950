package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;

@Autonomous
public class ParkovkaOBEZD extends LinearOpMode {
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
            horizontSlider.sL.setPower(0.4);
            horizontSlider.sR.setPower(-0.4);
            sleep(1000);
            zx.krut.setPower(-0.95);
            sleep(500);
            zx.zx.setPower(0.3);
            sleep(1000);
            zx.zx.setPower(0);
            zx.krut.setPower(0);
            horizontSlider.sL.setPower(0);
            horizontSlider.sR.setPower(-0);
            sleep(1000);
            verticalSlider.pod.setPower(0.5);
            zx.krut.setPower(0.3);
            sleep(2000);
            verticalSlider.pod.setPower(0.01);
            wheelBase.razvarot(1200, -0.3);
            sleep(200);
            wheelBase.nazad_timing(500, 0.5);
            sleep(200);
            wheelBase.razvarot(1200, 0.3);
            sleep(200);
            wheelBase.nazad_timing(3600, 0.4);
            sleep(200);
            wheelBase.razvarot(1200, 0.3);
            sleep(200);
            wheelBase.nazad_timing(400, 0.5);
            sleep(30000);
        }
    }
}
