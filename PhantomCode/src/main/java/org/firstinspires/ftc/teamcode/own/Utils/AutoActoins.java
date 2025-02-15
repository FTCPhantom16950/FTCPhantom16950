package org.firstinspires.ftc.teamcode.own.Utils;

public abstract class AutoActoins extends Thread{
    @Override
    public void run() {
        super.run();
        parallelAction();
    }
    public abstract void parallelAction();
    public abstract void linearAction();
}
