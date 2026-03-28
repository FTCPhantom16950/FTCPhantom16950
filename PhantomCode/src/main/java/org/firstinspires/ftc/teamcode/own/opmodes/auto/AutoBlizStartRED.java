package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.actions.auto.MotifFinder;
import org.firstinspires.ftc.teamcode.own.actions.auto.PedroAction;
import org.firstinspires.ftc.teamcode.own.actions.auto.RazgonAction;
import org.firstinspires.ftc.teamcode.own.mechanism.FollowerMechanismBlizRED;
import org.firstinspires.ftc.teamcode.own.mechanism.LaunchMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.LimeLightMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.LinearGroup;

@Autonomous
public class AutoBlizStartRED extends PhantomOpMode {
    @Override
    public void customOpModeSettings() throws InterruptedException {
        Robot.INSTANCE.addMechanism(new FollowerMechanismBlizRED());
        Robot.INSTANCE.addMechanism(new LaunchMechanism());
        Robot.INSTANCE.addMechanism(new LimeLightMechanism());
        Robot.INSTANCE.setStartAction(
                new LinearGroup(
                        new PedroAction(
                                40, 110,
                                new BezierLine(
                                        new Pose(95.000, 8.000),
                                        new Pose(113.000, 23.000)
                                )
                        ),
                        new MotifFinder(),
                        new PedroAction(
                                110, 40,
                                new BezierLine(
                                        new Pose(105.000, 104.000),
                                        new Pose(90.000, 100.000)
                                )),
                        new RazgonAction()
                ));
    }
}
