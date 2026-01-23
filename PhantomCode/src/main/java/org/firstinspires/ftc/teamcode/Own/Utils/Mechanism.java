package org.firstinspires.ftc.teamcode.Own.Utils;
/// Интерфейс для создания механизмов
/// Made by Hkial(Gleb)
/// Last Updated: 08.06.25 02:40
public interface Mechanism {
    void init() throws InterruptedException;
    default void read(){}
}
