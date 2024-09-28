package org.firstinspires.ftc.teamcode.own.OpModes.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.own.Utils.FTCControllers;

@TeleOp
public class PIDFmotorTester extends LinearOpMode {
    OPMode opMode = new OPMode();
    FTCControllers controllers = new FTCControllers(opMode);
    DcMotorEx motor;
    ElapsedTime timer = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        timer.reset();
        while (opMode.opModeIsActive()) {
            opMode.runOpMode();
        }
    }
    class OPMode extends LinearOpMode {
        @Override
        public void runOpMode() throws InterruptedException {
            motor = hardwareMap.get(DcMotorEx.class, "motor");
            waitForStart();
            timer.reset();
            while (opModeIsActive()) {

            }
        }
    }
}
