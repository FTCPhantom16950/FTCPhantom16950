package org.firstinspires.ftc.teamcode.own.Utils;

public class Color {
    public String color(double r, double g, double b){
        if((r <= 1 && g >= 0 && b >= 0) && (r >=0.3 && b <= 0.3 && g<= 0.3)) {
            return "RED";
        } else if ((r <= 1 && g <= 1 && b >= 0) && (r >= 0.58 && g >= 0.7 && b <= 0.4)) {
            return "YELLOW";
        } else if ((r >= 0 && g >= 0 && b <= 1) && (r <= 0.318 && g <= 0.518 && b >= 0.4)) {
            return "BLUE";
        } else{
            return "NONE";
        }
    }
}
