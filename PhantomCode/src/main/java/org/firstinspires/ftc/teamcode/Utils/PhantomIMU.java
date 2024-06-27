package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.AccelerationSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class PhantomIMU {
    private IMU imu;
    public double x,y,z,heading;
    public double aclhead, aclX, aclY;
    private YawPitchRollAngles rotation;
    private AngularVelocity rotateSpeed;
    AccelerationSensor accelerationSensor;
    Acceleration acceleration;
    public void initIMU(HardwareMap hwmap){
        accelerationSensor = hwmap.get(AccelerationSensor.class, "imu");
        imu = hwmap.get(IMU.class, "imu");
        RevHubOrientationOnRobot hubOrientationOnRobot =
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD);
        imu.initialize(new IMU.Parameters(hubOrientationOnRobot));
    }

    public void headingGetter(HardwareMap hw){
        initIMU(hw);
        Thread imuT = new Thread(() -> {
            while (true){
                acceleration = accelerationSensor.getAcceleration();
                aclX = acceleration.xAccel;
                aclY = acceleration.yAccel;
                rotation = imu.getRobotYawPitchRollAngles();
                rotateSpeed = imu.getRobotAngularVelocity(AngleUnit.DEGREES);
                heading = rotation.getYaw(AngleUnit.DEGREES);
                aclhead = rotateSpeed.yRotationRate;
            }
        });
        imuT.start();

    }
    public void resetHeading(){
        imu.resetYaw();
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


}
