package org.firstinspires.ftc.teamcode.own.opmodes.test;

import org.firstinspires.ftc.teamcode.own.actions.teleactions.ColorGreenAction;
import org.firstinspires.ftc.teamcode.own.mechanism.InputSensorsMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;

public class InputSensorOpMode extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        mechanism.add(new InputSensorsMechanism());
        actions = new ColorGreenAction();
    }
}
