package org.firstinspires.ftc.teamcode.own.opmodes.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.Utils.Color;

@Config
@TeleOp
public class ColorSensorOpMode extends LinearOpMode {
    public static double gain = 100;
    @Override
    public void runOpMode() throws InterruptedException {
        Color color = new Color();
        RevColorSensorV3 revColorSensorV3 = hardwareMap.get(RevColorSensorV3.class, "color_verx");
        revColorSensorV3.setGain((float) gain);
        waitForStart();
        while(opModeIsActive()){
            revColorSensorV3.enableLed(false);
            telemetry.addData("red", revColorSensorV3.getNormalizedColors().red);
            telemetry.addData("green", revColorSensorV3.getNormalizedColors().green);
            telemetry.addData("blue", revColorSensorV3.getNormalizedColors().blue);
            telemetry.addData("color", color.color(revColorSensorV3.getNormalizedColors().red,revColorSensorV3.getNormalizedColors().green,revColorSensorV3.getNormalizedColors().blue));
            telemetry.update();

        }
    }
}
