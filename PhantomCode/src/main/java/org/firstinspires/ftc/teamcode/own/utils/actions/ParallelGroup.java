package org.firstinspires.ftc.teamcode.own.utils.actions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelGroup implements Action {
    private final List<Action> actions = new ArrayList<>();

    public ParallelGroup(Action... actions) {
        this.actions.addAll(Arrays.asList(actions));
    }

    @Override
    public void execute() throws InterruptedException {

        List<Callable<Void>> tasks = new ArrayList<>();
        for (Action a : actions) {
            tasks.add(() -> {
                a.execute();
                return null;
            });
        }

        ExecutorService executorService = Executors.newCachedThreadPool();
        try {

            List<Future<Void>> futures = executorService.invokeAll(tasks);
            for (Future<Void> future : futures) {
                future.get();
            }
        } catch (ExecutionException e) {
            throw new RuntimeException("Ошибка в параллельной группе: " + e.getCause().getMessage(), e.getCause());
        } finally {
            executorService.shutdownNow();
            actions.clear();
        }
    }
}
