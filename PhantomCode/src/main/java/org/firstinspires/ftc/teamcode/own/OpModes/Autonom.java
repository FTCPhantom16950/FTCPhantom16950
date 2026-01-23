package org.firstinspires.ftc.teamcode.Own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Own.Actions.AutoActions.MoveAction;
import org.firstinspires.ftc.teamcode.Own.Actions.AutoActions.PulaloAction;
import org.firstinspires.ftc.teamcode.Own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.Own.Utils.Action.Groups.LinearGroup;
import org.firstinspires.ftc.teamcode.Own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomOpMode;
@Autonomous
public class Autonom extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        mechanism.add(new WheelBase());
        actions = new LinearGroup(
                new MoveAction(0.5, 0,0,500)
        );
    }
}
