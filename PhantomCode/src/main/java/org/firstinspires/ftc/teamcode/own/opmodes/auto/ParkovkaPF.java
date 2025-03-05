package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.FORTEST.Zx;
@Disabled

@Autonomous
public class ParkovkaPF extends LinearOpMode {
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
            wheelBase.nazadEncoder(500, 0.3);
            sleep(500);

            wheelBase.vperedEncoder(500, 0.3);
            wheelBase.razvarotEncoder(-1200, -0.3);
            wheelBase.vperedEncoder(500, 0.3);
            horizontSlider.vidvig();
            horizontSlider.zaxvat();
            wheelBase.nazadEncoder(500, 0.3);
            wheelBase.razvarotEncoder(1200, 0.3);
            wheelBase.nazadEncoder(500, 0.3);
            verticalSlider.kleshPower = -0.3;
            verticalSlider.vrash.setPower(-0.93);

            wheelBase.vperedEncoder(45000, 0.3);
            sleep(30000);
        }
    }
}
