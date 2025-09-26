package org.firstinspires.ftc.teamcode.own.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.own.Actions.Test;
import org.firstinspires.ftc.teamcode.own.Actions.Test1;
import org.firstinspires.ftc.teamcode.own.Actions.Test2;
import org.firstinspires.ftc.teamcode.own.Mechanism.Servos;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

@Autonomous
public class TestOpmode extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        Servos servos = new Servos(this);
        Test test = new Test(this);
        Test1 test1 = new Test1(this);
        mechanism.add(servos);
        action = new ParallelGroup(this,
                test,
                test1,
                new Test2(this, test, test1));
    }
}
