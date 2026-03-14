package org.firstinspires.ftc.teamcode.own.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.actions.util.RotateOtnTest;
import org.firstinspires.ftc.teamcode.own.mechanism.LaunchMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
@Autonomous
public class RotateOnTestOpMode extends PhantomOpMode {
    @Override
    public void customOpModeSettings() throws InterruptedException {
        Robot.INSTANCE.addMechanism(new LaunchMechanism());
        Robot.INSTANCE.setStartAction(new RotateOtnTest());
    }
}
