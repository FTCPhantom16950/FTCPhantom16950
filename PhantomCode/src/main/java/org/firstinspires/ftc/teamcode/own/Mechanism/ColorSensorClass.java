package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.Color;
import org.firstinspires.ftc.teamcode.own.Utils.TeleOpActions;

public class ColorSensorClass {
    LinearOpMode opMode;
    Color color  = new Color();
    public static RevColorSensorV3 color_zx;
    public static String colorZX;
    public static boolean initZX = false;
    public ColorSensorClass(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public void init(){
        HardwareMap hardwareMap = opMode.hardwareMap;
        color_zx = hardwareMap.get(RevColorSensorV3.class,"color");
    }

    public void play(){
        if (!(color_zx == null)){
            initZX = true;
            colorZX = color.color(color_zx.getNormalizedColors().red,color_zx.getNormalizedColors().green,color_zx.getNormalizedColors().blue);
        }
        else {
            initZX = false;
        }
    }
    public TeleOpActions teleOpActions = new TeleOpActions() {
        @Override
        public void play() {
            ColorSensorClass.this.play();
        }
    };
}
