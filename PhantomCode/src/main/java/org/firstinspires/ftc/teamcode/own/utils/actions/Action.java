package org.firstinspires.ftc.teamcode.own.utils.actions;

/**
 * Класс создания действия
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 02:40
 */
public interface Action {
    /// Метод для реализации выполнения действия
    void execute() throws InterruptedException;

    default void sleep(long milliseconds) throws InterruptedException {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new InterruptedException();
        }
    }
}
