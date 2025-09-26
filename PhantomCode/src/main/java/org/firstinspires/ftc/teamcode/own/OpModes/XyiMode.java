package org.firstinspires.ftc.teamcode.own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.Actions.XyiAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.PidKal;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.LinearGroup;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
@TeleOp
public class XyiMode extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        PidKal xui = new PidKal(this);
        XyiAction xyiAction = new XyiAction(this);
        mechanism.add(xui);
        action = new LinearGroup(this,xyiAction);
    }
}
