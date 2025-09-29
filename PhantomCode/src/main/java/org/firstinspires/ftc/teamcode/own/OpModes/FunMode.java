package org.firstinspires.ftc.teamcode.own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.Actions.FunAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.PidKal;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.LinearGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
@TeleOp
public class FunMode extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        PidKal fun = new PidKal(this);
        FunAction funAction = new FunAction(this);
        mechanism.add(fun);
        action = new LinearGroup(this, funAction);
    }
}
