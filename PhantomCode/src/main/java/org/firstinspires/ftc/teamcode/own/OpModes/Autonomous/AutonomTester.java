package org.firstinspires.ftc.teamcode.own.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Camera.PhantomCamera;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;
@Autonomous
public class AutonomTester extends LinearOpMode {
    LinearOpMode opMode = new AutonomTester();
    ElapsedTime timer = new ElapsedTime();
    WheelBase wheelBase = new WheelBase(opMode);
    Robot robot = new Robot(opMode);
    PhantomCamera camera = new PhantomCamera(opMode);
    Robot.Position position = new Robot.Position(0,0,0);
    @Override
    public void runOpMode() throws InterruptedException {
        camera.startCameraEasy(640, 480);
        robot.initAll(hardwareMap);
        timer.reset();
        waitForStart();
        while (opModeIsActive()){
            wheelBase.moveForward(100);
        }
    }
}
