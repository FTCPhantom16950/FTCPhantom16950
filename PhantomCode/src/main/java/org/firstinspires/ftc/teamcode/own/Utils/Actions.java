package org.firstinspires.ftc.teamcode.own.Utils;

import java.util.HashSet;
import java.util.Set;

public abstract class Actions {
    public Set<Mechanism> necessaryMechanisms = new HashSet<>();
    public abstract void startAndPlay();

    public boolean sleep(long ms) {
        try {
            wait(ms);
        } catch (InterruptedException e) {
            throw new CustomException("stopped while sleep");
        }
        return true;
    }

    public boolean sleep(int ms) {
        try {
            wait(ms);
        } catch (InterruptedException e) {
            throw new CustomException("stopped while sleep");
        }
        return true;
    }
}
