package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * класс для работы с PID-регулятором
 */
public class PIDControl extends Thread{
    /// необходимая позиция
    int target,
    /// измеренная позиция
    measured,
    /// погрешность
    tolerance = 50;
    /// ошибка
    int error = 0,
    /// ошибка в предыдущий момент времени
    lastError = error;
    /// /// итоговое D
    double D,
    /// итоговое I
    I,
    /// итоговое P
    P,
    /// cумма для коэф I
    iSum;
    /// минимальная мощность
    double minPower = -1,
    /// максимальная мощность
    maxPower = 1;
    /// булевая переменная текущей позиции
    boolean atTargetPos = false;
    /// Возвращает в нужной ли позиции мотор
    public boolean isAtTargetPos() {
        return atTargetPos;
    }
    /// Устанавливает необходимую позицию мотора
    public void setAtTargetPos(boolean atTargetPos) {
        this.atTargetPos = atTargetPos;
    }

    /// установить P коэффициент
    public void setkP(double kP) {
        this.kP = kP;
    }
    /// установить D коэффициент
    public void setkD(double kD) {
        this.kD = kD;
    }
    /// установить I коэффициент
    public void setkI(double kI) {
        this.kI = kI;
    }
    /// P коэффициент
    double kP,
    /// D коэффициент
           kD,
    /// I коэффициент
           kI;
    /// Выходная мощность
    public double out;
    /// Таймер
    ElapsedTime timer = new ElapsedTime();
    /// установить погрешность
    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }
    /// получить текущее заданное значение
    public int getTarget() {
        return target;
    }
    /// установить заданное значение
    public void setTarget(int target) {
        this.target = target;
    }
    /// получить текущее значение
    public int getMeasured() {
        return measured;
    }
    /// установить текущее значение
    public void setMeasured(int measured) {
        this.measured = measured;
    }
    /// запуск потока для расчета PID-контроллера
    @Override
    public synchronized void start() {
        super.start();
        while (this.isInterrupted()){
            calcError();
            calculate(target, measured);
            timer.reset();
        }
    }
    /// остановка потока для расчета PID-контроллера
    public void stopCalc(){
        this.interrupt();
    }
    /// получить вывод
    public double getOut() {
        return out;
    }

    /// получить минимальную мощность
    public double getMinPower() {
        return minPower;
    }
    /// устнаовить минимальную мощность
    public void setMinPower(double minPower) {
        this.minPower = minPower;
    }
    /// получить максимальную мощность
    public double getMaxPower() {
        return maxPower;
    }
    /// устнаовить максимальную мощность
    public void setMaxPower(double maxPower) {
        this.maxPower = maxPower;
    }
    /// расчет вывода
    public void calculate(int target, int measured){
        // если в нужной позиции переводит соответствующую перемнную в истинну
        if (!(!(target - tolerance < measured) && !(target + tolerance > measured))){
            atTargetPos = true;
        }
        // если не в нужной позиции расчитываем выход
        if (!atTargetPos){
            // расчет D
            D = kD * ((error - lastError) / timer.seconds());
            // расчет P
            P = error * kP;
            // расчет iSum
            iSum = iSum + (error * timer.seconds());
            // если iSum превышает лимит то устанавливаем предел
            if (iSum > 100){
                iSum = 100;
            } else if (iSum < -100){
                iSum = -100;
            }
            // расчет I
            I = iSum * kI;
            // расчет выхода
            out = Range.clip( P + I + D, minPower, maxPower);
        }
    }
    /// расчет ошибки
    public void calcError(){
        // текущая ошибка
        error = target - measured;
        // прошлая ошибка
        lastError = error;
    }
}
