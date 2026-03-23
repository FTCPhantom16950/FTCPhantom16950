package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.actions.auto.PedroAction;
import org.firstinspires.ftc.teamcode.own.actions.util.LimelightAction;
import org.firstinspires.ftc.teamcode.own.actions.util.PedroUpdater;
import org.firstinspires.ftc.teamcode.own.mechanism.FollowerMechanismDalnii;
import org.firstinspires.ftc.teamcode.own.mechanism.ImuMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.LimeLightMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.LinearGroup;
import org.firstinspires.ftc.teamcode.own.utils.actions.ParallelGroup;

@Autonomous
public class AutoOtezdPedro extends PhantomOpMode {
    @Override
    public void customOpModeSettings() throws InterruptedException {
        Robot.INSTANCE.addMechanism(new FollowerMechanismDalnii());
        Robot.INSTANCE.addMechanism(new LimeLightMechanism());
        Robot.INSTANCE.addMechanism(new ImuMechanism());
        Robot.INSTANCE.setStartAction(new LinearGroup(
                new PedroAction(90, 45, new BezierLine(
                        new Pose(36.037, 136.075),
                        new Pose(38.729, 104.187))
                ),
                new PedroAction(
                        45, 135,
                        new BezierLine(
                                new Pose(38.729, 104.187),
                                new Pose(62, 84)
                        )
                ),
                new ParallelGroup(
                        new PedroUpdater(),
                        new LimelightAction()
                )
                )
        );
    }
}
