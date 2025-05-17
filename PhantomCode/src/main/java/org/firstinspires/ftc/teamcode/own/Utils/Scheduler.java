package org.firstinspires.ftc.teamcode.own.Utils;

import java.util.List;
import java.util.Set;

public class Scheduler {
    private Set<Mechanism> mechanisms;
    private List<Actions> actions;

    public static class Builder {
        private Set<Mechanism> mechanisms;
        private List<Actions> actions;

        public Builder addMechanisms(Mechanism... mechanisms) {
            this.mechanisms.addAll(Set.of(mechanisms));
            return this;
        }

        public Builder addActions(Actions... actions) {
            this.actions.addAll(List.of(actions));
            return this;
        }
        public Scheduler build(){
            return new Scheduler(this);
        }
    }

    private Scheduler(Builder builder){
        mechanisms = builder.mechanisms;
        actions = builder.actions;
    }

}
