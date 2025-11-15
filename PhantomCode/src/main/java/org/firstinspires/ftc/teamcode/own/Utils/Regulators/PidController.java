package org.firstinspires.ftc.teamcode.own.Utils.Regulators;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class PidController extends Thread {
    private PIDCofficients pidCofficients;
    ElapsedTime timer;

    public PidController(PIDCofficients pidCofficients) {
        this.pidCofficients = pidCofficients;
    }
    private double P, I, integralSum, D, error, output, lastError, target;
    private double kP, kI, kD;
    DcMotorEx dcMotorEx;

    @Override
    public void run() {
        super.run();
        timer = new ElapsedTime();
        while (!opMode.isStopRequested()) {
            kP = pidCofficients.getkP();
            kI = pidCofficients.getkI();
            kD = pidCofficients.getkD();

            error = target - dcMotorEx.getVelocity();

            P = kP * error;

            integralSum = integralSum + error * timer.seconds();
            if (integralSum > 100) {
                integralSum = 100;
            } else if (integralSum < -100) {
                integralSum = -100;
            }
            I = kI * integralSum;

            D = kD * (error - lastError) / timer.seconds();
            lastError = error;

            output = P + I + D;
            output = Range.clip(output, -1, 1);
            timer.reset();
        }
    }
    public PIDCofficients getPidCofficients() {
        return pidCofficients;
    }

    public void setPidCofficients(PIDCofficients pidCofficients) {
        this.pidCofficients = pidCofficients;
    }
    public void setDcMotorEx(DcMotorEx dcMotorEx) {
        this.dcMotorEx = dcMotorEx;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getOutput() {
        return output;
    }
}
