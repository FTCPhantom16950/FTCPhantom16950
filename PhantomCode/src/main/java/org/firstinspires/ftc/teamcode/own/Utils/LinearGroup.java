package org.firstinspires.ftc.teamcode.own.Utils;

import java.util.ArrayList;
import java.util.List;

public class LinearGroup extends Actions {
    private List<Actions> actions = new ArrayList<Actions>();

    public LinearGroup(Actions... actions) {
        this.actions.addAll(List.of(actions));
        for (Actions a :
                this.actions) {
            necessaryMechanisms.addAll(a.necessaryMechanisms);
        }
    }

    @Override
    public void startAndPlay() {
        for (Actions a : actions) {
            a.startAndPlay();
        }
    }
}
