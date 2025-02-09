package org.firstinspires.ftc.teamcode.own.Utils;

import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class PhantomOpMode extends LinearOpMode {
    public double pathState = 0;
    private Timer pathTimer, actionTimer;
    Thread telemetryThread = new Thread(() -> {
        while (opModeIsActive()) {
            telemetryDebug();
            telemetry.update();
        }
    });

    @Override
    public void runOpMode() throws InterruptedException {
        pathTimer = new Timer();
        actionTimer = new Timer();
        initMechanism();
        waitForStart();
        telemetryThread.start();
        while (opModeIsActive()) {
            play();
        }
    }
    public abstract void initMechanism();
    public abstract void play();
    public abstract void telemetryDebug();
    public abstract void trajectory();

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }
}
