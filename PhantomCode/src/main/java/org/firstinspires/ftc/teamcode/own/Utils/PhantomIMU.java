package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.AccelerationSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class PhantomIMU {
    // Создаем перемнные для использования акселерометра и гироскопа
    public IMU imu;
    AccelerationSensor accelerationSensor;
    // создаем переменные для связи между классами и хранения данных
    public double x,y,z,heading;
    public double veloHead, aclX, aclY;
    // создаем переменные для внутренних классов данных, получаемых от иму
    private YawPitchRollAngles rotation;
    private AngularVelocity rotateSpeed;
    Acceleration acceleration;

    /**
     *  инициализация гироскопа и акселерометра
     * @param hwmap HardwareMap
     */
    public void initIMU(HardwareMap hwmap){
        //привязываем акслерометр и гироскоп к конфигу
   //     accelerationSensor = (AccelerationSensor) hwmap.get(IMU.class, "imu");
        imu = hwmap.get(IMU.class, "imu");
        // создаем настройки ориентации гироскопа для робота
        RevHubOrientationOnRobot hubOrientationOnRobot =
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD);
        // инициализируем гироскоп с параметрами установленными выше
        imu.initialize(new IMU.Parameters(hubOrientationOnRobot));

    }

    /**
     * получаем значения с акселерометра и гироскопа
     * @param hw HardwareMap
     */
    public void valueGetter(HardwareMap hw){
        Thread imuT = new Thread(() -> {
            while (true){
                // получаем ускорение по осям x и y
  //              acceleration = accelerationSensor.getAcceleration();
                aclX = acceleration.xAccel;
                aclY = acceleration.yAccel;
                // получаем скорость вращения
                rotation = imu.getRobotYawPitchRollAngles();
                rotateSpeed = imu.getRobotAngularVelocity(AngleUnit.DEGREES);
                // получаем текущий поворот робота
                heading = rotation.getYaw(AngleUnit.DEGREES);
                // получаем ускорение поворота
                veloHead = rotateSpeed.yRotationRate;

            }
        });
        imuT.start();
    }
    // сбрасываем угол поврота робота
    public void resetHeading(){
        imu.resetYaw();
    }
}
