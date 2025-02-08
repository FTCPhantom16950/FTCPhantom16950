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
public  class Config {
    public static boolean AUTOMODE = false;
    public static boolean TELEOPIMU = false;
    public static boolean PEDROTELEOP = false;
    public static boolean ACTIONINWORK = false;
    public static double tolerance = 1;

    public boolean isAUTOMODE() {
        return AUTOMODE;
    }

    public static void setAUTOMODE(boolean AUTOMODE) {
        Config.AUTOMODE = AUTOMODE;
    }
}
