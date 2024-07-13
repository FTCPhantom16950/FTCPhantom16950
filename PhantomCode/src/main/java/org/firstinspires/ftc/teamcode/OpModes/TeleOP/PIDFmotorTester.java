package org.firstinspires.ftc.teamcode.OpModes.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.Utils.Robot;

public class PIDFmotorTester extends LinearOpMode {
    WheelBase wheelBase = new WheelBase(new PIDFmotorTester());


    Robot robot = new Robot(new PIDFmotorTester());
    ElapsedTime time = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        wheelBase.initWheelBase(hardwareMap);
        robot.initAll(hardwareMap);
        waitForStart();
        time.reset();
        while (opModeIsActive()){
            wheelBase.driveFieldCentic();
        }
    }
}
