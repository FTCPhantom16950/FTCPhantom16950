package org.firstinspires.ftc.teamcode.own.utils;


import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.states.OpModeStates;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Scheduler {
    private final Set<Mechanism> mechanismSet;
    private final Action action;

    private Scheduler(Builder builder) {
        this.mechanismSet = builder.mechanismSet;
        this.action = builder.action;
    }

    public void initMechanisms() throws InterruptedException {
        for (Mechanism mechanism : mechanismSet) {
            try{
                mechanism.init();
            } catch (InterruptedException e){
                throw new InterruptedException();
            }
        }
    }

    public void run() throws InterruptedException {
        if (!Thread.currentThread().isInterrupted()) {
            try {
                action.execute();
            } catch (InterruptedException e) {
                throw e;
            }
        }
    }

    protected static class Builder {
        private final Set<Mechanism> mechanismSet = Collections.synchronizedSet(new HashSet<>());
        private Action action;

        public Builder addMechanism(Mechanism mechanism) {
            mechanismSet.add(mechanism);
            return this;
        }

        public Builder addMechanisms(Set<Mechanism> mechanisms) throws InterruptedException {
            if (mechanisms.isEmpty()) {
                throw new InterruptedException();
            }
            mechanismSet.addAll(mechanisms);
            return this;
        }

        public Builder setAction(Action action) throws InterruptedException {
            if (action == null) throw new InterruptedException();
            this.action = action;
            return this;
        }


        public Scheduler build() {
            return new Scheduler(this);
        }

    }
}
