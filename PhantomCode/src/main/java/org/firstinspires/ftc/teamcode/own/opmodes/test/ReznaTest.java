package org.firstinspires.ftc.teamcode.own.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.actions.testactions.ReznaTestAction;
import org.firstinspires.ftc.teamcode.own.mechanism.ReznaMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;

@TeleOp(group = "test")
public class ReznaTest extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        mechanism.add(new ReznaMechanism());
        actions = new ReznaTestAction();
    }
}
