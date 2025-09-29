package org.firstinspires.ftc.teamcode.own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.Actions.DriveAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
@TeleOp(name = "GamepadBased", group = "")
public class GameBased extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        setName("GamepadBased");
        actions = new DriveAction(this, new WheelBase(this.hardwareMap));
    }
}
