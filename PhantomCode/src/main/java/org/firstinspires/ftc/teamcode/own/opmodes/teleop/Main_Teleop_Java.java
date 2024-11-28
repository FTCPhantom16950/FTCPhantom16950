package org.firstinspires.ftc.teamcode.own.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.LynxModule;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;
import org.firstinspires.ftc.teamcode.own.Utils.Config;

@TeleOp
public class Main_Teleop_Java extends LinearOpMode {
    Config config = new Config();
    ElapsedTime timer = new ElapsedTime();
    HorizontSlider horizontSlider = new HorizontSlider(this);
    LynxModule lynxModule = new LynxModule(this);
    VerticalSlider verticalSlider  = new VerticalSlider(this);
    Zx zx = new Zx(this);
    WheelBase wheelBase = new WheelBase(this);
    @Override
    public void runOpMode() throws InterruptedException {
        wheelBase.initWheelBase(hardwareMap);
        lynxModule.init_Lynx();
        horizontSlider.init();
        verticalSlider.init();
        zx.init();
        timer.reset();
        waitForStart();
        while (opModeIsActive()) {
            wheelBase.driveEasy();
            horizontSlider.run();
            verticalSlider.run();
            zx.run();
        }
        if (!opModeIsActive()){
            horizontSlider.stop();
            verticalSlider.stop();
            zx.stop();
        }
    }
}
