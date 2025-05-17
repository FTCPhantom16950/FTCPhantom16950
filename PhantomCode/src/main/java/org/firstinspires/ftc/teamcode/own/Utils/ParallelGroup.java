package org.firstinspires.ftc.teamcode.own.Utils;

import java.util.List;

public class ParallelGroup extends Actions{
    private List<Actions> actions;
    private List<Thread> threads;

    public ParallelGroup(Actions... actions) {
        this.actions.addAll(List.of(actions));
    }

    @Override
    public void play() {
        for (Actions a : actions){
            Thread thread = new Thread(a::play);
            thread.start();
            threads.add(thread);
        }
        for (Thread t : threads){
            while(t.isAlive()){
                System.out.println("Waiting thread");
            }
        }
    }
}
