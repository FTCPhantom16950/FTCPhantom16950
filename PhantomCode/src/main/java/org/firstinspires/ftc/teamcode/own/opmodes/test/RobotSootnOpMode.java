package org.firstinspires.ftc.teamcode.own.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.actions.gamepadaction.RotateLongAction;
import org.firstinspires.ftc.teamcode.own.actions.util.AprilTagAction;
import org.firstinspires.ftc.teamcode.own.actions.util.IMUTest;
import org.firstinspires.ftc.teamcode.own.actions.util.TicksToDegreeAction;
import org.firstinspires.ftc.teamcode.own.mechanism.ImuMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.LaunchMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.LimeLightMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;

@TeleOp(group = "test")
public class RobotSootnOpMode extends PhantomOpMode {
    @Override
    public void customOpModeSettings() throws InterruptedException {
        Robot.INSTANCE.addMechanism(new LaunchMechanism());
        Robot.INSTANCE.addMechanism(new ImuMechanism());
        Robot.INSTANCE.setStartAction(new RotateLongAction(true));
    }
}
