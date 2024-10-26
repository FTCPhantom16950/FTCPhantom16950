package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.own.Camera.Basement.PhantomProcessor;

public class PhantomMath {
    // создаём пременные для работы с другими классами
    public double x, y;
    public boolean leftPose, rightPose;
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

//TODO:НАЙТИ КАК ПРОПИСАТЬ АКСЕЛЕРОМЕТР ФТС

//    /**
//     * получаем расстояние, пройденное роботом и его скорость при помощи акселерометра
//     * @param hw HardwareMap
//     */
//  public void getCoordinatesByAccel(HardwareMap hw){
//        // получаем значения от акселерометра
//        phantomIMU.valueGetter();
//        Thread coordinateMath = new Thread(() -> {
//            while(true){
//                // стартуем таймер
//                timer.startTime();
//                // получаем данные от акселерометра и записываем во внутренние переменные
//                accelX = phantomIMU.aclX;
//                accelY = phantomIMU.aclY;
//                // получаем время на текущий момент
//                time = timer.time(TimeUnit.SECONDS);
//                // получаем скорость по x и y
//                vCurrentX = accelX * time + v0X;
//                time = timer.time(TimeUnit.SECONDS);
//                vCurrentY = accelY * time + v0Y;
//                time = timer.time(TimeUnit.SECONDS);
//                // получаем расстояние по формуле x = x0 + v0 * t + (a * t * t) / 2
//                x = x + v0X * time + (accelX * time * time) / 2;
//                time = timer.time(TimeUnit.SECONDS);
//                y = y + v0Y * time + (accelY * time * time) / 2;
//                time = timer.time(TimeUnit.SECONDS);
//                // сбрасываем таймер
//                timer.reset();
//                // передаем значения текущей скорости в стартовую
//                v0Y = vCurrentY;
//                v0X = vCurrentX;
//            }
//        });
//        coordinateMath.start();
//    }


}

