package org.firstinspires.ftc.teamcode.own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.Actions.TeleActions.DriveAction;
import org.firstinspires.ftc.teamcode.own.Actions.TeleActions.PodemAction;
import org.firstinspires.ftc.teamcode.own.Actions.SoundActions.ShutkaAction;
import org.firstinspires.ftc.teamcode.own.Actions.TeleActions.ShootAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.ParkovkaMechanism;
import org.firstinspires.ftc.teamcode.own.Mechanism.ShootMechanism;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;



@TeleOp
public class MainTeleOpJava extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        WheelBase wheelBase = new WheelBase(this.hardwareMap);
        ParkovkaMechanism parkovkaMechanism = new ParkovkaMechanism();
        ShootMechanism shootMechanism = new ShootMechanism();
        mechanism.add(wheelBase);
        mechanism.add(parkovkaMechanism);
        mechanism.add(shootMechanism);
        actions = new ParallelGroup(
                new DriveAction()
                ,new PodemAction()
//                ,new AYLOKAction()
                ,new ShutkaAction()
                , new ShootAction()
        );
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
