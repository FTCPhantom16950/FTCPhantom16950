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
public class ParkovaVesh extends LinearOpMode {
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
            sleep(10000);
//            verticalSlider.pod.setPower(1.0);
//            sleep(300);
//            pod.setPower(0.13);
////            wheelBase.nazadEncoder(1000, 0.3);
//            wheelBase.nazad_timing(2000, 0.3);
//            wheelBase.vpred_Taiming(180, 0.3);
//            sleep(500);
//            pod.setPower(1.0);
//            sleep(450);
//            pod.setPower(0.13);
//            sleep(500);
//            wheelBase.vpred_Taiming(200, 0.3);
//            pod.setPower(-1);
//            sleep(300);
//            pod.setPower(0.13);
//            wheelBase.vpred_Taiming(1800, 0.3);
//            wheelBase.razvarot(1200, 0.3);
            wheelBase.nazad_timing(2500, 0.3);
            sleep(30000);
        }
    }
}
