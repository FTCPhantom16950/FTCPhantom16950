package org.firstinspires.ftc.teamcode.FORTEST.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Camera.PhantomCamera;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
@Autonomous
public class AutonomTester extends LinearOpMode {
    LinearOpMode opMode = new AutonomTester();
    ElapsedTime timer = new ElapsedTime();
    WheelBase wheelBase = new WheelBase(this);

    PhantomCamera camera = new PhantomCamera(opMode);
    @Override
    public void runOpMode() throws InterruptedException {
        camera.startCameraEasy(640, 480);
        timer.reset();
        waitForStart();
        while (opModeIsActive()){
            wheelBase.moveForward(100);
        }
    }
}
