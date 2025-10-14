package org.firstinspires.ftc.teamcode.own.OpModes;

import static org.firstinspires.ftc.teamcode.own.Utils.Points.endToThird;
import static org.firstinspires.ftc.teamcode.own.Utils.Points.firstToStart;
import static org.firstinspires.ftc.teamcode.own.Utils.Points.secondToEnd;
import static org.firstinspires.ftc.teamcode.own.Utils.Points.start;
import static org.firstinspires.ftc.teamcode.own.Utils.Points.startToSecond;
import static org.firstinspires.ftc.teamcode.own.Utils.Points.thirdToEnd;

import org.firstinspires.ftc.teamcode.own.Actions.PedroAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.FollowerMechanism;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.LinearGroup;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class OpMode2 extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
        FollowerMechanism xui = new FollowerMechanism(this.hardwareMap);
        mechanism.add(xui);
        actions = new LinearGroup(this,
                new ParallelGroup(this, new PedroAction(this, start)),
                new ParallelGroup(this, new PedroAction(this, firstToStart)),
                new ParallelGroup(this, new PedroAction(this, startToSecond)),
                new ParallelGroup(this, new PedroAction(this, secondToEnd)),
                new ParallelGroup(this, new PedroAction(this, endToThird)),
                new ParallelGroup(this, new PedroAction(this, thirdToEnd))
                );

    }
}
