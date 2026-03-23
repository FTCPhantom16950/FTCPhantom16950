package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.actions.auto.LaunchAutoAction;
import org.firstinspires.ftc.teamcode.own.actions.auto.MoveRobotAutoAction;
import org.firstinspires.ftc.teamcode.own.actions.auto.RazgonAction;
import org.firstinspires.ftc.teamcode.own.actions.auto.SleepAction;
import org.firstinspires.ftc.teamcode.own.actions.stateaction.AngleStateSwap;
import org.firstinspires.ftc.teamcode.own.actions.stateaction.LaunchStateSwap;
import org.firstinspires.ftc.teamcode.own.actions.stateaction.RevolverStateSwap;
import org.firstinspires.ftc.teamcode.own.actions.stateaction.UpperStateSwap;
import org.firstinspires.ftc.teamcode.own.mechanism.CaptureMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.ColorSensorsMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.ImuMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.LaunchMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.StandingMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.WheelBaseMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.LinearGroup;
import org.firstinspires.ftc.teamcode.own.utils.actions.ParallelGroup;

@Autonomous
public class AutoRed extends PhantomOpMode {
    @Override
    public void customOpModeSettings() throws InterruptedException {
        Robot.INSTANCE.addMechanism(new CaptureMechanism());
        Robot.INSTANCE.addMechanism(new LaunchMechanism());
        Robot.INSTANCE.addMechanism(new StandingMechanism());
        Robot.INSTANCE.addMechanism(new WheelBaseMechanism());
        Robot.INSTANCE.addMechanism(new ImuMechanism());
        Robot.INSTANCE.addMechanism(new ColorSensorsMechanism());
        Robot.INSTANCE.setStartAction(new ParallelGroup(
                new LinearGroup(
                        new RazgonAction(),
                        new MoveRobotAutoAction(0,-0.8,0,750),
                        new ParallelGroup(
                                new LaunchAutoAction(),
                                new LinearGroup(
                                        new SleepAction(500),
                                        new MoveRobotAutoAction(0,-0.5,0,300)
                                )
                        ),
                        new MoveRobotAutoAction(-1,0,0,750)
                ),
                new LaunchStateSwap(),
                new AngleStateSwap(),
                new RevolverStateSwap(),
                new UpperStateSwap()
        ));
    }
}
