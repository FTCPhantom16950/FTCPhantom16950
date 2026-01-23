package org.firstinspires.ftc.teamcode.Own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Own.Actions.TeleOPActions.DriveAction;
import org.firstinspires.ftc.teamcode.Own.Actions.TeleOPActions.PodemAction;
import org.firstinspires.ftc.teamcode.Own.Actions.SoundActions.ShutkaAction;
import org.firstinspires.ftc.teamcode.Own.Actions.TeleOPActions.PodxodAction;
import org.firstinspires.ftc.teamcode.Own.Actions.TeleOPActions.ShootAction;
import org.firstinspires.ftc.teamcode.Own.Actions.TeleOPActions.SosaloAction;
import org.firstinspires.ftc.teamcode.Own.Mechanism.ParkovkaMechanism;
import org.firstinspires.ftc.teamcode.Own.Mechanism.PodxodMechanism;
import org.firstinspires.ftc.teamcode.Own.Mechanism.SOSaloMechanism;
import org.firstinspires.ftc.teamcode.Own.Mechanism.ShootMechanism;
import org.firstinspires.ftc.teamcode.Own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.Own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomOpMode;



@TeleOp
public class MainTeleOpJava extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        SOSaloMechanism soSaloMechanism = new SOSaloMechanism();
        WheelBase wheelBase = new WheelBase();
        ParkovkaMechanism parkovkaMechanism = new ParkovkaMechanism();
        ShootMechanism shootMechanism = new ShootMechanism();
        PodxodMechanism podxodMechanism = new PodxodMechanism();
        mechanism.add(podxodMechanism);
        mechanism.add(wheelBase);
        mechanism.add(parkovkaMechanism);
        mechanism.add(shootMechanism);
        mechanism.add(soSaloMechanism);
        actions = new ParallelGroup(
                new DriveAction()
                ,new PodemAction()
//                ,new AYLOKAction()
                ,new ShutkaAction()
                , new ShootAction()
                , new PodxodAction()
                , new SosaloAction()
        );
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
