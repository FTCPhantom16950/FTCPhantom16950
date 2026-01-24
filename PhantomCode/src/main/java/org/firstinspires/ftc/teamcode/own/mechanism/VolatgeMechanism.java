package org.firstinspires.ftc.teamcode.own.mechanism;

import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;

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
        Robot.addData("voltage", Robot.INSTANCE.voltage);
    }
}
