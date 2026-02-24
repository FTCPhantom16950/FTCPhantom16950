package org.firstinspires.ftc.teamcode.own.utils;




import org.firstinspires.ftc.teamcode.own.utils.states.OpModeStates;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Scheduler {
    private final Set<Mechanism> mechanismSet;
    private final Action action;

    private Scheduler(Builder builder) {
        this.mechanismSet = builder.mechanismSet;
        this.action = builder.action;
    }

    public void initMechanisms() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Callable<Boolean>> tasks = new ArrayList<>();
        synchronized (mechanismSet) {
            for (Mechanism mechanism : mechanismSet) {
                tasks.add(() -> {
                    mechanism.init();
                    return true;
                });
            }
        }

        List<Future<Boolean>> futures = executorService.invokeAll(tasks);
        for (Future<Boolean> future : futures){
            try {
                future.get();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        executorService.shutdown();

    }
    public void run() throws InterruptedException {
        if (action != null && Robot.INSTANCE.getRobotData("OpModeState", OpModeStates.class) == OpModeStates.ACTIVE){
            try {
                action.execute();
            } catch (InterruptedException e){
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
            if (mechanisms.isEmpty()){
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
