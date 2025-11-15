package org.firstinspires.ftc.teamcode.own.Utils.Regulators;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.opMode;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.voltageSensor;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class PIDController extends Thread {
    public PIDController(PIDCofficients pidCofficients) {
        this.pidCofficients = pidCofficients;
    }

    private double maxPower;

    public double getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(double maxPower) {
        this.maxPower = maxPower;
    }

    private PIDCofficients pidCofficients;
    private double currentError = 0d;
    private double kP, kI, kD;
    ElapsedTime timer = new ElapsedTime();
    private double previousError = 0, previousTime = 0, integralSum = 0;
    private double output = 0;

    public PIDCofficients getPidCofficients() {
        return pidCofficients;
    }

    public void setPidCofficients(PIDCofficients pidCofficients) {
        this.pidCofficients = pidCofficients;
    }

    public double getCurrentError() {
        return currentError;
    }

    public void setCurrentError(double currentError) {
        this.currentError = currentError;
    }

    public double update() {
        kP = pidCofficients.getkP();
        kI = pidCofficients.getkI();
        kD = pidCofficients.getkD();
        double time = timer.seconds();
        double P, I, D;
        double dE = (currentError - previousError);
        double dT;
        if (previousTime == 0) {
            dT = 0;
            previousTime = time;
        } else {
            dT = time - previousTime;
        }
        if (dT > 0) {
            integralSum += (currentError * dT);
        }
        P = currentError * kP;
        I = integralSum * kI;
        if (dT > 0) {
            D = (dE / dT) * kD;
        } else {
            D = 0;
        }
        previousError = currentError;
        previousTime = time;
        timer.reset();
        return Range.clip(P + I + D, -1, 1);

    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    @Override
    public void run() {
        super.run();
        while (!opMode.isStopRequested()) {
            output = update();
            PhantomOpMode.addData("output", output);
        }
    }
}
