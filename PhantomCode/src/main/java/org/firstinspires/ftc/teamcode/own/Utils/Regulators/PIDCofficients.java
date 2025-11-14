package org.firstinspires.ftc.teamcode.own.Utils.Regulators;

public class PIDCofficients{
    public void setkP(double kP) {
        this.kP = kP;
    }

    public void setkI(double kI) {
        this.kI = kI;
    }

    public void setkD(double kD) {
        this.kD = kD;
    }

    private double kP = 0, kI = 0, kD = 0;

    public double getkP() {
        return kP;
    }

    public double getkI() {
        return kI;
    }

    public double getkD() {
        return kD;
    }

    public PIDCofficients(double kP) {
        this.kP = kP;
        this.kI = 0;
        this.kD = 0;
    }

    public PIDCofficients(double kP, double kD) {
        this.kP = kP;
        this.kI = 0;
        this.kD = kD;
    }

    public PIDCofficients(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }
}