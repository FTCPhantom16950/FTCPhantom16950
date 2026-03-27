package org.firstinspires.ftc.teamcode.own.mechanism;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;

public class LimeLightMechanism implements Mechanism {
    Limelight3A limelight3A;
    HardwareMap hw;
    @Override
    public void init() throws InterruptedException {
        hw = Robot.INSTANCE.getRobotData("HardwareMap" , HardwareMap.class);
        limelight3A = hw.get(Limelight3A.class, "LimeLight");
        limelight3A.setPollRateHz(100);
        limelight3A.pipelineSwitch(0);
        limelight3A.start();
        Robot.INSTANCE.addRobotDevice("limelight", limelight3A);
    }
}
