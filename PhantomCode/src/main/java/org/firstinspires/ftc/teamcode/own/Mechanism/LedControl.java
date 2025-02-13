package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.DigitalChannelImpl;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Objects;

public class LedControl {
    LinearOpMode opMode;
    DigitalChannelImpl blue , red, yellow;
    public LedControl(LinearOpMode opMode) {
        this.opMode = opMode;
    }
    HardwareMap map;
    public void init(){
        blue = map.get(DigitalChannelImpl.class,"dg");
        blue.setMode(DigitalChannel.Mode.OUTPUT);
        blue.setState(false);
    }
    public void play(){
        if(ColorSensorClass.initZX && Objects.equals(ColorSensorClass.colorZX, "BLUE")){
            blue.setState(true);
        }else if (!ColorSensorClass.initZX) {
            blue.setState(true);
        }
        if (ColorSensorClass.initZX && Objects.equals(ColorSensorClass.colorZX, "RED")) {

        } else if (!ColorSensorClass.initZX) {
//            red.setState(true);
        }
        if (ColorSensorClass.initZX && Objects.equals(ColorSensorClass.colorZX, "YELLOW")) {

        }else if (!ColorSensorClass.initZX) {
//            yellow.setState(true);
        }
    }
}
