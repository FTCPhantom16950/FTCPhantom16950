package org.firstinspires.ftc.teamcode.own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.Actions.DriveAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.LinearGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;


@TeleOp
public class MainTeleOpJava extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        WheelBase wheelBase = new WheelBase(this.hardwareMap);
        mechanism.add(wheelBase);
        actions = new LinearGroup(
                new DriveAction(this));
    }
}
