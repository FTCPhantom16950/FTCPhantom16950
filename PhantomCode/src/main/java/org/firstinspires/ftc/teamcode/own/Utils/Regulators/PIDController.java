package org.firstinspires.ftc.teamcode.own.Utils.Regulators;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.voltageSensor;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class PIDController {
    private double kP = 0, kI = 0, kD = 0;
    ElapsedTime timer = new ElapsedTime();
    private double previousError = 0, previousTime = 0, integralSum = 0;
    public double update(double currentError) {
        double time = timer.seconds();
        double P,I,D;
        double dE = currentError - previousError;
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
        return Range.clip(P+I+D,-1,1);
    }
}
