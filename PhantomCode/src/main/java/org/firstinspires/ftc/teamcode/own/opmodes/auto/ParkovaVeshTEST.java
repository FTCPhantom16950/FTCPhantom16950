package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;
import org.firstinspires.ftc.teamcode.own.Utils.PedroUtil;

@Autonomous
public class ParkovaVeshTEST extends LinearOpMode {
    ElapsedTime timer = new ElapsedTime();
    Follower follower;
    PedroUtil pedroUtil;
    HorizontSlider horizontSlider = new HorizontSlider(this);
    VerticalSlider verticalSlider = new VerticalSlider(this);
    Zx zx = new Zx(this);
    @Override
    public void runOpMode() throws InterruptedException {
        follower = new Follower(hardwareMap);
        pedroUtil = new PedroUtil(follower, this);
        waitForStart();
        timer.reset();
        while (opModeIsActive()){
            sleep(30000);
        }
    }
}
