package org.firstinspires.ftc.teamcode.own.utils.regulators;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDPoseRegulator {
    double output = 0;
    private double I, integralSum, P, D, derivative, F;
    private double kP;
    private double kD;
    private double kI, kF;
    private double targetPose, currentPose;
    private double currentError, tolerance;
    private double previousError;
    private ElapsedTime timer;
    private double derivativeFilter;

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    boolean stop = false;

    public PIDPoseRegulator(double kP, double kD, double kI, double kF, double tolerance, double derivativeFilter) {
        this.kP = kP;
        this.kD = kD;
        this.kI = kI;
        this.kF = kF;
        this.tolerance = tolerance;
        this.derivativeFilter = derivativeFilter;
        timer = new ElapsedTime();
    }

    public double getCurrentPose() {
        return currentPose;
    }

    public void setCurrentPose(double currentPose) {
        this.currentPose = currentPose;
    }

    public double getDerivativeFilter() {
        return derivativeFilter;
    }

    public void setDerivativeFilter(double derivativeFilter) {
        this.derivativeFilter = derivativeFilter;
    }

    public double getTolerance() {
        return tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    public double getTargetPose() {
        return targetPose;
    }

    public void setTargetPose(double targetPose) {
        this.targetPose = targetPose;
    }

    public double getkF() {
        return kF;
    }

    public void setkF(double kF) {
        this.kF = kF;
    }

    public double getkI() {
        return kI;
    }

    public void setkI(double kI) {
        this.kI = kI;
    }

    public double getkD() {
        return kD;
    }

    public void setkD(double kD) {
        this.kD = kD;
    }

    public double getkP() {
        return kP;
    }

    public void setkP(double kP) {
        this.kP = kP;
    }

    public double update() {
        timer.reset();
        currentError = (targetPose - currentPose);
        P = kP * currentError;
        integralSum += currentError * timer.seconds();
        if (integralSum >= 1000) {
            integralSum = 1000;
        } else if (integralSum <= -1000) {
            integralSum = -1000;
        }
        F = kF * targetPose;
        I = integralSum * kI;
        derivative = derivativeFilter * previousError + (1 - derivativeFilter) * currentError;
        D = derivative * kD;
        output = P + I + D + F;

        if (Math.abs(currentError) <= tolerance) {
            output = 0;
            stop = true;
        } else if (Math.abs(currentError) >= tolerance) {
            stop = false;
        }
        previousError = currentError;
        return output;
    }

}
