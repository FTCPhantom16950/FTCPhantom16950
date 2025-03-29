package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.FORTEST.CustomExcpection;

public class PhMath extends Thread {
    @Override
    public synchronized void start() {
        super.start();
    }

    public static double fromDegreesToPower(double power, double max) {
        if (power > max || power < 0) {
            throw new CustomExcpection("Значение угла сервы: " + power + "больше чем максимальный угол: " + max);
        }
        double resultPower = 0;
        if (power / (max / 2) == 0) {
            resultPower = -1;
        } else if (power / (max / 2) == 1) {
            resultPower = 0;
        } else if (power / (max / 2) == 2) {
            resultPower = 1;
        } else {
            resultPower = Range.clip(power / (max / 2) - 1, -1, 1);
        }
        return resultPower;
    }
}
