package org.firstinspires.ftc.teamcode.own.OpModes.TeleOP;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

@TeleOp
public class TEST1 extends LinearOpMode {
    ElapsedTime time = new ElapsedTime();
    Robot robot = new Robot(this);
    WheelBase wheelBase = new WheelBase(this);
    @Override
    public void runOpMode() throws InterruptedException {
        robot.initLynx(hardwareMap);
        wheelBase.initWheelBase(hardwareMap);
        wheelBase.gamepads.start();
        waitForStart();
        time.reset();
        while(opModeIsActive()){
            wheelBase.driveEasy();
        }
        wheelBase.gamepads.stop();
    }
}
