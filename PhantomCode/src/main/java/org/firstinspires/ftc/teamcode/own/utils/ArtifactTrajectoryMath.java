package org.firstinspires.ftc.teamcode.own.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ArtifactTrajectoryMath {
    public static final double EPSILON = 0.0f;

    public double[] calculateFly(double[] speedRange, double[] angelRange, double distance, double height, double mass, double g, double cd, double s, double p, double massSpin) {
        double[] res = new double[2];
        double dt = 0.01;
        List<double[]> minSpeedX = new ArrayList<>();
        int step0 = 0;
        for (double speed = speedRange[0]; speed <= speedRange[1]; speed += 100) {
            int step1 = 0;
            double currentSpeed = Math.sqrt(massSpin / mass) * speed;
            for (double j = angelRange[0]; j <= angelRange[1]; j += 5) {
                double currentAngel = Math.toRadians(j);
                double currentSpeedX = currentSpeed * Math.cos(currentAngel), currentSpeedY = currentSpeed * Math.sin(currentAngel);

                double travelledDistance = 0;
                double currentHeight = 0;
                while (!(travelledDistance >= distance) && (travelledDistance <= distance + 0.35)) {
                    double Fc = (cd * Math.pow(currentSpeed, 2) * p * s) / 2;

                    double ax = (Fc * Math.cos(currentAngel)) / mass;
                    double ay = (Fc * Math.sin(currentAngel) + mass * g) / mass;

                    double dx = currentSpeedX * dt - ax * Math.pow(dt, 2) / 2.0;
                    double dy = currentSpeedY * dt - ay * Math.pow(dt, 2) / 2.0;

                    travelledDistance = travelledDistance + dx;
                    currentHeight = currentHeight + dy;

                    currentSpeedX = currentSpeed * Math.cos(currentAngel) - ax * dt;
                    currentSpeedY = currentSpeed * Math.sin(currentAngel) - ay * dt;

                    currentSpeed = Math.sqrt(currentSpeedX * currentSpeedX + currentSpeedY * currentSpeedY);
                    currentAngel = Math.atan(currentSpeedY / currentSpeedX);
                }
                if ((travelledDistance >= distance) && (currentHeight <= height) && (travelledDistance <= distance + 0.35 )&& (currentHeight >= 0.75)) {
                    minSpeedX.add(new double[]{speed,j,currentSpeedX});
                }
                step1 += 1;
            }
            step0 += 1;
        }
        double[] minParams = new double[]{0,0,1000000000};
        for (double[] speeds : minSpeedX){
            if (speeds[2] < minParams[2]){
                minParams = speeds.clone();
            }
        }
        res[0] = minParams[0];
        res[1] = minParams[1];
        return res;
    }
}
