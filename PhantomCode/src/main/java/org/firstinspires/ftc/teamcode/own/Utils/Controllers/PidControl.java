package org.firstinspires.ftc.teamcode.own.Utils.Controllers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PidControl{
    private double p, i,d,reference;
    double lastTime = 0;
    private DcMotorEx motor;
    private double proportional, integral, derivative, output;
    double error, lastError;
    double tolerance = 200;

    double integerLimit = 30;
    double time = 1;

//    KalmanFilter filter = new KalmanFilter(1, 1, motor.getCurrentPosition(), reference, 1);

    /**
     * Конструктор для PIDF регулятора
     * @param motor DcMotorEx мотор в для которого будет настроен PIDF регулятор
     * @param p p коэффициент
     * @param i i коэффициент
     * @param d d коэффициент
     * @param reference число необходимых оборотов моторов
     */
    public PidControl(double p, double i, double d, double reference, DcMotorEx motor) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.reference = reference;
        this.motor = motor;
    }


    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public double getI() {
        return i;
    }

    public void setI(double i) {
        this.i = i;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public DcMotorEx getMotor() {
        return motor;
    }

    public void setMotor(DcMotorEx motor) {
        this.motor = motor;
    }

    public double getReference() {
        return reference;
    }

    public void setReference(double reference) {
        this.reference = reference;
    }

    public double calculate(){
        ElapsedTime timer = new ElapsedTime();
        //double filteredPos = filter.calculate();
        // error = reference - filteredPos;

            time = timer.seconds();
            error = reference - motor.getCurrentPosition();
            double dt = time - lastTime;
            derivative = (error - lastError)/ dt;
            integral += error * dt;
            integral = Math.max(Math.min(integral, integerLimit), -integerLimit);
            proportional = p * error;
            double integration = i * integral;
            double derv = d * derivative;
            output = proportional + integration + derv;
            output = Math.max(Math.min(output, 0.9), -0.9);
            lastError = error;
            lastTime = time;
            return output;
    }}
