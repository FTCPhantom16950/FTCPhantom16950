package org.firstinspires.ftc.teamcode.own.Utils.Regulators;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import com.qualcomm.robotcore.util.Range;

public class FeedForwardController {
    private double kV = 0.0;
    private double kA = 0.0;
    private double kS = 0.0;

    public double voltageNominal = 12.5;

    public double update(double targetVelocity, double targetAcceleration) {
        return Range.clip((kV * targetVelocity) + (kA * targetAcceleration) + (kS * Math.signum(targetVelocity)) * (voltageNominal / voltageSensor.getVoltage()),-1, 1);
    }

    public void setkV(double kV) {
        this.kV = kV;
    }

    public void setkA(double kA) {
        this.kA = kA;
    }

    public void setkS(double kS) {
        this.kS = kS;
    }
}
