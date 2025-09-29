package org.firstinspires.ftc.teamcode.own.Utils.Action.Groups;


import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

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
public class ParallelGroup extends Group {
    /// список добавляемых действий
    private final List<Action> actions = new ArrayList<Action>();
    private final List<Thread> threads = new ArrayList<>();

    /**
     * Класс для добавления последовательных групп
     *
     * @param actions действия которые будут выполняться последовательно
     */
    public ParallelGroup(PhantomOpMode phantomOpMode, Action... actions) {
        super(phantomOpMode);
        this.actions.addAll(List.of(actions));
    }

    /// Метод выполнения действий последовательно
    @Override
    public void execute() {
        for (Action a :
                actions) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    a.execute();
                }
            };
            threads.add(thread);
            thread.start();
        }
        int i = 0;
        for (Thread t :
                threads) {
            i++;
            while (t.isAlive() && opMode.opModeIsActive()) {
            }
        }
    }
}
