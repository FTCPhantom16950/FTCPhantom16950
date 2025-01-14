package org.firstinspires.ftc.teamcode.own.Utils;

public class Color {
    public String color(double r, double g, double b){
        if((r <= 1 && g >= 0 && b >= 0) && (r >=0.22 && b <= 0.19 && g<= 0.18)) {
            return "RED";
        } else if ((r <= 1 && g <= 1 && b >= 0) && (r >= 0.26 && g >= 0.36 && b <= 0.17)) {
            return "YELLOW";
        } else if ((r >= 0 && g >= 0 && b <= 1) && (r <= 0.09 && g <= 0.19 && b >= 0.26)) {
            return "BLUE";
        } else{
            return "NONE";
        }
    }
}
