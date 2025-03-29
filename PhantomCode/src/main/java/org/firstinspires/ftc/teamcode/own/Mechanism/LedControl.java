package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelImpl;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Objects;

public class LedControl extends Thread {
    LinearOpMode opMode;
    DigitalChannelImpl blue, red, yellow;

    public LedControl(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    HardwareMap map;

    public void init() {
        map = opMode.hardwareMap;
        blue = opMode.hardwareMap.get(DigitalChannelImpl.class, "dg");
        blue.setMode(DigitalChannel.Mode.OUTPUT);
        blue.setState(false);
    }

    public void play() {
        red.setState(!ColorSensorClass.initZX || Objects.equals(ColorSensorClass.colorZX, 1));
        blue.setState(!ColorSensorClass.initZX || Objects.equals(ColorSensorClass.colorZX, 2));
        yellow.setState(!ColorSensorClass.initZX || Objects.equals(ColorSensorClass.colorZX, 3));
    }

    @Override
    public void run() {
        while (!opMode.isStopRequested()) {
            play();
        }
    }
}
