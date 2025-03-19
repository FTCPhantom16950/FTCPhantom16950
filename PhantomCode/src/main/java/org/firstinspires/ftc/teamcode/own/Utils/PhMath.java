package org.firstinspires.ftc.teamcode.own.Utils;

import androidx.annotation.Nullable;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.FORTEST.CustomExcpection;

public class PhMath extends Thread {
    @Override
    public synchronized void start() {
        super.start();

    }

    public static double fromDegreesToPower(double power, double max){
        if(power > max || power < 0){
            throw new CustomExcpection("Значение угла сервы: " + power + "больше чем максимальный угол: " + max);
        }
        double itogpower = 0;
        if(power/ (max / 2) == 0) {
            itogpower = -1;
        }else if (power / (max / 2) == 1) {
            itogpower = 0;
        } else if (power/ (max / 2) == 2) {
            itogpower = 1;
        } else{
            itogpower = Range.clip(power / (max / 2) - 1 , -1, 1);
        }
        return itogpower;
    }
}
