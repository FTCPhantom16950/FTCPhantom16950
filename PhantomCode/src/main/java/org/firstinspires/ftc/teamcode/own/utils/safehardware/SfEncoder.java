package org.firstinspires.ftc.teamcode.own.utils.safehardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class SfEncoder {
    private final DcMotorEx dcMotorEx;
    private final Object lock = new Object();
    DcMotorSimple.Direction direction = DcMotorSimple.Direction.FORWARD;
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
            this.direction = direction;
        }
    }

    public int getCurrentPosition() {
        if (direction == DcMotorSimple.Direction.FORWARD){
            return dcMotorEx.getCurrentPosition();
        } else{
            return -dcMotorEx.getCurrentPosition();
        }
    }
    public double getVelocity(){
        if (direction == DcMotorSimple.Direction.FORWARD){
            return dcMotorEx.getVelocity() * 60 / encoderResolution;
        } else {
            return -dcMotorEx.getVelocity() * 60 / encoderResolution;
        }

    }

}
