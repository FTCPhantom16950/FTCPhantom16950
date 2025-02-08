package org.firstinspires.ftc.teamcode.own.Mechanism;

import org.firstinspires.ftc.teamcode.own.Utils.Sensor;

import java.util.Collections;
import java.util.List;

public class ColorSensors implements Sensor {
    Boolean isInited = null;
    Boolean isActionNow = null;
    Boolean isActive = null;
    Boolean isStopped = null;
    @Override
    public void init() {
        isInited = true;
    }

    @Override
    public void play() {
        isActionNow = true;
    }

    @Override
    public List<Double> get() {
        return Collections.emptyList();
    }

    @Override
    public void set() {

    }
}
