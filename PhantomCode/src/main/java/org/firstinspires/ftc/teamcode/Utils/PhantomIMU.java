package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class PhantomIMU {
    private IMU imu;
    public double x,y,z,heading;
    public void initIMU(HardwareMap hwmap){
        imu = hwmap.get(IMU.class, "imu");
        RevHubOrientationOnRobot hubOrientationOnRobot =
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD);

        imu.initialize(new IMU.Parameters(hubOrientationOnRobot));
    }
    // getters and setters for x y z and heading
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getZ() {
        return z;
    }
    public void setZ(double z) {
        this.z = z;
    }
    public double getHeading() {
        return heading;
    }
    public void setHeading(double heading) {
        this.heading = heading;
    }

}
