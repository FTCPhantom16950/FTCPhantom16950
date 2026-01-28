package org.firstinspires.ftc.teamcode.own.mechanism;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfCrServo;
@Config
@Configurable
public class SpinMechanism implements Mechanism {
    public static double startDegree = 135;
    SfCrServo crServo;

    @Override
    public void read() {
        Mechanism.super.read();
        Robot.INSTANCE.addData("startSpinServoDegree", startDegree);

    }

    @Override
    public void init() throws InterruptedException {
        crServo = new SfCrServo(Robot.INSTANCE.hw.get(CRServo.class, "spin"));
        crServo.setPower(0);
        Robot.INSTANCE.addOrUpdate(crServo, "spinServo");
    }
}
