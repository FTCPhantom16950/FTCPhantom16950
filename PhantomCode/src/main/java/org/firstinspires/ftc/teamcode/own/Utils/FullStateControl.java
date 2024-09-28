package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class FullStateControl{
    private double referencePosition, referenceVelocity;
    private double k1, k2;
    private DcMotorEx motor;
    private double maxPower = 1;
    /**
     * full state feedback контроллер
     * @param motor DcMotorEx мотор в для которого будет настроен регулятор
     * @param referencePosition число необходимых оборотов моторов
     * @param referenceVelocity необходимая скорость мотора
     * @param k1 коэффициент 1(коэффициент для ошибки позиции)
     * @param k2 коэффициент 2(коэффициент для ошибки скорости)
     */
    public FullStateControl(double referencePosition, double referenceVelocity, double k1, double k2, DcMotorEx motor) {
        this.referencePosition = referencePosition;
        this.referenceVelocity = referenceVelocity;
        this.k1 = k1;
        this.k2 = k2;
        this.motor = motor;
    }
    public double stateControl(){
        double errorPos = referencePosition - motor.getCurrentPosition();
        double errorVelocity = referenceVelocity - motor.getVelocity();
        double output = k1 * errorPos + k2 * errorVelocity;
        output = Math.max(Math.min(output, maxPower), -maxPower);
        return output;
    }
}
