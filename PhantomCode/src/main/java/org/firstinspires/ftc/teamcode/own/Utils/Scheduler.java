package org.firstinspires.ftc.teamcode.own.Utils;

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
            return new Scheduler(this);
        }
    }
    /// Внутренний конструктор необходимый для Builder
    private Scheduler(Builder builder) {
        mechanisms = builder.mechanisms;
        action = builder.action;
    }
    /// Метод для инициализации механизмов
    public void initMechanism() {
        for (Mechanism mechanism :
                mechanisms) {
            mechanism.init();
        }
    }
    /// Запуск действий
    public void run() {
        action.execute();
    }
}
