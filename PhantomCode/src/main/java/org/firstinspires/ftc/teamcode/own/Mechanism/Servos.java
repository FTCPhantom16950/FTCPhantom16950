package org.firstinspires.ftc.teamcode.own.Mechanism;

import static org.firstinspires.ftc.teamcode.own.Utils.Config.*;
import static org.firstinspires.ftc.teamcode.own.Utils.UnitedTelemetry.multipleTelemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class Servos implements Mechanism {
    public static CRServo sample, krut;
    LinearOpMode opMode;

    public Servos(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    @Override
    public boolean init() {
        sample = opMode.hardwareMap.get(CRServo.class, "sample");
        krut = opMode.hardwareMap.get(CRServo.class, "vrash");
        sample.setPower(SAMPLE_START_POWER);
        krut.setPower(KRUT_START_POWER);
        return true;
    }
}
