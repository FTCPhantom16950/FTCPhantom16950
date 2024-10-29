package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
///Интерфейс для всех механизмов, кроме колесной базы
public interface Mechanism {
    //Инициализация мотороа/серв/прочего
    void init(HardwareMap hw);
    //геймпады и кнопки, которые будут использоваться
    void gamepads(Gamepad gamepad);
    //код для теста
    void test();
    // код для использования
    void run();
}
