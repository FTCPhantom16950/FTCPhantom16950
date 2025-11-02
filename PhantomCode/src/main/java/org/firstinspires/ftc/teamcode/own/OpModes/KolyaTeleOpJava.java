package org.firstinspires.ftc.teamcode.own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.Actions.AYLOKAction;
import org.firstinspires.ftc.teamcode.own.Actions.DriveAction;
import org.firstinspires.ftc.teamcode.own.Actions.PodemAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.ParkovkaMechanism;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;


@TeleOp
public class KolyaTeleOpJava extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        WheelBase wheelBase = new WheelBase(this.hardwareMap);
        ParkovkaMechanism parkovkaMechanism = new ParkovkaMechanism();
        mechanism.add(wheelBase);
        mechanism.add(parkovkaMechanism);
        actions = new ParallelGroup(
                new DriveAction()
                ,new PodemAction()
                ,new AYLOKAction()
        );
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
