package org.firstinspires.ftc.teamcode.own.Exception;
///Класс ошибки, выдается в слуяае отсоединения одного из хабов
public class HubDisconnectionError extends RuntimeException {
    public HubDisconnectionError() {
        super("Отключился один из хабов, скорее всего экспеншен");
    }
}
