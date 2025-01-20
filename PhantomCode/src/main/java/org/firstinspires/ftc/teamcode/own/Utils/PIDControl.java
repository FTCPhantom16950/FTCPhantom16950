package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class PIDControl extends Thread{
    int target, measured, tolerance = 50;
    LinearOpMode opMode;
    int error = 0, lastError = error;
    double D, I, P, iSum;
    double minPower = -1, maxPower = 1;
    public boolean isAtTargetPos() {
        return atTargetPos;
    }

    public void setAtTargetPos(boolean atTargetPos) {
        this.atTargetPos = atTargetPos;
    }

    boolean atTargetPos = false;
    public void setkP(double kP) {
        this.kP = kP;
    }

    public void setkD(double kD) {
        this.kD = kD;
    }

    public void setkI(double kI) {
        this.kI = kI;
    }

    double kP, kD, kI;
    double out;
    ElapsedTime timer = new ElapsedTime();
    public void setOpMode(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }
    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getMeasured() {
        return measured;
    }

    public void setMeasured(int measured) {
        this.measured = measured;
    }

    @Override
    public synchronized void start() {
        super.start();
        while(opMode.opModeIsActive()){
            calculate(target, measured);
            calcError();
            timer.reset();
        }
    }

    public double getOut() {
        return out;
    }


    public double getMinPower() {
        return minPower;
    }

    public void setMinPower(double minPower) {
        this.minPower = minPower;
    }

    public double getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(double maxPower) {
        this.maxPower = maxPower;
    }

    public void calculate(int target, int measured){
        while(!(target - tolerance < measured) && !(target + tolerance > measured)){
            D = kD * ((error - lastError) / timer.seconds());
            P = error * kP;
            iSum = iSum + (error * timer.seconds());
            if (iSum > 100){
                iSum = 100;
            } else if (iSum < -100){
                iSum = -100;
            }
            I = iSum * kI;
            out = Range.clip( P + I + D, minPower, maxPower);
            atTargetPos = false;
        }
        if (!(!(target - tolerance < measured) && !(target + tolerance > measured))){
            atTargetPos = true;
        }
    }
    public void calcError(){
        error = target - measured;
        lastError = error;
    }
}
