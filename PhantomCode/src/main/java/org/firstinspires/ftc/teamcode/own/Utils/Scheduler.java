package org.firstinspires.ftc.teamcode.own.Utils;

import static org.firstinspires.ftc.teamcode.own.Utils.UnitedTelemetry.multipleTelemetry;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс для подготовки и последовательного выполения действий
 * Class for initialization and running actions
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 02:40
 */
public class Scheduler {
    /// сет с необходимыми механизмами
    private final Set<Mechanism> mechanisms;
    /// Выполняемое действие
    private final Action action;

    /**
     * Билдер для класса Scheduler, позволяет его настраивать
     */
    public static class Builder {
        /// сет с необходимыми механизмами
        private final Set<Mechanism> mechanisms = new HashSet<>();
        /// Выполняемое действие
        private Action action;

        /// Метод добавления механизмов в необходимые
        public Builder addMechanisms(Set<Mechanism> mechanisms) {
            this.mechanisms.addAll(mechanisms);
            return this;
        }

        /// Метод добавления механизма в необходимые
        public Builder addMechanism(Mechanism mechanism) {
            this.mechanisms.add(mechanism);
            return this;
        }

        /// Метод добавления действия
        public Builder setAction(Action action) {
            this.action = action;
            return this;
        }

        /// Метод для сборки класса Scheduler
        public Scheduler build() {
            if (action == null) {
                throw new IllegalStateException("Action is required");
            }
            return new Scheduler(this);
        }
    }

    /// Внутренний конструктор необходимый для Builder
    private Scheduler(Builder builder) {
        mechanisms = Set.copyOf(builder.mechanisms);
        action = builder.action;
    }

    /// Метод для инициализации механизмов
    public void initMechanism() {
        if (mechanisms.isEmpty()) {
            throw new IllegalStateException("Mechanisms is null");
        }
        for (Mechanism mechanism : mechanisms) {
            try {
                mechanism.init();
            } catch (Exception e) {
                multipleTelemetry.addLine("Mechanism init failed: " + mechanism.getClass().getSimpleName());
                throw e;
            }
        }
    }

    private boolean isRunning = false;

    /// Запуск действий
    public void run() {
        if (isRunning) {
            throw new IllegalStateException("Scheduler already running");
        }
        isRunning = true;

        if (action == null) {
            throw new NullPointerException("Action is null");
        }
        action.execute();
    }
}
