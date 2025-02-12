package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.own.Mechanism.ColorSensorClass;
@Autonomous
public class Color extends LinearOpMode {
    ModernRoboticsI2cColorSensor sensorMRColor;
    public float hsvValues[] = {0F,0F,0F};
    @Override
    public void runOpMode() throws InterruptedException {
        sensorMRColor = hardwareMap.get(ModernRoboticsI2cColorSensor.class,"mrcolor");
        waitForStart();
        while(opModeIsActive()){
            sensorMRColor.enableLed(true);
        }
    }
}
