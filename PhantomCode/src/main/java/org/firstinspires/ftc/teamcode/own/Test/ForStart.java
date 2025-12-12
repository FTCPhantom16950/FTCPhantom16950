package org.firstinspires.ftc.teamcode.own.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.Actions.Test.DsTestAction;
import org.firstinspires.ftc.teamcode.own.Actions.Test.ForTestAction;
import org.firstinspires.ftc.teamcode.own.Actions.Test.MaxSpeedGetter;
import org.firstinspires.ftc.teamcode.own.Actions.Test.TestDrive;
import org.firstinspires.ftc.teamcode.own.Actions.Utility.ThreeWheelOdometry;
import org.firstinspires.ftc.teamcode.own.Mechanism.PIDFTestMechanism;
import org.firstinspires.ftc.teamcode.own.Mechanism.TestDSMechanism;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

@TeleOp
public class ForStart extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
//        mechanism.add(new TestDSMechanism());
        mechanism.add(new PIDFTestMechanism());
        actions = new ParallelGroup(
                new ForTestAction());
    }
}
