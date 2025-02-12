package org.firstinspires.ftc.teamcode.own.Mechanism;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ColorSensorClass {
    com.qualcomm.robotcore.hardware.ColorSensor sensorMRColor;
    LinearOpMode opMode;


    public ColorSensorClass(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public void init(){
        HardwareMap hardwareMap = opMode.hardwareMap;

    }
    public void play(){
    }
}
