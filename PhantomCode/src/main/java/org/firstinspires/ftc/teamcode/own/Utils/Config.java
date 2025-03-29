package org.firstinspires.ftc.teamcode.own.Utils;
/*
    кнопки занятые на втором геймпаде rb, y, a
        |-----|                 |-----|
        | -lt |                 | -rt |
        |_____|                 |_____|
        | -lb |                 | -rb |
        |_____________________________|
        /                               \
       /     |___|                        \
       |     |-du|                -y       |
       |  ___|___|___                      |
       | |-dl|   |-dr|        -x       -b  |
       |  ---|---|---                      |
       |     |-dd|                -a       |
       |     |---|                         |
       |_____-ls                  -rs_____|
 */

@com.acmerobotics.dashboard.config.Config
/**
 * класс конфига для настройки из FTCDashboard
 */
public class Config {
    /// включает движение в Телеопе относительно центра поля
    public static boolean teleOpIMU = false;
    /// включает движение в Телеопе при помощи библиотеки PedroPathing
    public static boolean pedroTeleOp = false;
    /// хранит выполняется ли действие в данный момент времени
    public static boolean actionInWork = false;
    /// погрешность для передвижения в автономном периоде (в дюймах)
    public static double tolerance = 0.7;
}
