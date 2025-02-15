package org.firstinspires.ftc.teamcode.own.opmodes.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.Mechanism.ColorSensorClass;
import org.firstinspires.ftc.teamcode.own.Mechanism.LedControl;
import org.firstinspires.ftc.teamcode.own.Utils.Color;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.Utils.TeleOpActions;

@Config
@TeleOp
public class ColorSensorOpMode extends PhantomOpMode {
    public static double gain = 100;
    ColorSensorClass colorSensorClass = new ColorSensorClass(this);
    LedControl ledControl = new LedControl(this);

    @Override
    public void afterWaitForStart() {
        playActionOpMode(ledControl.teleOpActions, colorSensorClass.teleOpActions);
    }

    @Override
    public void initMechanism() {
        colorSensorClass.init();
        ledControl.init();
        ColorSensorClass.color_zx.setGain((float) gain);
    }

    @Override
    public void play() {
        sleep(30000000);
    }

    @Override
    public void autoActions() {

    }

    @Override
    public void telemetryDebug() {
        telemetry.addData("status", ColorSensorClass.initZX);
        telemetry.addData("red", ColorSensorClass.color_zx.getNormalizedColors().red);
        telemetry.addData("green", ColorSensorClass.color_zx.getNormalizedColors().green);
        telemetry.addData("blue", ColorSensorClass.color_zx.getNormalizedColors().blue);
        telemetry.addData("color", ColorSensorClass.colorZX);
    }

    @Override
    public void trajectory() {
        //
    }
}
