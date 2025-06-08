package org.firstinspires.ftc.teamcode.own.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/// Класс для добавления параллельных действий
/// Made by Hkial(Gleb)
/// Last Updated: 08.06.25 02:40
public class ParallelGroup extends Action {
    /// список добавляемых действий
    private List<Action> actions = new ArrayList<Action>();
    /// список для работающих потоков
    private final ExecutorService executorService;
    /// Сет необходимых механизмов
    private final Set<Mechanism> necessaryMechanisms = new HashSet<>();

    @Override
    public Set<Mechanism> getNecessaryMechanisms() {
        return Collections.unmodifiableSet(necessaryMechanisms);
    }

    /**
     * Собирает все необходимые механизмы из дочерних действий
     */
    private void collectNecessaryMechanisms() {
        for (Action action : actions) {
            necessaryMechanisms.addAll(action.getNecessaryMechanisms());
        }
    }
    /**
     * Класс для добавления параллельных групп
     * @param actions действия которые будут выполняться параллельно
     */
    public ParallelGroup(Action... actions) {
        this.actions.addAll(List.of(actions));
        this.executorService = Executors.newFixedThreadPool(actions.length);
        collectNecessaryMechanisms();
    }
    /// Метод выполнения действий параллельно
    @Override
    public void execute() {
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (Action action : actions) {
            futures.add(CompletableFuture.runAsync(action::execute, executorService));
        }

        // Ожидаем завершения всех задач
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        shutdownExecutor();
    }
    /**
     * Безопасное завершение работы пула потоков
     */
    private void shutdownExecutor() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
