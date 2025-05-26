package org.firstinspires.ftc.teamcode.own.Utils;

public abstract class Actions {
    public abstract void play();

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
