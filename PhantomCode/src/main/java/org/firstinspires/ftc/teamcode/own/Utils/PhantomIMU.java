package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class PhantomIMU {
    // Создаем перемнные для использования акселерометра и гироскопа
    public IMU imu;
    // создаем переменные для связи между классами и хранения данных
    public double x,y,heading;
    public double veloHead;
    // создаем переменные для внутренних классов данных, получаемых от иму
    private YawPitchRollAngles rotation;
    private AngularVelocity rotateSpeed;


    /**
     *  инициализация гироскопа и акселерометра
     * @param hwmap HardwareMap
     */
    public void initIMU(HardwareMap hwmap){
        imu = hwmap.get(IMU.class, "imu");
        // создаем настройки ориентации гироскопа для робота
        RevHubOrientationOnRobot hubOrientationOnRobot =
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP);
        // инициализируем гироскоп с параметрами установленными выше
        imu.initialize(new IMU.Parameters(hubOrientationOnRobot));

    }
    /**
     * получаем значения с акселерометра и гироскопа
     *
     */
    public void valueGetter(){
        // получаем скорость вращения
        rotation = imu.getRobotYawPitchRollAngles();
        rotateSpeed = imu.getRobotAngularVelocity(AngleUnit.DEGREES);
        // получаем текущий поворот робота
        heading = rotation.getYaw(AngleUnit.DEGREES);
        // получаем ускорение поворота
        veloHead = rotateSpeed.yRotationRate;
    }
    // сбрасываем угол поврота робота
    public void resetHeading(){
        imu.resetYaw();
    }
}
