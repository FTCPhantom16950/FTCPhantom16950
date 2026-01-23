package org.firstinspires.ftc.teamcode.Own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Own.Actions.SoundActions.AYLOKAction;
import org.firstinspires.ftc.teamcode.Own.Actions.TeleOPActions.DriveAction;
import org.firstinspires.ftc.teamcode.Own.Actions.TeleOPActions.PodemAction;
import org.firstinspires.ftc.teamcode.Own.Mechanism.ParkovkaMechanism;
import org.firstinspires.ftc.teamcode.Own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.Own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomOpMode;

@TeleOp
public class KolyaTeleOpJava extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        ParkovkaMechanism parkovkaMechanism = new ParkovkaMechanism();
        mechanism.add(new WheelBase());
//        mechanism.add(parkovkaMechanism);
        actions = new ParallelGroup(
                new DriveAction()
//                ,new PodemAction()
                ,new AYLOKAction()
        );
    }
}
