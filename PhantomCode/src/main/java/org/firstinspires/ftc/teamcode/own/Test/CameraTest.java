package org.firstinspires.ftc.teamcode.Own.Test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Own.Actions.Utility.CameraAction;
import org.firstinspires.ftc.teamcode.Own.Mechanism.CameraMechanism;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomOpMode;
@TeleOp
public class CameraTest extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        mechanism.add(new CameraMechanism());
        actions = new CameraAction();
    }
}
