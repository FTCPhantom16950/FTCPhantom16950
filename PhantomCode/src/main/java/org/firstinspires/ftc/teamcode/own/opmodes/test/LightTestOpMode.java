package org.firstinspires.ftc.teamcode.own.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.actions.teleactions.LightingAction;
import org.firstinspires.ftc.teamcode.own.mechanism.LightingMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;

@TeleOp(group = "test")
public class LightTestOpMode extends PhantomOpMode {

    @Override
    public void customOpModeSettings() {
        mechanism.add(new LightingMechanism());
        actions = new LightingAction();
    }
}
