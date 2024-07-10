package org.firstinspires.ftc.teamcode.Utils;

import android.content.Context;
import android.widget.Toast;


import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Mechanism.WheelBase;

import java.util.List;

public class Robot {
    WheelBase wheelBase = new WheelBase();
    PhantomIMU phantomIMU = new PhantomIMU();
    Telemetry telemetry;
    Context context = new FtcRobotControllerActivity();
    double strafeSpeed, forwardSpeed, turnSpeed;
    public List<LynxModule> allhubs;

    public void initLynx(HardwareMap hardwareMap){
        allhubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allhubs){
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
    }
    public void initAll(HardwareMap hardwareMap){
        wheelBase.initWheelBase(hardwareMap);
        phantomIMU.initIMU(hardwareMap);
    }

    public void teleopMovement(Gamepad gamepad1, Gamepad gamepad2){
        Thread math = new Thread(() ->{
            while (true){
                if (gamepad1.right_bumper) {
                    rBumper = 0.4;
                }
                if (gamepad1.left_bumper){
                    lBumper = 0.4;
                }
                forwardSpeed = gamepad1.left_stick_y + gamepad1.right_stick_y * 0.4;
                strafeSpeed = gamepad1.left_stick_x + gamepad1.right_stick_x * 0.4;
                turnSpeed = (gamepad1.right_trigger - gamepad1.left_trigger) + (rBumper - lBumper);
            }
        });
        Thread teleopMovement = new Thread(() -> {
           wheelBase.mecanumDrive.driveRobotCentric(
                strafeSpeed,
                   forwardSpeed,
                   turnSpeed
           );
        });
        math.start();
        teleopMovement.start();
    }
    public class Position{
        public double x, y, heading;
        public Position(double x, double y, double heading) {
            this.x = x;
            this.y = y;
            this.heading = heading;
        }

    }
}
