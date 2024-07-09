package org.firstinspires.ftc.teamcode.Utils;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Mechanism.WheelBase;

public class Robot {
    WheelBase wheelBase = new WheelBase();
    PhantomIMU phantomIMU = new PhantomIMU();

    public void initAll(HardwareMap hardwareMap){
        wheelBase.initWheelBase(hardwareMap);
        phantomIMU.initIMU(hardwareMap);
    }

    public void teleopMovement(Gamepad gamepad1, Gamepad gamepad2){
        Thread math = new Thread(() -> {

        });
        Thread teleopMovement = new Thread(() -> {
           wheelBase.mecanumDrive.driveRobotCentric(
                1,
                   1,
                   1
           );
        });
    }
}
