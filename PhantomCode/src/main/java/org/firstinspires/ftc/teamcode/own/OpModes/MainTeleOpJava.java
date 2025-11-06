package org.firstinspires.ftc.teamcode.own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.Actions.TeleActions.DriveAction;
import org.firstinspires.ftc.teamcode.own.Actions.TeleActions.PodemAction;
import org.firstinspires.ftc.teamcode.own.Actions.SoundActions.ShutkaAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.ParkovkaMechanism;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;



@TeleOp
public class MainTeleOpJava extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        WheelBase wheelBase = new WheelBase(this.hardwareMap);
        ParkovkaMechanism parkovkaMechanism = new ParkovkaMechanism();
        mechanism.add(wheelBase);
        mechanism.add(parkovkaMechanism);
        actions = new ParallelGroup(
                new DriveAction()
                ,new PodemAction()
//                ,new AYLOKAction()
                ,new ShutkaAction()
        );
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
