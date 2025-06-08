package org.firstinspires.ftc.teamcode.own.Utils.Regulators;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class PIDRegulator extends Thread {


    public PIDRegulator(PIDCofficients pidCofficients, PhantomOpMode phantomOpMode) {
        this.pidCofficients = pidCofficients;
        this.phantomOpMode = phantomOpMode;
    }

    ElapsedTime timer;
    private int target = 0, measured = 0, error = 0, lastError = 0;
    private double kP, kI, kD, P = 0, I = 0, D = 0, iSum, out = 0, minPower, maxPower;
    private PIDCofficients pidCofficients;
    private PhantomOpMode phantomOpMode;
    private DcMotorEx dcMotorEx;

    public int target() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public double minPower() {
        return minPower;
    }

    public void setMinPower(double minPower) {
        this.minPower = minPower;
    }

    public double maxPower() {
        return maxPower;
    }

    public void setMaxPower(double maxPower) {
        this.maxPower = maxPower;
    }

    public PIDCofficients pidCofficients() {
        return pidCofficients;
    }

    public void setPidCofficients(PIDCofficients pidCofficients) {
        this.pidCofficients = pidCofficients;
    }

    public DcMotorEx dcMotorEx() {
        return dcMotorEx;
    }

    public void setDcMotorEx(DcMotorEx dcMotorEx) {
        this.dcMotorEx = dcMotorEx;
    }

    private void play() {
        timer = new ElapsedTime();
        while (phantomOpMode.opModeIsActive()) {
            measured = dcMotorEx.getCurrentPosition();
            kI = pidCofficients.kI();
            kD = pidCofficients.kD();
            kP = pidCofficients.kP();
            calcError();
            calculate(target, measured);
            timer.reset();
        }
    }

    /// запуск потока для расчета PID-контроллера
    @Override
    public void run() {
        super.run();
        play();
    }

    /// остановка потока для расчета PID-контроллера
    public void stopCalc() {
        this.interrupt();
    }

    /// расчет вывода
    public void calculate(int target, int measured) {
        // расчет D
        D = kD * ((error - lastError) / timer.seconds());
        // расчет P
        P = error * kP;
        // расчет iSum
        iSum = iSum + (error * timer.seconds());
        // если iSum превышает лимит то устанавливаем предел
        if (iSum > 100) {
            iSum = 100;
        } else if (iSum < -100) {
            iSum = -100;
        }
        // расчет I
        I = iSum * kI;
        // расчет выхода
        out = Range.clip(P + I + D, minPower, maxPower);

    }

    /// расчет ошибки
    public void calcError() {
        // текущая ошибка
        error = target - measured;
        // прошлая ошибка
        lastError = error;
    }


}
