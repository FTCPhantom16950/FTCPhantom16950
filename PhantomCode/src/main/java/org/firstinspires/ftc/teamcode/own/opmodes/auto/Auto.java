package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.actions.auto.ImuLogAction;
import org.firstinspires.ftc.teamcode.own.actions.auto.MoveForwardAction;
import org.firstinspires.ftc.teamcode.own.mechanism.ImuMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.WheelBaseMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;

@Autonomous
public class Auto extends PhantomOpMode {
    @Override
    public void customOpModeSettings() throws InterruptedException {
//        Robot.INSTANCE.addMechanism(new WheelBaseMechanism());
        Robot.INSTANCE.addMechanism(new ImuMechanism());
        Robot.INSTANCE.setStartAction(new ImuLogAction());
    }
}
