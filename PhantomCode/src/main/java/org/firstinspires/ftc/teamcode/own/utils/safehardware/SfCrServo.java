package org.firstinspires.ftc.teamcode.own.utils.safehardware;

import com.qualcomm.robotcore.hardware.CRServo;

public class SfCrServo {
    private final CRServo crServo;
    private final Object lock = new Object();
    private volatile double lastPower = 0.0;

    public SfCrServo(CRServo crServo) {
        this.crServo = crServo;
    }
    public double getPower(){
        return lastPower;
    }
    public void setPower(double power){
        synchronized (lock){
            crServo.setPower(power);
            lastPower = power;
        }
    }

}
