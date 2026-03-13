package org.firstinspires.ftc.teamcode.own.utils.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.own.utils.Robot;

/**
 * Класс создания прерываемых действий
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 04:00
 */
public abstract class InterruptibleAction implements Action {

    public boolean isInterrupted = false;

    public boolean isInterrupted() {
        return isInterrupted;
    }
    public Gamepad gamepad1;

    public void setInterrupted(boolean interrupted) {
        isInterrupted = interrupted;
    }

    @Override
    public void execute() throws InterruptedException {
        gamepad1 = Robot.INSTANCE.getRobotData("Gamepad1", Gamepad.class);
        while (!Thread.currentThread().isInterrupted()){
            if (!isInterrupted) {
                run();
            }
            if (isInterrupted) {
                handleInterrupt();
            }
        }

    }

    public abstract void run() throws InterruptedException;

    public abstract void handleInterrupt() throws InterruptedException;
}
