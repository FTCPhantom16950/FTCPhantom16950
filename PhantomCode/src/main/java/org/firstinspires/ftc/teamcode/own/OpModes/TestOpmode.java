package org.firstinspires.ftc.teamcode.own.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.own.Actions.Test;
import org.firstinspires.ftc.teamcode.own.Actions.Test1;
import org.firstinspires.ftc.teamcode.own.Actions.Test2;
import org.firstinspires.ftc.teamcode.own.Mechanism.Servos;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.LinearGroup;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

@Autonomous
public class TestOpmode extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        Servos servos = new Servos(this);
        mechanism.add(servos);
        action = new ParallelGroup(new Test(this),
                new Test1(this),
                new Test2(this));
    }
}
