package org.firstinspires.ftc.teamcode.own.Mechanism.trajectories;


import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;


public class Trajectories {
    LinearOpMode opMode;
    MecanumDrive drive = new MecanumDrive(opMode.hardwareMap, new Pose2d(0,0,0));
    Action trajectory;
    public void test(){
        trajectory = drive.actionBuilder(drive.pose)
                .build();
    }
}
