package org.firstinspires.ftc.teamcode.own.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.actions.gamepadaction.CaptureGamepad;
import org.firstinspires.ftc.teamcode.own.actions.stateaction.CaptureStateSwap;
import org.firstinspires.ftc.teamcode.own.mechanism.CaptureMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.LaunchMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.StandingMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.WheelBaseMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.ParallelGroup;

@TeleOp
public class MainTeleOp extends PhantomOpMode {
    @Override
    public void customOpModeSettings() throws InterruptedException {
        Robot.INSTANCE.addMechanism(new CaptureMechanism());
        Robot.INSTANCE.addMechanism(new LaunchMechanism());
        Robot.INSTANCE.addMechanism(new StandingMechanism());
        Robot.INSTANCE.addMechanism(new WheelBaseMechanism());
        Robot.INSTANCE.setStartAction(new ParallelGroup(
                new CaptureGamepad(),
                new CaptureStateSwap()
        ));
    }
}
