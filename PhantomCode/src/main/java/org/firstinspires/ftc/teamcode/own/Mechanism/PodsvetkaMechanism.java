package org.firstinspires.ftc.teamcode.Own.Mechanism;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.Own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.Own.Utils.Robot;

public class PodsvetkaMechanism implements Mechanism {
    @Override
    public boolean init() throws InterruptedException {
        DigitalChannel digitalChannel = Robot.hw.get(DigitalChannel.class, "proz1");
        digitalChannel.setMode(DigitalChannel.Mode.OUTPUT);
        digitalChannel.setState(false);
        Robot.addOrUpdate("proz1", digitalChannel);

        return true;
    }
}
