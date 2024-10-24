package org.firstinspires.ftc.teamcode.FORTEST.Controllers;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class FeedForwardControl{
    private double a, v, g, cos, degrees;
    private DcMotorEx motor;

    /**
     * метод для настройки feedForward регулятора для мотора
     * @param motor DcMotorEx мотор в для которого будет настроен FeedForward регулятор
     * @param a a коэффициент (для ускорения)
     * @param v v коэффициент (для скорости)
     * @param g g коэффициент (для противостояния гравитации)
     * @param cos cos коэффициент (для угла, под которым нужно держать механизм)
     * @param degrees значение угла на котором нужно держать мотор
     */
    public FeedForwardControl(double a, double v, double g, double cos, double degrees, DcMotorEx motor) {
        this.a = a;
        this.v = v;
        this.g = g;
        this.cos = cos;
        this.degrees = degrees;
        this.motor = motor;
    }
    public double feedForward(double referenceAcceleration, double referenceVelocity){
        double output = a * referenceAcceleration + v * referenceVelocity + cos * degrees + g;
        output = Math.max(Math.min(output, 1), -1);
        return output;
    }
}
