package org.firstinspires.ftc.teamcode.own.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
@Disabled

@TeleOp
public class PID_Tester extends LinearOpMode{
//    VerticalSlider verticalSlider = new VerticalSlider(this);
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor pod = hardwareMap.dcMotor.get("pod");
        pod.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pod.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        verticalSlider.init();
//        verticalSlider.pidThread.start();
        waitForStart();
        while(opModeIsActive()){
//            verticalSlider.test();
            if(this.gamepad2.dpad_up){
                if (pod.getPower() <= 1){
                    pod.setPower(1);
                } else {
                    pod.setPower(1);
                }
            } else if (this.gamepad2.dpad_down) {
                if (pod.getPower() >= -1){
                    pod.setPower(-1);
                } else {
                    pod.setPower(-1);
                }
            } else {
                pod.setPower(0.0125);
            }
        }
    }
}