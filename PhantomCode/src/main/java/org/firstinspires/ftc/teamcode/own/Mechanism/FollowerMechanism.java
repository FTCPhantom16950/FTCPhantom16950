package org.firstinspires.ftc.teamcode.Own.Mechanism;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.ThreeWheelIMUConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.Own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.pedropathing.Constants;

public class FollowerMechanism implements Mechanism {

    private static HardwareMap hardwareMap;

    public FollowerMechanism(HardwareMap hardwareMap) {
        FollowerMechanism.hardwareMap = hardwareMap;
    }

    public static Follower follower;

    @Override
    public boolean init() {
        follower = Constants.createFollower(hardwareMap);
        return true;
    }
}
