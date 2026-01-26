package org.firstinspires.ftc.teamcode.own.utils.safehardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class SfEncoder {
    private final DcMotorEx dcMotorEx;
    private final Object lock = new Object();
    private volatile double lastPower = 0.0, lastPosition = 0.0;
    private final int encoderResolution;
    public SfEncoder(DcMotorEx dcMotorEx, int encoderResolution) {
        this.dcMotorEx = dcMotorEx;
        this.encoderResolution = encoderResolution;
    }

    public double getPower() {
        return lastPower;
    }
    public void resetEncoder(){
        var prev = dcMotorEx.getMode();
        dcMotorEx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dcMotorEx.setMode(prev);
    }
    public void setMode(DcMotor.RunMode mode) {
        synchronized (lock) {
            dcMotorEx.setMode(mode);
        }
    }

    public void setDirection(DcMotor.Direction direction) {
        synchronized (lock) {
            dcMotorEx.setDirection(direction);
        }
    }

    public int getCurrentPosition() {
        return dcMotorEx.getCurrentPosition();
    }
    public double getVelocity(){
        return dcMotorEx.getVelocity() * 60 / encoderResolution;
    }

}
