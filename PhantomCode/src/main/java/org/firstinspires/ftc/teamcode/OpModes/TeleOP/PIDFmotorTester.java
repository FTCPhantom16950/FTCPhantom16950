package org.firstinspires.ftc.teamcode.OpModes.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.Utils.Config;

public class PIDFmotorTester extends OpMode {
    WheelBase wheelBase = new WheelBase();
    Config config = new Config();
    @Override
    public void init() {
        wheelBase.initWheelBase(hardwareMap);
    }

    @Override
    public void loop() {
        wheelBase.PIDFtester(config.power, config.distance);
    }
}
