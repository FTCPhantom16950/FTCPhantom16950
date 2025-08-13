package org.firstinspires.ftc.teamcode.own.Utils;


import org.firstinspires.ftc.teamcode.own.OpModes.GameBased;

/**
 * Класс для конфигурации
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 02:40
 */
@com.acmerobotics.dashboard.config.Config
public class Config {
    private Config() {
        throw new CustomException("НЕЛЬЗЯ СОЗДАВАТЬ ОБЪЕКТ CONFIG ИЛИ НАСЛЕДОВАТЬСЯ ОТ НЕГО.", new RuntimeException());
    }
}
