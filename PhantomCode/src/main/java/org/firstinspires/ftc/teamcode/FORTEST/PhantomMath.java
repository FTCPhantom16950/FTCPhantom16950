package org.firstinspires.ftc.teamcode.FORTEST;

import org.firstinspires.ftc.teamcode.own.Camera.Basement.PhantomProcessor;

/**
 * Класс для просчета математики используемой роботом
 */
public class PhantomMath {
    // создаём пременные для работы с другими классами
    public double x, y;
    public boolean leftPose, rightPose;
    double size;
    /**
     * Перевод данных из цветовых значений в boolean
     *
     * @param cameraReworked используемый у вас в автономе процессор
     */
    public void pipeLine(PhantomProcessor cameraReworked) {
        // получаем данные из процессора
        int valLeft = cameraReworked.getValLeft();
        int valRight = cameraReworked.getValRight();
        // проверяем значения цвета, если больше половины то получаем true, если меньше то false
        leftPose = valLeft >= 122;
        rightPose = valRight >= 122;
    }

    public double inchesToMM(double amount){
        size = amount * 2.54;
        return size;
    }
    public double mMToInches(double amount){
        size = amount / 2.54;
        return size;
    }






}

