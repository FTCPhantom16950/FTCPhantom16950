package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Camera.PhantomCamera;
import org.firstinspires.ftc.teamcode.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.Utils.PhantomIMU;
import org.firstinspires.ftc.teamcode.Utils.Robot;

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
            wheelBase.moveForward(position.metersToRotations(100, 0)[0]);

        }
    }
}
