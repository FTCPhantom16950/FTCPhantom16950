package org.firstinspires.ftc.teamcode.own.mechanism.util;

import static org.firstinspires.ftc.teamcode.own.utils.Robot.INSTANCE;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;

public class VolatgeMechanism implements Mechanism {
    @Override
    public void init() throws InterruptedException {
        VoltageSensor voltageSensor = Robot.INSTANCE.hw.voltageSensor.iterator().next();
        Robot.INSTANCE.addOrUpdate(voltageSensor, "vltg");
    }

    @Override
    public void read() {
        Mechanism.super.read();
        Robot.INSTANCE.voltage = Robot.INSTANCE.voltageSensor.getVoltage();
        Robot.INSTANCE.addData("voltage", Robot.INSTANCE.voltage);
        Robot.INSTANCE.addData("voltage compenser", Robot.INSTANCE.voltageCompenser);
        if (INSTANCE.voltage <= 9.0 && !INSTANCE.soundPlaying && !INSTANCE.nearlyPlayed) {
            int soundID = INSTANCE.getApp().getResources().getIdentifier("rubezvozvrata", "raw", INSTANCE.getApp().getPackageName());
            INSTANCE.multipleTelemetry.addData("playing", soundID);
            INSTANCE.soundPlaying = true;

            SoundPlayer.getInstance().startPlaying(INSTANCE.getApp(), soundID, INSTANCE.params, null,
                    new Runnable() {
                        public void run() {
                            INSTANCE.soundPlaying = false;
                        }
                    });
        }
    }
}
