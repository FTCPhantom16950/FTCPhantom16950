package org.firstinspires.ftc.teamcode.own.Utils.Regulators;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.opMode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

public class PidController extends Thread {

    private DcMotorEx dcMotorEx;
    private PIDCofficients pidCofficients;

    private double target;
    private double output;

    private double error;
    private double lastVelocity;

    private double integralSum;
    private double filteredAccel;

    public double getkS() {
        return kS;
    }

    public void setkS(double kS) {
        this.kS = kS;
    }

    private double kP, kI, kD, kV = 1.0 / 2400.0, kS;
    private double a = 0.1; // фильтр D

    private long prevTime;

    public PidController(PIDCofficients pidCofficients) {
        this.pidCofficients = pidCofficients;
    }

    @Override
    public void run() {
        prevTime = System.nanoTime();

        while (!opMode.isStopRequested()) {

            long now = System.nanoTime();
            double dt = (now - prevTime) / 1e9;
            prevTime = now;

            if (dt <= 0) continue;

            kP = pidCofficients.getkP();
            kI = pidCofficients.getkI();
            kD = pidCofficients.getkD();

            double velocity = dcMotorEx.getVelocity();
            error = target - velocity;

            // ---------- FEEDFORWARD ----------
            double ff = kV * target;
            if (Math.abs(target) > 1) {
                ff += kS * Math.signum(target);
            }

            // ---------- P ----------
            double P = kP * error;

            // ---------- I ----------
            integralSum += error * dt;
            integralSum = Range.clip(integralSum, -100, 100);
            double I = kI * integralSum;

            // ---------- D (по ускорению скорости) ----------
            double accel = (velocity - lastVelocity) / dt;
            filteredAccel = a * filteredAccel + (1 - a) * accel;
            double D = -kD * filteredAccel;

            lastVelocity = velocity;

            // ---------- OUTPUT ----------
            output = ff + P + I + D;

            output = Range.clip(output, -1, 1);
            if (Math.abs(output) >= 1) {
                integralSum = 0;
            }

            PhantomOpMode.addData("FF", ff);
            PhantomOpMode.addData("PID out", output);
            PhantomOpMode.addData("vel", velocity);
            PhantomOpMode.addData("err", error);

            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {}
            }
    }

    // --- setters / getters ---

    public void setTarget(double target) {
        if (this.target != target) {
            integralSum = 0;
            filteredAccel = 0;
        }
        this.target = target;
    }

    public double getOutput() {
        return output;
    }

    public double getError() {
        return error;
    }

    public void setDcMotorEx(DcMotorEx dcMotorEx) {
        this.dcMotorEx = dcMotorEx;
    }

    public void setPidCofficients(PIDCofficients pidCofficients) {
        this.pidCofficients = pidCofficients;
    }

    public void setA(double a) {
        this.a = a;
    }
}
