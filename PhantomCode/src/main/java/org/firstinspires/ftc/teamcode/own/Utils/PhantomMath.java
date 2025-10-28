package org.firstinspires.ftc.teamcode.own.Utils;

//import org.psilynx.psikit.core.wpi.Pose2d;
//import org.psilynx.psikit.core.wpi.Rotation2d;

public class PhantomMath {
    public static final double EPSILON = 0.0f;
    public static double servoCRPowerToDegrees(double input,double max){
        double resultPower;
        if (max <= EPSILON) {
            throw new IllegalArgumentException("Максимальное должно быть больше 0");
        }
        if (input > max || input < 0) {
            throw new IllegalArgumentException(String.format("Угол %.2f вне диапазона [0, %.2f]", input, max));
        }
        resultPower = input / (max / 2) - 1;
        return resultPower;
    }
    public static double makeLinearToCubic(double input){
        return Math.pow(input, 3);
    }
    public static double makeLinearToCubic(double input, int a){
        return Math.pow(input, 3 * a);
    }
//    public static Pose2d convertCoordinatesToLogger(double x, double y, double rot){
//        return new Pose2d(144 - x, 144- y, new Rotation2d(rot));
//    }
    public static double xPedroToFtcX(double x){
        return 72 - x;
    }
    public static double yPedroToFtcY(double y){
        return y - 72;
    }
}
