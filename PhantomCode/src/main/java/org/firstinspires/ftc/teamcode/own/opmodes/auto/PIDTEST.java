package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.pod;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Utils.PIDControl;

@Autonomous
@Config
public class PIDTEST extends LinearOpMode {
    public static double P = 0,I = 0,D = 0;
    public static int target = 1000, tolerance = 50;
    PIDControl pidControl = new PIDControl();
    VerticalSlider verticalSlider = new VerticalSlider(this);
    @Override
    public void runOpMode() throws InterruptedException {
        verticalSlider.init();
        pidControl.setkP(P);
        pidControl.setkD(D);
        pidControl.setkI(I);
        pidControl.setTarget(target);
        pidControl.setTolerance(tolerance);
        pidControl.setMaxPower(0.5);
        pidControl.setMinPower(-0.5);
        pidControl.setOpMode(this);
        waitForStart();
        pidControl.start();
        while (!isStopRequested()){
            pidControl.setkP(P);
            pidControl.setkD(D);
            pidControl.setkI(I);
            pidControl.setTarget(target);
            pidControl.setTolerance(tolerance);
            pod.setPower(pidControl.out);
            pidControl.setMeasured(pod.getCurrentPosition());
            telemetry.addData("out",pidControl.out);
            telemetry.addData("measured",pod.getCurrentPosition());
            telemetry.addData("error0", pidControl.error);
            telemetry.addData("target", pidControl.getTarget());

            telemetry.update();
        }
        if(isStopRequested()){
            pidControl.stopCalc();
        }
    }
}
