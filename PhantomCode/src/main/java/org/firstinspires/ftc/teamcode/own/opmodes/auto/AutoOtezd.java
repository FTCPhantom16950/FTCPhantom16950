package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.actions.auto.MoveRobotAutoAction;
import org.firstinspires.ftc.teamcode.own.mechanism.WheelBaseMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
@Autonomous
public class AutoOtezd extends PhantomOpMode {
    @Override
    public void customOpModeSettings() throws InterruptedException {
        Robot.INSTANCE.addMechanism(new WheelBaseMechanism());
        Robot.INSTANCE.setStartAction(new MoveRobotAutoAction(0,-1,0,500));
    }
}
