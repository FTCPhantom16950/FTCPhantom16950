package org.firstinspires.ftc.teamcode.own.Utils;
/// Кастомная ошибка
/// Made by Hkial(Gleb)
/// Last Updated: 08.06.25 02:40
public class CustomException extends RuntimeException {
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
