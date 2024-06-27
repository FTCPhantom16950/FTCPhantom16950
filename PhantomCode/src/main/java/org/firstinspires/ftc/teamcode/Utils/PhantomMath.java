package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Camera.Basement.PhantomProcessor;
import org.firstinspires.ftc.teamcode.Mechanism.WheelBase;

import java.util.concurrent.TimeUnit;

public class PhantomMath {
    // создаем внутрении классы других механизмов
    PhantomIMU phantomIMU = new PhantomIMU();
    WheelBase wheelBase = new WheelBase();
    // создаём пременные для работы с другими классами
    public double x,y, vCurrentX, vCurrentY;
    public boolean leftPose, rightPose;
    public double resultForFOdo, resultForSOdo, resultForTOdo;
    // создаём внутренние перменные
    private double v0X,v0Y, time;
    private double accelX, accelY;
    private double countOffOdo, countOfsOdo, countOFtOdo;
    double headVelo;
    // создаём таймер
    private ElapsedTime timer = new ElapsedTime();

    /**
     * Перевод данных из цветовых значений в boolean
     * @param cameraReworked используемый у вас в автономе процессор
     */
    public void pipeLine(PhantomProcessor cameraReworked){
        // получаем данные из процессора
        int valLeft = cameraReworked.valLeft;
        int valRight = cameraReworked.valRight;
        // проверяем значения цвета, если больше половины то получаем true, если меньше то false
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

    /**
     * получаем расстояние, пройденное роботом и его скорость при помощи акселерометра
     * @param hw HardwareMap
     */
    public void getCoordinatesByAccel(HardwareMap hw){
        // получаем значения от акселерометра
        phantomIMU.valueGetter(hw);
        Thread coordinateMath = new Thread(() -> {
            while(true){
                // стартуем таймер
                timer.startTime();
                // получаем данные от акселерометра и записываем во внутренние переменные
                accelX = phantomIMU.aclX;
                accelY = phantomIMU.aclY;
                // получаем время на текущий момент
                time = timer.time(TimeUnit.SECONDS);
                // получаем скорость по x и y
                vCurrentX = accelX * time + v0X;
                time = timer.time(TimeUnit.SECONDS);
                vCurrentY = accelY * time + v0Y;
                time = timer.time(TimeUnit.SECONDS);
                // получаем расстояние по формуле x = x0 + v0 * t + (a * t * t) / 2
                x = x + v0X * time + (accelX * time * time) / 2;
                time = timer.time(TimeUnit.SECONDS);
                y = y + v0Y * time + (accelY * time * time) / 2;
                time = timer.time(TimeUnit.SECONDS);
                // сбрасываем таймер
                timer.reset();
                // передаем значения текущей скорости в стартовую
                v0Y = vCurrentY;
                v0X = vCurrentX;
            }
        });
        coordinateMath.start();
    }

    /**
     * перевод из оборотов одометра в градусы, получаем на выходе отношение: градус / оборот
     * @param hw HardwareMap
     */
    public void OdoToDegrees(HardwareMap hw){
        // получаем значения от
        phantomIMU.valueGetter(hw);
        // получаем число оборотов мертвых колес
        wheelBase.OdoCounter();
        // считываем значения для каждого мертвого колеса
        countOFtOdo = wheelBase.countTOdo;
        countOfsOdo = wheelBase.countSOdo;
        countOffOdo = wheelBase.countFOdo;
        // считываем значения скорости поворота в градусах
        headVelo = phantomIMU.veloHead;
        // расчитываем отношения градусов к оборотам мертвых колес
        resultForFOdo = headVelo / countOffOdo;
        resultForSOdo = headVelo / countOfsOdo;
        resultForTOdo = headVelo / countOFtOdo;
    }
}
