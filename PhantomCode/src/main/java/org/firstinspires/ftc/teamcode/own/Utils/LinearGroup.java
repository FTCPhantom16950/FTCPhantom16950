package org.firstinspires.ftc.teamcode.own.Utils;

import java.util.List;

public class LinearGroup extends Actions {
    private List<Actions> actions;

    public LinearGroup(Actions... actions) {
        this.actions.addAll(List.of(actions));
    }

    @Override
    public void play() {
        for (Actions a : actions) {
            a.play();
        }
    }


}
