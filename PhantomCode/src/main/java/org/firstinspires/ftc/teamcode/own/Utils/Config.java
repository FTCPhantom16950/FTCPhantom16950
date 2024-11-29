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
public class Config {
    public boolean AUTOMODE = false;
    public boolean TELEOPIMU = false;

    public boolean isAUTOMODE() {
        return AUTOMODE;
    }

    public void setAUTOMODE(boolean AUTOMODE) {
        this.AUTOMODE = AUTOMODE;
    }
}
