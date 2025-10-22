package org.firstinspires.ftc.teamcode.own.Utils;


/**
 * Класс для конфигурации
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 02:40
 */

public class Config {
    public static final double
            SAMPLE_START_POWER = -1,
            KRUT_START_POWER = 0
    ;
    private Config() {
        throw new CustomException("НЕЛЬЗЯ СОЗДАВАТЬ ОБЪЕКТ CONFIG ИЛИ НАСЛЕДОВАТЬСЯ ОТ НЕГО.", new RuntimeException());
    }
}
