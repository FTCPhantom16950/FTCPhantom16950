package org.firstinspires.ftc.teamcode.own.utils.actions;



import org.firstinspires.ftc.teamcode.own.utils.states.OpModeStates;
import org.firstinspires.ftc.teamcode.own.utils.Robot;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelGroup implements Action {
    /// список добавляемых действий
    private final List<Action> actions = new ArrayList<Action>();

    /**
     * Класс для добавления последовательных групп
     *
     * @param actions действия которые будут выполняться последовательно
     */
    public ParallelGroup(Action... actions) {
        this.actions.addAll(Arrays.asList(actions));
    }

    /// Метод выполнения действий последовательно
    @Override
    public void execute() throws InterruptedException {
        List<Callable<Void>> tasks = new ArrayList<>();
        List<Future<Void>> futures = new ArrayList<>();
        for (Action a : actions) {
            tasks.add(() -> {
                try {
                    a.execute();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            });
        }
        ExecutorService executorService = Executors.newFixedThreadPool(actions.size());
        for (Callable<Void> task : tasks) {
            futures.add(executorService.submit(task));
        }
        try {
            while (!(Robot.INSTANCE.getRobotData("OpModeState", OpModeStates.class) == OpModeStates.STOP)) {
                for (Future<Void> future : futures) {
                    if (future.isDone()) {
                        future.get();
                    }
                }
                if (Robot.INSTANCE.getRobotData("OpModeState", OpModeStates.class) == OpModeStates.STOP) {
                    break;
                }
            }
        } catch (ExecutionException e) {
            Objects.requireNonNull(e.getCause()).printStackTrace();
            throw new RuntimeException("Error in thread: " + e.getCause().getMessage(), e.getCause());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdownNow();
            tasks.clear();
            futures.clear();
        }
    }
}
