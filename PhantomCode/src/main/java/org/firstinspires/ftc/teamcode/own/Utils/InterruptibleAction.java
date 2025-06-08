package org.firstinspires.ftc.teamcode.own.Utils;

/**
 * Класс создания прерываемых действий
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 04:00
 */
public abstract class InterruptibleAction extends Action {
    /// Прервано ли действие
    protected volatile boolean isInterrupted = false;
    /// Выполняется ли действие
    protected volatile boolean isRunning = false;
    ///  Выполняются ли условия для действия
    private volatile boolean requirementsToRun = false;

    /// Прерывание действий
    public void interrupt() {
        isInterrupted = true;
    }

    /// Сброс
    public void reset() {
        isInterrupted = false;
        isRunning = false;
    }

    /// Метод для реализации выполнения действия
    public abstract void run();

    /// Меетод для получения значения выполняются ли условия для действия
    public boolean isRequirementsToRun() {
        return requirementsToRun;
    }

    /// Меетод для установки значения выполняются ли условия для действия
    public void setRequirementsToRun(boolean requirementsToRun) {
        this.requirementsToRun = requirementsToRun;
    }

    /// Метод для выполнения прерывающегося действия
    @Override
    public void execute() {
        // для себя в будущем, нажал на кнопку геймпада = true и ждём выполнения действия потом false
        // выполняется пока не остановлено или выполняются действия
        while (!isInterrupted && requirementsToRun) {
            isRunning = true;
            run();
        }
        // действие в случае остановки
        if (isInterrupted) {
            handleInterruption();
        }
    }
    /// Метод для реализации случая остановки
    public abstract void handleInterruption();

}
