package org.firstinspires.ftc.teamcode.own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.Actions.SoundActions.AYLOKAction;
import org.firstinspires.ftc.teamcode.own.Actions.TeleOPActions.DriveAction;
import org.firstinspires.ftc.teamcode.own.Actions.TeleOPActions.PodemAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.ParkovkaMechanism;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

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
