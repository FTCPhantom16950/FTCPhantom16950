package org.firstinspires.ftc.teamcode.own.Utils;

/**
 * класс для определения цвета
 */
public class Color {
    public enum ResultColor {
        NONE,
        RED,
        BLUE,
        YELLOW,
    }

    /**
     * @param r числовое значение красного канала в диапазоне [0;1]
     * @param g числовое значение зеленого канала в диапазоне [0;1]
     * @param b числовое значение синего канала в диапазоне [0;1]
     * @return возвращает enum для обнаруженного цвета
     */
    public ResultColor color(float r, float g, float b) {
        if ((r <= 0.05 && g <= 0.03 && b <= 0.02) && (r >= 0.02 && g >= 0.005 && b >= 0.005)) {
            return ResultColor.RED;
        } else if ((r <= 0.05 && g <= 0.06 && b <= 0.02) && (r >= 0.03 && g >= 0.03 && b >= 0.005)) {
            return ResultColor.YELLOW;
        } else if ((r <= 0.04 && g <= 0.13 && b <= 0.13) && (r >= 0.0025 && g >= 0.0035 && b >= 0.011)) {
            return ResultColor.BLUE;
        } else {
            return ResultColor.NONE;
        }
    }
}
