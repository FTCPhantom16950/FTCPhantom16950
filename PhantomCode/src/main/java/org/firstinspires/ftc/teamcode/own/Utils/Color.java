package org.firstinspires.ftc.teamcode.own.Utils;

/**
 * класс для распределения нормализованного цвета сэмпла
 */
public class Color {
    public String color(double r, double g, double b){
        if((r <= 0.5 && g <= 0.61 && b <= 0.17) && (r >= 0.2 && g >= 0.19 && b >= 0.12) ) {
            return "RED";
        } else if ((r <= 1 && g <= 1 && b <= 1) && (r >= 0.5 && g >= 0.61 && b >= 0.17)) {
            return "YELLOW";
        } else if ((r <= 1 && g <= 1 && b <= 1) && (r >= 0.09 && g >= 0.17 && b >= 0.31)) {
            return "BLUE";
        } else{
            return "NONE";
        }
    }
}
