package org.firstinspires.ftc.teamcode.own.Test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.Actions.CameraAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.CameraMechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
@TeleOp
public class CameraTest extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        mechanism.add(new CameraMechanism());
        actions = new CameraAction();
    }
}
