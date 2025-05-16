package org.firstinspires.ftc.teamcode.own.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scheduler {
    private List<Mechanism> initedMechanisms;
    private List<Actions> actions;

    public List<Mechanism> getInitedMechanisms() {
        return initedMechanisms;
    }

    public List<Actions> getActions() {
        return actions;
    }

    public Scheduler(Actions... actions) {
        this.actions = Arrays.asList(actions);
    }
    public void comparison(){

    }
    public class MechanismInit {
        private List<Mechanism> mechanisms;

        MechanismInit(Mechanism... mechanisms) {
            this.mechanisms = Arrays.asList(mechanisms);
        }

        public void init() {
            for (Mechanism mechanism : mechanisms) {
                boolean init = mechanism.init();
                if (init) {
                    initedMechanisms.add(mechanism);
                }
            }
        }
    }
}
