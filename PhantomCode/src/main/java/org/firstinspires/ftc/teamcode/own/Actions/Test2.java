package org.firstinspires.ftc.teamcode.own.Actions;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class Test2 extends Action {
    PhantomOpMode opMode;
    Test test;
    Test1 test1;

    public Test2(PhantomOpMode opMode, Test test, Test1 test1) {
        super(opMode);
        this.opMode = opMode;
        this.test = test;
        this.test1 = test1;
    }

    @Override
    public void execute() {
        while (opMode.opModeIsActive()){
            if (opMode.gamepad1.x){
                test1.setInterrupted(true);
                test.setInterrupted(true);
            }
        }
    }
}
