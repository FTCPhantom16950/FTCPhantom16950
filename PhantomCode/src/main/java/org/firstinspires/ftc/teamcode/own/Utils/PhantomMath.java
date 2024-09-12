package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.own.Camera.Basement.PhantomProcessor;

import java.util.concurrent.TimeUnit;

public class PhantomMath {
    LinearOpMode opMode;

    public PhantomMath(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    // создаем внутрении классы других механизмов
    PhantomIMU phantomIMU = new PhantomIMU();

    // создаём пременные для работы с другими классами
    public double x, y, vCurrentX, vCurrentY;
    public boolean leftPose, rightPose;
    public double resultForFOdo, resultForSOdo, resultForTOdo;
    // создаём внутренние перменные
    private double v0X, v0Y, time;
    private double accelX, accelY;
    private double countOffOdo, countOfsOdo, countOFtOdo;
    double headVelo;
    // создаём таймер
    private final ElapsedTime timer = new ElapsedTime();

    /**
     * Перевод данных из цветовых значений в boolean
     *
     * @param cameraReworked используемый у вас в автономе процессор
     */
    public void pipeLine(PhantomProcessor cameraReworked) {
        // получаем данные из процессора
        int valLeft = cameraReworked.valLeft;
        int valRight = cameraReworked.valRight;
        // проверяем значения цвета, если больше половины то получаем true, если меньше то false
        leftPose = valLeft >= 122;
        rightPose = valRight >= 122;
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


}

