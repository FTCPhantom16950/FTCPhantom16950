package org.firstinspires.ftc.teamcode.own.Utils;

import org.firstinspires.ftc.teamcode.FORTEST.CustomException;

public class PhMath {
    private static final double EPSILON = 1e-10;
    /**
     * @param targetAngle целевое значение угла поворота сервомотора в градусах
     * @param maxAngle    максимальный угол сервомотора
     * @return возвращает уровень мощности для сервомотора в диапазоне [-1; 1], которая позволяет
     * повернуться сервомотору на нужный угол, где -1 это 0 градусов, а 1 - максимальный угол
     */
    public static double fromDegreesToPower(double targetAngle, double maxAngle) {
        double resultPower;
        if (maxAngle <= EPSILON) {
            throw new CustomException("Максимальное должно быть больше 0");
        }
        if (targetAngle > maxAngle || targetAngle < 0) {
            throw new CustomException(String.format("Угол %.2f вне диапазона [0, %.2f]", targetAngle, maxAngle));
        }
        resultPower = targetAngle / (maxAngle / 2) - 1;
        return resultPower;
    }
}
