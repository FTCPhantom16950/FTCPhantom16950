package org.firstinspires.ftc.teamcode.own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.Actions.DriveAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;

import io.github.ftcphantom16950.phantomlib.utils.Action.Groups.LinearGroup;
import io.github.ftcphantom16950.phantomlib.utils.Action.Groups.ParallelGroup;
import io.github.ftcphantom16950.phantomlib.utils.PhantomOpMode;
@TeleOp
public class MainTeleOpJava extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        WheelBase wheelBase = new WheelBase(this.hardwareMap);
        mechanism.add(wheelBase);
        actions = new LinearGroup(this,
                new DriveAction(this));
    }
}
