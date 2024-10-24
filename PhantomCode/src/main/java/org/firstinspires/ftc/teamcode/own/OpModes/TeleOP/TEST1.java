package org.firstinspires.ftc.teamcode.own.OpModes.TeleOP;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Mechanism.LynxModule;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;

@TeleOp
public class TEST1 extends LinearOpMode {
    ElapsedTime time = new ElapsedTime();
    LynxModule lynxModule = new LynxModule();
    WheelBase wheelBase = new WheelBase(this);
    @Override
    public void runOpMode() throws InterruptedException {
        lynxModule.init_Lynx(hardwareMap);
        wheelBase.initWheelBase(hardwareMap);
        //wheelBase.gamepads.start();
        waitForStart();
        time.reset();
        while(opModeIsActive()){
            wheelBase.driveEasy(gamepad1);
        }

    }
}
