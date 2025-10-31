package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;

public class OdometryMechanism implements Mechanism {
    private static DcMotorEx leftOdometry, rightOdometry, horizontalOdometry;

    @Override
    public boolean init() {

        return true;
    }
}
