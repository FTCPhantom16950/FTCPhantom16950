package org.firstinspires.ftc.teamcode.own.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.actions.testactions.SpinTestAction;
import org.firstinspires.ftc.teamcode.own.mechanism.SpinMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;

@TeleOp(group = "test")
public class SpinTest extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        mechanism.add(new SpinMechanism());
        actions = new SpinTestAction();
    }
}
