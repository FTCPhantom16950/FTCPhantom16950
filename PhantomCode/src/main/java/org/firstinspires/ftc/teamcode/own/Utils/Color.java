package org.firstinspires.ftc.teamcode.own.Utils;

/**
 * класс для распределения нормализованного цвета сэмпла
 */
public class Color {
    public String color(double r, double g, double b) {
        if ((r <= 0.05 && g <= 0.03 && b <= 0.02) && (r >= 0.02 && g >= 0.005 && b >= 0.005)) {
            return "RED";
        } else if ((r <= 0.05 && g <= 0.06 && b <= 0.02) && (r >= 0.03 && g >= 0.03 && b >= 0.005)) {
            return "YELLOW";
        } else if ((r <= 0.04 && g <= 0.13 && b <= 0.13) && (r >= 0.0025 && g >= 0.0035 && b >= 0.011)) {
            return "BLUE";
        } else {
            return "NONE";
        }
    }
}
