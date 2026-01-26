package org.firstinspires.ftc.teamcode.own.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.actions.testactions.ShootTestAction;
import org.firstinspires.ftc.teamcode.own.mechanism.ShooterMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;

@TeleOp(group = "test")
public class ShootTest extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        mechanism.add(new ShooterMechanism());
        actions = new ShootTestAction();
    }
}
