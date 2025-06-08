package org.firstinspires.ftc.teamcode.own.Utils;

import org.firstinspires.ftc.teamcode.own.OpModes.TestOpMode;

/**
 * Класс для конфигурации
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 02:40
 */
@com.acmerobotics.dashboard.config.Config
public class Config {
    /// Список необходимых OpMode
    public static PhantomOpMode[] opModes = new PhantomOpMode[]{new TestOpMode().runOpMode()};
}
