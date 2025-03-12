package org.firstinspires.ftc.teamcode.FORTEST.ftclib.SYS;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

public class WheelBaseSYS extends SubsystemBase {
    public static Follower follower;
    public WheelBaseSYS(LinearOpMode opMode, Pose pose) {
        Constants.setConstants(FConstants.class, LConstants.class);
        follower.setStartingPose(pose);
    }

    @Override
    public void periodic() {
        super.periodic();
        follower.update();
    }
}
