package org.firstinspires.ftc.teamcode.FORTEST.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.FORTEST.Controllers.FtcControllers;
import org.firstinspires.ftc.teamcode.FORTEST.Controllers.FullControl;
import org.firstinspires.ftc.teamcode.FORTEST.Controllers.FullStateControl;


@TeleOp
public class PIDFmotorTester2 extends LinearOpMode {
    FtcControllers controllers = new FtcControllers(this);
    FullControl fullControl;
    FullStateControl fullStateControl;
    ElapsedTime runtime = new ElapsedTime();
    DcMotorEx motor;
    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setDirection(DcMotor.Direction.REVERSE);
        fullStateControl = new FullStateControl(0, 0, 0, 0, motor);
        fullControl = new FullControl(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, motor, this);
        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                motor.setPower(0.1);
                sleep(1000);
                motor.setPower(0);
            } else if (gamepad1.b) {
                controllers.originalPidf(motor, 0,0,0,0);
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
                motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motor.setPower(fullControl.calculate());// full control
            } else if (gamepad1.y) {
                motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motor.setPower(fullStateControl.stateControl());//fullstate
            }
        }
    }

}
