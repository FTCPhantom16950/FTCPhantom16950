package org.firstinspires.ftc.teamcode.Own.OpModes.Test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Own.Actions.ShootAction;
import org.firstinspires.ftc.teamcode.Own.Mechanism.ShooterMechanism;
import org.firstinspires.ftc.teamcode.Own.Utils.Action.Groups.LinearGroup;
import org.firstinspires.ftc.teamcode.Own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.Own.Utils.Robot;
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
