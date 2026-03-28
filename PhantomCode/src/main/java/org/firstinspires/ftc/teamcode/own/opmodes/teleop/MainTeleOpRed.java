package org.firstinspires.ftc.teamcode.own.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.actions.auto.AutoLaunch;
import org.firstinspires.ftc.teamcode.own.actions.gamepadaction.CaptureGamepad;
import org.firstinspires.ftc.teamcode.own.actions.gamepadaction.LaunchGamepad;
import org.firstinspires.ftc.teamcode.own.actions.gamepadaction.RotateLongAction;
import org.firstinspires.ftc.teamcode.own.actions.gamepadaction.StandingGamepad;
import org.firstinspires.ftc.teamcode.own.actions.gamepadaction.WheelBaseAction;
import org.firstinspires.ftc.teamcode.own.actions.stateaction.AngleStateSwap;
import org.firstinspires.ftc.teamcode.own.actions.stateaction.CaptureStateSwap;
import org.firstinspires.ftc.teamcode.own.actions.stateaction.LaunchStateSwap;
import org.firstinspires.ftc.teamcode.own.actions.stateaction.RevolverStateSwap;
import org.firstinspires.ftc.teamcode.own.actions.stateaction.RotateStateSwap;
import org.firstinspires.ftc.teamcode.own.actions.stateaction.UpperStateSwap;
import org.firstinspires.ftc.teamcode.own.mechanism.CaptureMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.ColorSensorsMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.ImuMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.LaunchMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.LimeLightMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.StandingMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.WheelBaseMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.ParallelGroup;

@TeleOp
public class MainTeleOpRed extends PhantomOpMode {
    @Override
    public void customOpModeSettings() throws InterruptedException {
        Robot.INSTANCE.addMechanism(new CaptureMechanism());
        Robot.INSTANCE.addMechanism(new LaunchMechanism());
        Robot.INSTANCE.addMechanism(new StandingMechanism());
        Robot.INSTANCE.addMechanism(new WheelBaseMechanism());
        Robot.INSTANCE.addMechanism(new ImuMechanism());
        Robot.INSTANCE.addMechanism(new ColorSensorsMechanism());
        Robot.INSTANCE.addMechanism(new LimeLightMechanism());
        Robot.INSTANCE.setStartAction(new ParallelGroup(
                new StandingGamepad(),
                new RotateLongAction(true),
                new CaptureGamepad(),
                new RevolverStateSwap(),
                new CaptureStateSwap(),
                new LaunchGamepad(),
                new LaunchStateSwap(),
                new WheelBaseAction(),
                new UpperStateSwap(),
                new RotateStateSwap(),
                new AngleStateSwap(),
                new AutoLaunch()
                ));
    }
}
