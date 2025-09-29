package org.firstinspires.ftc.teamcode.own.Utils;

public class PhantomMath {
    public static double makeLinearToCubic(double input){
        return Math.pow(input, 3);
    }
    public static double makeLinearToCubic(double input, int a){
        return Math.pow(input, 3 * a);
    }
}
