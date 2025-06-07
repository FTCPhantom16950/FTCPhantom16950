package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;

import java.util.HashSet;
import java.util.Set;

public abstract class PhantomOpMode extends OpMode {
    public String name = "Default";
    public OpModeMeta.Flavor flavor = OpModeMeta.Flavor.TELEOP;
    public String group = "default";
    public Actions action;
    private Scheduler scheduler;

    public String getName() {
        return name;
    }

    public OpModeMeta.Flavor getFlavor() {
        return flavor;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public void init() {
        scheduler = new Scheduler.Builder()
                .setAction(action)
                .addMechanisms(
                        findNecessaryMechanisms(action)
                )
                .build();
        scheduler.initMechanism();
    }

    @Override
    public void start() {
        super.start();
        scheduler.run();
    }

    @Override
    public void loop() {

    }

    public abstract void runOpMode();

    private Set<Mechanism> findNecessaryMechanisms(Actions actionsAll){
        return new HashSet<>(actionsAll.necessaryMechanisms);
    }
}
