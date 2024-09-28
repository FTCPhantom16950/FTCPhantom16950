package org.firstinspires.ftc.teamcode.own.OpModes.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.own.Utils.FTCControllers;

@TeleOp
public class PIDFmotorTester2 extends LinearOpMode {
    ElapsedTime runtime = new ElapsedTime();
    DcMotorEx motor;
    @Override
    public void runOpMode() throws InterruptedException {
        FTCControllers controllers = new FTCControllers(new PIDFmotorTester2());
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                motor.setPower(0.1);
            } else if (gamepad1.b) {
                controllers.originalPIDF(motor, 0,0,0,0);
                motor.setTargetPosition(200);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor.setPower(0.3);
                while (opModeIsActive() && motor.isBusy()) {
                    telemetry.addData("Error", motor.getTargetPosition() - motor.getCurrentPosition());
                }
                motor.setPower(0);
                motor.setTargetPosition(-200);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor.setPower(0.3);
                while (opModeIsActive() && motor.isBusy()) {
                    telemetry.addData("Error", motor.getTargetPosition() - motor.getCurrentPosition());
                }
                motor.setPower(0);
            } else if (gamepad1.x) {
                controllers.fullControl(motor, 0,0,0,0,0,0,0,0,0);
            } else if (gamepad1.y) {
                motor.setPower(controllers.stateControl(motor, 0,0,0,0));
            }
        }
    }

}
