package org.firstinspires.ftc.teamcode.own.Utils;

import com.acmerobotics.roadrunner.Line;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PidControl{
    private double p, i,d,reference;
    private DcMotorEx motor;
    private double proportional, integral, derivative, filter, prevFilter, output;
    double error, lastError;
    double lastRef = 0;
    double integerLimit = 30;
    double time = 0;
    LinearOpMode OPMode;

    /**
     * Конструктор для PIDF регулятора
     * @param motor DcMotorEx мотор в для которого будет настроен PIDF регулятор
     * @param p p коэффициент
     * @param i i коэффициент
     * @param d d коэффициент
     * @param reference число необходимых оборотов моторов
     */
    public PidControl(double p, double i, double d, double reference, DcMotorEx motor, double time, LinearOpMode OPMode) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.reference = reference;
        this.motor = motor;
        this.time = time;
        this.OPMode = OPMode;
    }
    public void calculateError(){
        while (OPMode.opModeIsActive()){
            error = reference - motor.getCurrentPosition();
            lastError = error;
            lastRef = reference;
        }
    }
    public double calculate(){
        Thread errorThread = new Thread(this::calculateError);
        errorThread.start();
        double a = 0.8;
        ElapsedTime timer = new ElapsedTime();
        filter = (a * prevFilter) + (1-a) * (error- lastError);
        prevFilter = filter;
        derivative = filter / time;
        integral += error * time;
        integral = Math.max(Math.min(integral, integerLimit), -integerLimit);
        proportional = p * error;
        double integration = i * integral;
        double derv = d * derivative;
        output = proportional + integration + derv;
        output = Math.max(Math.min(output, 1), -1);
        timer.reset();
        return output;
    }}
