package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.jetbrains.annotations.NotNull;
/// класс контроллеров для моторов
public class FTCControllers {
    // объявляем переменные
    LinearOpMode OPMode;
    double currentVelocity;
    double referenceVelocity = 0;
    double integrative = 0;
    double limInteger = 30;
    double der = 0;
    double lastError = 0;
    public double error;
    double filter = 0;
    double prevFilter = 0;
    double lastRef = 0;

    /**
     * Класс контроллеров для моторов
     * @param OPMode опмод в котором он будет работать
     */
    public FTCControllers(LinearOpMode OPMode) {
        this.OPMode = OPMode;
    }

    /**
     * метод для настройки оригинального пидф регулятора для моторов
     * @param motor DcMotorEx мотор в для которого будет настроен PIDF регулятор
     * @param p p коэффициент
     * @param i i коэффициент
     * @param d d коэффициент
     * @param f f коэффициент
     */
    public void originalPIDF(@NotNull DcMotorEx motor, double p, double i, double d, double f) {
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(p, i, d, f);
        motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        int errorThere = motor.getTargetPosition() - motor.getCurrentPosition();
        OPMode.telemetry.addData("P,I,D,F (modified)", "%.04f, %.04f, %.04f, %.04f",
                pidfCoefficients.p, pidfCoefficients.i, pidfCoefficients.d, pidfCoefficients.f);
        OPMode.telemetry.update();
        OPMode.telemetry.addData("Error: ", errorThere);
        OPMode.telemetry.update();
    }

    /**
     * метод для настройки feedForward регулятора для мотора
     * @param motor DcMotorEx мотор в для которого будет настроен FeedForward регулятор
     * @param a a коэффициент (для ускорения)
     * @param v v коэффициент (для скорости)
     * @param g g коэффициент (для противостояния гравитации)
     * @param cos cos коэффициент (для угла, под которым нужно держать механизм)
     * @param degrees значение угла на котором нужно держать мотор
     * @return возвращает мощность для мотора
     */
    public double feedForward(@NotNull DcMotorEx motor, double a, double v, double g, double cos,double degrees) {
        double accel = 15;
        currentVelocity = motor.getVelocity();
        double output = a * accel + v * referenceVelocity + Math.cos(degrees) * cos + g;
        return output;
    }

    /**
     *
     * @param motor DcMotorEx мотор в для которого будет настроен PIDF регулятор
     * @param p p коэффициент
     * @param i i коэффициент
     * @param d d коэффициент
     * @param reference число необходимых оборотов моторов
     * @return возвращает мощность для мотора
     */
    public double PIDController(@NotNull DcMotorEx motor, double p, double i, double d, double reference) {
        double output;
        double a = 0.8;
        ElapsedTime timer = new ElapsedTime();
        error = reference - motor.getCurrentPosition();
        filter = (a * prevFilter) + (1-a) + (error- lastError);
        prevFilter = filter;
        der = filter / timer.milliseconds();
        integrative = integrative + (error * timer.milliseconds());
        if (integrative >= limInteger){
            integrative = limInteger;
        } else if (integrative <= -limInteger){
            integrative = -limInteger;
        }
        output = p * error + integrative * i + der * d;
        timer.reset();
        lastError = error;
        lastRef = reference;
        return output;
    }

    /**
     *
     * @param motor DcMotorEx мотор в для которого будет настроен регулятор
     * @param p p коэффициент
     * @param i i коэффициент
     * @param d d коэффициент
     * @param a a a коэффициент (для ускорения)
     * @param v v коэффициент (для скорости)
     * @param g g коэффициент (для противостояния гравитации)
     * @param cos cos коэффициент (для угла, под которым нужно держать механизм)
     * @param reference число необходимых оборотов моторов
     * @param degrees значение угла на котором нужно держать мотор
     */
    public void fullControl(@NotNull DcMotorEx motor, double p, double i, double d, double a, double v, double g, double cos, double reference, double degrees) {
        boolean pointReached = false;
        double tolerance = 100;
        double output = 0;
        if (motor.getCurrentPosition() >= (motor.getTargetPosition() - tolerance) && (motor.getCurrentPosition() <= (motor.getTargetPosition() + tolerance))){
            pointReached = true;
        }
        while (!pointReached) {
            output = PIDController(motor, p, i, d, reference) + feedForward(motor, a, v, g, cos, degrees);
            motor.setPower(output);
        }
        if (output >= 0){
            motor.setPower(-0.1);
        } else if (output < 0) {
            motor.setPower(0.1);
        }
        motor.setPower(0);
    }

    /**
     * full state feedback контроллер
     * @param motor DcMotorEx мотор в для которого будет настроен регулятор
     * @param reference число необходимых оборотов моторов
     * @param refVelocity необходимая скорость мотора
     * @param k1 коэффициент 1(коэффициент для ошибки позиции)
     * @param k2 коэффициент 2(коэффициент для ошибки скорости)
     * @return возвращает мощность для мотора
     */
    public double stateControl(@NotNull DcMotorEx motor, double reference, double refVelocity, double k1, double k2){
        double errorPos = reference - motor.getCurrentPosition();
        double errorVelocity = refVelocity - motor.getVelocity();
        double output = errorPos * k1 + errorVelocity * k2;
        return output;
    }





}