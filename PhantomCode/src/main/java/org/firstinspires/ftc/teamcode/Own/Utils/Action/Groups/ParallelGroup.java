package org.firstinspires.ftc.teamcode.Own.Utils.Action.Groups;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Own.Utils.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ParallelGroup implements Action {
    private final LinearOpMode opMode;
    private final List<Action> actions = new ArrayList<>();
    private final ExecutorService threadPool;

    public ParallelGroup(ExecutorService threadPool, LinearOpMode opMode, Action... actions) {
        this.actions.addAll(List.of(actions));
        this.opMode = opMode;
        this.threadPool = threadPool;
    }

    @Override
    public void execute() throws InterruptedException {
        // CountDownLatch можно использовать вместо join, но join проще для понимания
        List<java.util.concurrent.Future<?>> futures = new ArrayList<>();

        for (Action a : actions) {
            futures.add(threadPool.submit(() -> {
                try {
                    a.execute();
                } catch (Exception e) {
                    Robot.INSTANCE.multipleTelemetry.addData("Parallel Error", e.getMessage());
                }
            }));
        }

        // Ждем завершения всех задач
        boolean allDone = false;
        while (!allDone && opMode.opModeIsActive() && !Thread.currentThread().isInterrupted()) {
            allDone = true;
            for (Future<?> future : futures) {
                if (!future.isDone()) {
                    allDone = false;
                    break; // Если хоть один не готов, выходим из for и ждем дальше
                }
            }
            // Спим чуть-чуть, чтобы не грузить процессор
            opMode.sleep(10);
        }

        // 3. Если OpMode остановлен (кнопка Stop), отменяем задачи, которые еще висят
        if (!opMode.opModeIsActive()) {
            for (Future<?> future : futures) {
                // true означает "прервать жестко", даже если поток работает
                future.cancel(true);
            }
        }
    }
}
