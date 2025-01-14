package org.firstinspires.ftc.teamcode.pedroPathing.PedroOpModes;

import com.pedropathing.follower.Follower;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.paths.EndPaths;
import org.firstinspires.ftc.teamcode.pedroPathing.paths.StartPaths;
@Autonomous
public class PedroVesh extends LinearOpMode {
    StartPaths startPaths;
    EndPaths endPaths;
    @Override
    public void runOpMode() throws InterruptedException {
        Constants.setConstants(FConstants.class, LConstants.class);
        Follower follower = new Follower(hardwareMap);
        startPaths = new StartPaths();
        endPaths = new EndPaths(follower);
        startPaths.buildStartPaths();
        endPaths.buildEndPaths();
        waitForStart();
        if (opModeIsActive()){
            follower.followPath(startPaths.toSpiceMan, true);
            follower.update();
            follower.followPath(endPaths.toPark2fromSPICEMAN, true);
            follower.update();
            sleep(30000);
        }
    }
}
