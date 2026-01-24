package org.firstinspires.ftc.teamcode.own.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.actions.TestActions.ShootAction;
import org.firstinspires.ftc.teamcode.own.mechanism.ShooterMechanism;
import org.firstinspires.ftc.teamcode.own.utils.actions.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
@TeleOp
public class ShootTest extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        mechanism.add(new ShooterMechanism());
        actions = new ParallelGroup(
                Robot.INSTANCE.threadPool, this,
                new ShootAction());
    }
}
