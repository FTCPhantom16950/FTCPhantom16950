package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.own.Utils.PIDControl;

@Autonomous
@Config
public class PodPidTest extends LinearOpMode {
    public static double p,i,d;
    public static int target;
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motor = hardwareMap.get(DcMotor.class, "pod");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        PIDControl pidControl = new PIDControl();
        Thread thread = new Thread(() ->{
            while(opModeIsActive()){
                pidControl.setTarget(target);
                pidControl.setMeasured(motor.getCurrentPosition());
                motor.setPower(pidControl.getOut());
            }
        });
        waitForStart();
        thread.start();
        while(true){
            pidControl.start();
            pidControl.setkP(p);
            pidControl.setkD(d);
            pidControl.setkI(i);
        }
    }
}
