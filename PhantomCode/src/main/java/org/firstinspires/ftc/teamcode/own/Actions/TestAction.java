package org.firstinspires.ftc.teamcode.own.Actions;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Mechanism.TestMechanism;
import org.firstinspires.ftc.teamcode.own.Utils.Action;

public class TestAction extends Action {
    public TestAction(HardwareMap hw) {
        addNecessaryMechanism(new TestMechanism(hw));
    }

    @Override
    public void execute() {
        System.out.println("test Succed");
    }
}
