package org.firstinspires.ftc.teamcode.own.utils;


import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class Scheduler {
    private final Set<Mechanism> mechanismSet;
    private final Action action;

    private Scheduler(Builder builder) {
        this.mechanismSet = builder.mechanismSet;
        this.action = builder.action;
    }

    public void initMechanisms(){
        for (Mechanism mechanism : mechanismSet) {
            try {
                mechanism.init();
            } catch (RuntimeException | InterruptedException e) {
                throw new RuntimeException("Error in Mechanism: " + e + " " + mechanism.getClass().getSimpleName());
            }
        }
    }

    public void run() {
        if (!Thread.currentThread().isInterrupted()) {
            try {
                action.execute();
            } catch (RuntimeException | InterruptedException e) {
                throw new RuntimeException("Error in Action: " + e + " " + action.getClass().getSimpleName());
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
                throw new NullPointerException();
            }
            mechanismSet.addAll(mechanisms);
            return this;
        }

        public Builder setAction(Action action) throws InterruptedException {
            if (action == null) throw new NullPointerException();
            this.action = action;
            return this;
        }


        public Scheduler build() {
            return new Scheduler(this);
        }

    }
}
