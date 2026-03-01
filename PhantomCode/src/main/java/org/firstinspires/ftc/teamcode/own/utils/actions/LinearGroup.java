package org.firstinspires.ftc.teamcode.own.utils.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LinearGroup implements Action {
    /// список добавляемых действий
    private final List<Action> actions = new ArrayList<Action>();

    /**
     * Класс для добавления последовательных групп
     *
     * @param actions действия которые будут выполняться последовательно
     */
    public LinearGroup(Action... actions) {
        this.actions.addAll(Arrays.asList(actions));
    }

    /// Метод выполнения действий последовательно
    @Override
    public void execute() throws InterruptedException {
        for (Action a : actions) {
            try {
                a.execute();
            } catch ( RuntimeException | InterruptedException  e){
                throw new RuntimeException("Error in Action: " + e.getCause().getMessage(), e.getCause());
            }
        }
        actions.clear();
    }
}
