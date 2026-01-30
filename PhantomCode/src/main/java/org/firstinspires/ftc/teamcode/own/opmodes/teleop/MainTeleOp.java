package org.firstinspires.ftc.teamcode.own.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.actions.teleactions.LightingAction;
import org.firstinspires.ftc.teamcode.own.actions.testactions.CaptureTestAction;
import org.firstinspires.ftc.teamcode.own.actions.testactions.ReznaTestAction;
import org.firstinspires.ftc.teamcode.own.actions.testactions.ShootTestAction;
import org.firstinspires.ftc.teamcode.own.actions.testactions.SpinTestAction;
import org.firstinspires.ftc.teamcode.own.actions.testactions.WheelBaseTestAction;
import org.firstinspires.ftc.teamcode.own.mechanism.CaptureMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.InputSensorsMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.LightingMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.LimelightMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.ReznaMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.ShooterMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.SpinMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.WheelBaseMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.ParallelGroup;

@TeleOp
public class MainTeleOp extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        mechanism.add(new ReznaMechanism());
        mechanism.add(new CaptureMechanism());
        mechanism.add(new ShooterMechanism());
        mechanism.add(new WheelBaseMechanism());
        mechanism.add(new SpinMechanism());
        mechanism.add(new LightingMechanism());
        mechanism.add(new LimelightMechanism());
        mechanism.add(new InputSensorsMechanism());
        actions = new ParallelGroup(
                Robot.INSTANCE.threadPool, this,
                new SpinTestAction(),
                new ShootTestAction(),
                new WheelBaseTestAction(),
                new ReznaTestAction(),
                new CaptureTestAction(),
                new LightingAction()
        );
    }
}
