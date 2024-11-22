package org.firstinspires.ftc.teamcode.FORTEST.opModes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.LynxModule;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;


@TeleOp(name = "A_TELEOP_BASED")
public class TEST1 extends LinearOpMode {
    ElapsedTime time = new ElapsedTime();
    LynxModule lynxModule = new LynxModule();
    WheelBase wheelBase = new WheelBase(this);
    HorizontSlider horizontSlider = new HorizontSlider(this);

    @Override
    public void runOpMode() throws InterruptedException {
        wheelBase.initWheelBase(hardwareMap);
        horizontSlider.init();
        //wheelBase.gamepads.start();
        waitForStart();
        time.reset();
        while(opModeIsActive()){
            wheelBase.driveEasy(gamepad1);
            horizontSlider.run();
        }

    }
}
