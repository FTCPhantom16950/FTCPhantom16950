package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Exception.HubDisconnectionError;

import java.util.List;
/// Класс для работы с Lynx модулем, отвечающим за связь между моторами/контроллерами/датчиками и т.п
public class LynxModule {
    public void init_Lynx(HardwareMap hardwareMap){
        try {
            // Создаем список всех механизмов связанных с Lynx модулем
            List<com.qualcomm.hardware.lynx.LynxModule> allHubs = hardwareMap.getAll(com.qualcomm.hardware.lynx.LynxModule.class);
            // Для каждого из них устанавливаем авто режим
            /*
            РЕЖИМЫ:
            OFF - Самый медленный режим, стоит по умолчанию, каждое взаимодействие обрабатывается отдельлно, не использует кэш
            AUTO - Автоматически очищает кэш и считывает данные, может совершать больше считываний чем нужно
            MANUAl - НЕ ИСПОЛЬЗОВАТЬ, РУЧНОЕ УПРАВЛЕНИЕ КЭШОМ
                         */
            for (com.qualcomm.hardware.lynx.LynxModule hub : allHubs){
                hub.setBulkCachingMode(com.qualcomm.hardware.lynx.LynxModule.BulkCachingMode.AUTO);
            }
        } catch (Exception e){
            //Выдастся если мы потеряем хаб
            throw new HubDisconnectionError();
        }


    }
}
