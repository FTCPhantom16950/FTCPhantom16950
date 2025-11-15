package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

public class PodxodMechanism implements Mechanism {
    @Override
    public boolean init() throws InterruptedException {
        CRServo left = Robot.hw.get(CRServo.class, "left");
        CRServo right = Robot.hw.get(CRServo.class, "right");
        left.setPower(0);
        right.setPower(0);
        Robot.addOrUpdate("right", right);
        Robot.addOrUpdate("left", left);
        return true;
    }
}
