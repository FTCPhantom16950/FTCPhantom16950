package org.firstinspires.ftc.teamcode.own.Utils;

@com.acmerobotics.dashboard.config.Config
/**
 * конфиг для дэшборда
 */
public class Config {
    public static double k_p = 1;
    public static double k_i = 1;
    public static double k_d = 1;
    public double k_f = 0;
    public double power = 0;
    public double distance = 0;
    public String right_front = "rf";
    public String left_front = "lf";
    public String right_back = "rb";
    public String left_back = "lb";
    public static double gainK = 1;
    public static double measurementNoiseVariance = 0.1;
    public static double processNoiseVariance = 1;
}
