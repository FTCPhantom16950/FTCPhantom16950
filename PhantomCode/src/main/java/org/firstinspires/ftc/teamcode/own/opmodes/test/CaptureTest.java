package org.firstinspires.ftc.teamcode.own.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.actions.testactions.CaptureTestAction;
import org.firstinspires.ftc.teamcode.own.mechanism.CaptureMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
@TeleOp(group = "test")
public class CaptureTest extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        mechanism.add(new CaptureMechanism());
        actions = new CaptureTestAction();
    }
}
