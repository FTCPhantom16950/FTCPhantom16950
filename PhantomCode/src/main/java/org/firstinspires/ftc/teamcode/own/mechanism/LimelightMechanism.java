package org.firstinspires.ftc.teamcode.own.mechanism;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfCrServo;

public class LimelightMechanism implements Mechanism {
    SfCrServo crServo;
    Limelight3A limelight3A;
    @Override
    public void init() throws InterruptedException {
        limelight3A = Robot.INSTANCE.hw.get(Limelight3A.class, "LimeLight");
        limelight3A.setPollRateHz(100);
//        limelight3A.start();
//        limelight3A.pipelineSwitch(0);
    }

    @Override
    public void read() throws InterruptedException {
        Mechanism.super.read();
    }
}
