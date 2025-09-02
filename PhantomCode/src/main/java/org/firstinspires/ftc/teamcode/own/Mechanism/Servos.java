package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class Servos implements Mechanism {
    public static CRServo sample, krut;
    PhantomOpMode opMode;

    public Servos(PhantomOpMode opMode) {
        this.opMode = opMode;
    }

    @Override
    public boolean init() {
        sample = opMode.hardwareMap.get(CRServo.class, "sample");
        krut = opMode.hardwareMap.get(CRServo.class, "krut");
        return true;
    }
}
