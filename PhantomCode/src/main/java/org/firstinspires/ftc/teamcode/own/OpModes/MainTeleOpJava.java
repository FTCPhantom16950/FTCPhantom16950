package org.firstinspires.ftc.teamcode.own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import org.firstinspires.ftc.teamcode.own.Actions.DriveAction;
import org.firstinspires.ftc.teamcode.own.Actions.PodemAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.ParkovkaMechanism;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.LinearGroup;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.psilynx.psikit.core.Logger;


@TeleOp
public class MainTeleOpJava extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        WheelBase wheelBase = new WheelBase(this.hardwareMap);
        ParkovkaMechanism parkovkaMechanism = new ParkovkaMechanism();
        mechanism.add(wheelBase);
//        mechanism.add(parkovkaMechanism);
        actions = new ParallelGroup(
                new DriveAction()
//                ,new PodemAction()
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        multipleTelemetry.addData("abc", "abc");
        Logger.recordOutput("abc", "abc");
    }
}
