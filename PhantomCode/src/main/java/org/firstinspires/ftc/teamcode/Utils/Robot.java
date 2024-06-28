package org.firstinspires.ftc.teamcode.Utils;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Mechanism.WheelBase;

public class Robot {

    double lTrigger;
    double rTrigger;
    WheelBase wheelBase = new WheelBase();
    PhantomIMU phantomIMU = new PhantomIMU();
    MecanumDrive wheels;
    GamepadEx gamepad1Ex, gamepad2Ex;
    public void initAll(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2){
        gamepad1Ex = new GamepadEx(gamepad1);
        gamepad2Ex = new GamepadEx(gamepad2);
        wheelBase.initWheelBase(hardwareMap);
        phantomIMU.initIMU(hardwareMap);
    }
    public void TeleOpMovement(){
        double frontSpeed = gamepad1Ex.getLeftX() + gamepad1Ex.getRightX() * 0.4;
        double strafeSpeed = gamepad1Ex.getLeftY() + gamepad1Ex.getRightY() * 0.4;
        Thread triggers = new Thread(() -> {
            if (gamepad1Ex.getButton(GamepadKeys.Button.LEFT_BUMPER)){
                 lTrigger = 0.4;
            }
            if (gamepad1Ex.getButton(GamepadKeys.Button.RIGHT_BUMPER)){
                rTrigger = 0.4;
            }
        });
        triggers.start();
        double turnSpeed = (gamepad1Ex.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) - gamepad1Ex.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)) +(lTrigger - rTrigger);
        wheels.driveRobotCentric(
                strafeSpeed,
                frontSpeed,
                turnSpeed
        );
    }
}
