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
    long timeDelta, prevTime;
    private double kP, kI, kD;
    DcMotorEx dcMotorEx;

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    @Override
    public void run() {
        super.run();
        timer = new ElapsedTime();
        prevTime = System.nanoTime();
        while (!opMode.isStopRequested()) {
            kP = pidCofficients.getkP();
            kI = pidCofficients.getkI();
            kD = pidCofficients.getkD();
            timeDelta = System.nanoTime() - prevTime;
            error = target - dcMotorEx.getVelocity();
//            if (target + 150 >= dcMotorEx.getVelocity() && target - 150 <= dcMotorEx.getVelocity()){

                if (Math.abs(error) >= 350){
                    P = kP * error;
                    integralSum = integralSum + error * timeDelta;
                    if (integralSum > 100) {
                        integralSum = 100;
                    } else if (integralSum < -100) {
                        integralSum = -100;
                    }
                    I = kI * integralSum;
                    D = kD * (error - lastError) / timeDelta;
                    lastError = error;
                    prevTime = System.nanoTime();
                    output = P + I + D;
//                    output = Range.clip(output, -1, 1);
                    timer.reset();
                }
//            }
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
