package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.actions.auto.AutoMotifFirstLaunch;
import org.firstinspires.ftc.teamcode.own.actions.auto.MotifFinder;
import org.firstinspires.ftc.teamcode.own.actions.auto.PedroAction;
import org.firstinspires.ftc.teamcode.own.actions.auto.RazgonAction;
import org.firstinspires.ftc.teamcode.own.actions.auto.StopAction;
import org.firstinspires.ftc.teamcode.own.actions.stateaction.AngleStateSwap;
import org.firstinspires.ftc.teamcode.own.actions.stateaction.CaptureStateSwap;
import org.firstinspires.ftc.teamcode.own.actions.stateaction.LaunchStateSwap;
import org.firstinspires.ftc.teamcode.own.actions.stateaction.RevolverStateSwap;
import org.firstinspires.ftc.teamcode.own.actions.stateaction.UpperStateSwap;
import org.firstinspires.ftc.teamcode.own.mechanism.CaptureMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.FollowerMechanismDalniiRed;
import org.firstinspires.ftc.teamcode.own.mechanism.LaunchMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.LimeLightMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.LinearGroup;
import org.firstinspires.ftc.teamcode.own.utils.actions.ParallelGroup;

@Autonomous
public class AutoDalniiStart3Red extends PhantomOpMode {
    @Override
    public void customOpModeSettings() throws InterruptedException {
        Robot.INSTANCE.addMechanism(new CaptureMechanism());
        Robot.INSTANCE.addMechanism(new FollowerMechanismDalniiRed());
        Robot.INSTANCE.addMechanism(new LaunchMechanism());
        Robot.INSTANCE.addMechanism(new LimeLightMechanism());
        Robot.INSTANCE.setStartAction(
                new ParallelGroup(
                        new LaunchStateSwap(),
                        new RevolverStateSwap(),
                        new CaptureStateSwap(),
                        new AngleStateSwap(),
                        new UpperStateSwap(),
                        new LinearGroup(
                                new PedroAction(
                                        40,110,
                                        new BezierLine(
                                                new Pose(123.000, 124.000),
                                                new Pose(105.000, 104.000)
                                        )
                                ),
                                new MotifFinder(),
                                new PedroAction(
                                        110,45,
                                        new BezierLine(
                                                new Pose(105.000, 104.000),
                                                new Pose(88.5, 93.5)
                                        )
                                ),
                                new ParallelGroup(
                                        new RazgonAction(),
                                        new AutoMotifFirstLaunch()
                                ),
                                new PedroAction(
                                        45,40,
                                        new BezierLine(
                                                new Pose(88.5, 93.5),
                                                new Pose(125, 99)
                                        )
                                ),
                                new StopAction()

                        )
                )
        );
    }
}
