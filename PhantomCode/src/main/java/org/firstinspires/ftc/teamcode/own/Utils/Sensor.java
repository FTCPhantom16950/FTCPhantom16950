package org.firstinspires.ftc.teamcode.own.Utils;

import java.util.List;
/**
Пытаюсь создать интерфейс для датчиков)
 */
public interface Sensor {
     abstract void init();
     abstract void play();
     abstract List<Double> get();
     abstract void set();
}
