package org.firstinspires.ftc.teamcode.own.Exception;
///Класс ошибки, выдается в слуяае пустого вижн портала на старте
public class VisionPortalNullException extends RuntimeException {
    public VisionPortalNullException() {
        super("Вижн портал выключился, а он нужен");
    }
}
