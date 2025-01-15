package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.pod;

import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;
@Autonomous
public class ParkovaVesh extends LinearOpMode {
    public final Pose startPose = new Pose(134.47662485746864, 75.53021664766247, Math.toRadians(0));
    final Pose toSpiecman = new Pose(109.6830102622577, 72.73888255416192, Math.toRadians(0));
    final Pose toPark = new Pose(131.0285062713797, 133.1630558722919, Math.toRadians(90));
    final Pose toBucket = new Pose(128.07297605473204,15.270239452679593, Math.toRadians(135));
    final Pose to1Sample = new Pose(112.96693272519954, 24.136830102622582, Math.toRadians(180));
    final Pose to1SampleControl = new Pose(127.90877993158495, 26.928164196123156);
    final Pose to2Sample = new Pose(112.80273660205245, 12.31470923603192, Math.toRadians(180));
    final Pose to2SampleControl = new Pose(121.34093500570125, 7.8814139110604255);
    final Pose to3Sample = new Pose(115.59407069555303, 10.672748004560997, Math.toRadians(210));
    final Pose to3SampleControl = new Pose(117.40022805017104, 31.03306727480046);
    final Pose toPark2 = new Pose(80.62029646522234,46.63169897377423, Math.toRadians(270));
    final Pose ToPark2Control = new Pose(120.1915621436716,4.2690992018244085);
    private Timer pathTimer, actionTimer;
    PathChain toSpiecmanPC, toParkPC, toBucketPC, to1SamplePC, to2SamplePC, to3SamplePC, toPark2PC;
    ElapsedTime timer = new ElapsedTime();
    WheelBase wheelBase = new WheelBase(this);
    HorizontSlider horizontSlider = new HorizontSlider(this);
    VerticalSlider verticalSlider = new VerticalSlider(this);
    Zx zx = new Zx(this);

    @Override
    public void runOpMode() throws InterruptedException {
        pathTimer = new Timer();
        wheelBase.initWheelBase(hardwareMap);
        verticalSlider.init();
        horizontSlider.init();
        zx.init();
        waitForStart();
        timer.reset();
        while (opModeIsActive()){
            timer.reset();
            verticalSlider.pod.setPower(1.0);
            sleep(300);
            pod.setPower(0.13);
            wheelBase.nazad_timing(3000, 0.3);
            wheelBase.vpred_Taiming(160, 0.3);
            sleep(500);
            pod.setPower(0.7);
            sleep(700);
            pod.setPower(0.13);
            sleep(500);
            wheelBase.vpred_Taiming(200, 0.3);
            pod.setPower(-0.7);
            sleep(500);
            pod.setPower(0.13);
            // sleep(10000);
            wheelBase.vpred_Taiming(1500, 0.3);
            sleep(200);
            wheelBase.razvarotEncoder(1500, 0.3);
            sleep(200);
            wheelBase.nazad_timing(5000, 0.3);

            sleep(30000);
        }
    }

}
