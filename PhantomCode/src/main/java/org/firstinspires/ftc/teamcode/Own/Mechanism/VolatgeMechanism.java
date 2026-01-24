package org.firstinspires.ftc.teamcode.Own.Mechanism;

import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.Own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.Own.Utils.Robot;

public class VolatgeMechanism implements Mechanism {
    @Override
    public void init() throws InterruptedException {
        VoltageSensor voltageSensor = Robot.INSTANCE.hw.voltageSensor.iterator().next();
        Robot.INSTANCE.addOrUpdate(voltageSensor, "vltg");
    }

    @Override
    public void read() {
        Mechanism.super.read();
        Robot.INSTANCE.voltage = Robot.INSTANCE.voltageSensor.getVoltage();
        Robot.addTelemetryData("voltage", Robot.INSTANCE.voltage);
    }
}
