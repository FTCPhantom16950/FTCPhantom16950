package org.firstinspires.ftc.teamcode.own.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.actions.testactions.WheelBaseTestAction;
import org.firstinspires.ftc.teamcode.own.actions.utilactions.GamepadTrackerAction;
import org.firstinspires.ftc.teamcode.own.mechanism.WheelBaseMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.ParallelGroup;

@TeleOp(group = "test")
public class WheelBaseTestOpMode extends PhantomOpMode {

    @Override
    public void customOpModeSettings() {
        mechanism.add(new WheelBaseMechanism());
        actions = new ParallelGroup(
                Robot.INSTANCE.threadPool, this,
                new GamepadTrackerAction(),
                new WheelBaseTestAction()
        );
    }
}
