package org.firstinspires.ftc.teamcode.own.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.actions.test.ColorTestAction;
import org.firstinspires.ftc.teamcode.own.actions.util.CameraAction;
import org.firstinspires.ftc.teamcode.own.mechanism.CameraMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.ColorSensorsMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
@TeleOp(group = "test")
public class ColorTestMode extends PhantomOpMode {
    @Override
    public void customOpModeSettings() throws InterruptedException {
        Robot.INSTANCE.addMechanism(new ColorSensorsMechanism());
        Robot.INSTANCE.addMechanism(new CameraMechanism());
        Robot.INSTANCE.setStartAction(new CameraAction());
    }
}
