package org.firstinspires.ftc.teamcode.own.Utils;

public abstract class AutoActions extends Thread{
    // You must write here a number of path-chain with which you will use this action
    public int pathNumber = 0;

    /**
     * Creating an action for an autonomous
     * @param pathNumber with which pathchain will be used your action
     */
    public AutoActions(int pathNumber) {
        this.pathNumber = pathNumber;
    }
    // running a thread
    @Override
    public void run() {
        super.run();
        parallelAction();
    }
    public abstract void parallelAction();
    public abstract void linearAction();
}
