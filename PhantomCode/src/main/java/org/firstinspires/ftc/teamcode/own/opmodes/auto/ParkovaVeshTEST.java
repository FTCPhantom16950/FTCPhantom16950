package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.pod;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;

@Autonomous
public class ParkovaVeshTEST extends LinearOpMode {
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
            timer.reset();
            verticalSlider.pod.setPower(1.0);
            sleep(300);
            pod.setPower(0.13);
            wheelBase.nazadEncoder(8100, 0.3);
            sleep(500);
            pod.setPower(0.7);
            sleep(800);
            pod.setPower(0.13);
            sleep(500);
            wheelBase.vperedEncoder(500, 0.3);
            pod.setPower(-0.7);
            sleep(500);
            pod.setPower(0.13);
            // sleep(10000);
            wheelBase.vperedEncoder(7600, 0.3);
            sleep(200);
            wheelBase.razvarotEncoder(1550, 0.3);
            sleep(200);
            wheelBase.nazadEncoder(20000, 0.4);

            sleep(30000);
        }
    }
}
