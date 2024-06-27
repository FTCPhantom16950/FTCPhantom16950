package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Camera.Basement.PhantomProcessor;

import java.util.concurrent.TimeUnit;

public class PhantomMath {
    public double x,y;
    PhantomIMU phantomIMU = new PhantomIMU();
    public boolean leftPose, rightPose;
    private double v0X,v0Y, vCurrentX, vCurrentY, time;
    private double accelX, accelY;
    private ElapsedTime timer = new ElapsedTime();
    public void pipeLine(PhantomProcessor cameraReworked){
        int valLeft = cameraReworked.valLeft;
        int valRight = cameraReworked.valRight;
        if (valLeft >= 122){
            leftPose = true;
        } else {
            leftPose = false;
        }
        if (valRight >= 122){
            rightPose = true;
        } else {
            rightPose = false;
        }
    }
    // ускорение в мм/сек
    public void getCoordinatesByAccel(HardwareMap hw){
        phantomIMU.headingGetter(hw);
        Thread coordinateMath = new Thread(() -> {
            while(true){
                timer.startTime();
                accelX = phantomIMU.aclX;
                accelY = phantomIMU.aclY;
                time = timer.time(TimeUnit.SECONDS);
                vCurrentX = accelX * time + v0X;
                time = timer.time(TimeUnit.SECONDS);
                vCurrentY = accelY * time + v0Y;
                time = timer.time(TimeUnit.SECONDS);
                x = x + v0X * time + (accelX * time * time) / 2;
                time = timer.time(TimeUnit.SECONDS);
                y = y + v0Y * time + (accelY * time * time) / 2;
                time = timer.time(TimeUnit.SECONDS);
                timer.reset();
                v0Y = vCurrentY;
                v0X = vCurrentX;
            }
        });
        coordinateMath.start();
    }
}
