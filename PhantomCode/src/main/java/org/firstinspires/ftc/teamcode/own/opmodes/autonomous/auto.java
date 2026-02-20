package org.firstinspires.ftc.teamcode.own.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.actions.autoactions.MoveBack;
import org.firstinspires.ftc.teamcode.own.mechanism.WheelBaseMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.actions.LinearGroup;

@Autonomous
public class auto extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        mechanism.add(new WheelBaseMechanism());
        actions = new LinearGroup(
                new MoveBack()
        );
    }
}
