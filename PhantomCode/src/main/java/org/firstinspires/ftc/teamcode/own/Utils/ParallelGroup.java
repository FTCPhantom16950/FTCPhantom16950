package org.firstinspires.ftc.teamcode.own.Utils;

import java.util.ArrayList;
import java.util.List;

public class ParallelGroup extends Actions {
    private List<Actions> actions = new ArrayList<Actions>();
    private List<Thread> threads = new ArrayList<Thread>();

    public ParallelGroup(Actions... actions) {
        this.actions.addAll(List.of(actions));
        for (Actions a :
                this.actions) {
            necessaryMechanisms.addAll(a.necessaryMechanisms);
        }
    }

    @Override
    public void startAndPlay() {
        for (Actions a : actions) {
            Thread thread = new Thread(a::startAndPlay);
            thread.start();
            threads.add(thread);
        }
        for (Thread t : threads) {
            while (t.isAlive()) {
                System.out.println("Waiting thread");
            }
        }
    }
}
