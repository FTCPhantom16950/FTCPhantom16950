package org.firstinspires.ftc.teamcode.own.Utils;


import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс хранящий основные переменные OpMode, хранящиеся статично
 */
public class Robot {
    public static DcMotorEx lf, rb, lb, rf;
    /// Используемый {@link PhantomOpMode }
    public static PhantomOpMode opMode;
    /// Используемый HardwareMap
    public static HardwareMap hw;
    /// Используемый Telemetry
    public static Telemetry telemetry;
    /// Используемый геймпад(gamepad1)
    public static Gamepad gamepadDriver,
    /// Используемый геймпад (gamepad2)
    gamepadOperator;

    private static final Map<String, Object> customObjects = new HashMap<>();

    /**
     * Добавляет или обновляет кастомный объект в статичном хранилище
     * @param key    ключ для доступа к объекту
     * @param object объект для хранения
     */
    public static void addOrUpdate(String key, Object object) {
        customObjects.put(key, object);
    }


    public static <T> T get(String name, Class<T> type) {
        return type.cast(customObjects.get(name));
    }

}
