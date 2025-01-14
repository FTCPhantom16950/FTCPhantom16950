package org.firstinspires.ftc.teamcode.own.Utils;

public class Color {
    public String color(double r, double g, double b){
        if((r <= 1 && g >= 0 && b >= 0) && (r >=0.19 && b <= 0.15 && g<= 0.2)) {
            return "RED";
        } else if ((r <= 1 && g <= 1 && b >= 0) && (r >= 0.26 && g >= 0.36 && b <= 0.17)) {
            return "YELLOW";
        } else if ((r >= 0 && g >= 0 && b <= 1) && (r <= 0.1 && g <= 0.24 && b >= 0.2)) {
            return "BLUE";
        } else{
            return "NONE";
        }
    }
}
