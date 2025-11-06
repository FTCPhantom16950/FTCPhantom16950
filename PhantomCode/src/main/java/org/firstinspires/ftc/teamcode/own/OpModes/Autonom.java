package org.firstinspires.ftc.teamcode.own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.Actions.AutoActions.AutoAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
@Autonomous
public class Autonom extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        WheelBase wheelbase = new WheelBase(this.hardwareMap);
        mechanism.add(wheelbase);
        actions = new AutoAction();
    }
}
