package org.firstinspires.ftc.teamcode.own.Utils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Scheduler {
    private Set<Mechanism> mechanisms;
    private Actions actions;

    public static class Builder {
        private Set<Mechanism> mechanisms = new LinkedHashSet<Mechanism>();
        private Actions actions;

        public Builder addMechanisms(Set<Mechanism> mechanisms) {
            this.mechanisms.addAll(mechanisms);
            return this;
        }

        public Builder setAction(Actions action) {
            actions = action;
            return this;
        }

        public Scheduler build() {
            return new Scheduler(this);
        }
    }

    private Scheduler(Builder builder) {
        mechanisms = builder.mechanisms;
        actions = builder.actions;
    }

    public void initMechanism() {
        for (Mechanism mechanism :
                mechanisms) {
            mechanism.init();
        }
    }
    public void run(){
        actions.startAndPlay();
    }
}
