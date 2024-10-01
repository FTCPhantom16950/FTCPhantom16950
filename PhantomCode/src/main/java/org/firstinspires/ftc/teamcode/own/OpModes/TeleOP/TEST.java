package org.firstinspires.ftc.teamcode.own.OpModes.TeleOP;



import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.Utils.Controllers.KalmanFilter;

@TeleOp
public class TEST extends LinearOpMode {
    ElapsedTime time = new ElapsedTime();
    KalmanFilter filter;
    double filteredPos;
  CRServo servo;
    @Override
    public void runOpMode() throws InterruptedException {
       servo = hardwareMap.get(CRServo.class, "test1");
        DcMotorEx motor = hardwareMap.get(DcMotorEx.class, "test");
        filter = new KalmanFilter(-0.1, -0.1, motor.getCurrentPosition(), motor.getTargetPosition(), 0.1);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        time.reset();
        waitForStart();
        while(opModeIsActive()){
//
//           servo.setPower(0.1);
//          sleep(10000);
//            servo.setPower(0.5);
//           sleep(2000);
//           servo.setPower(1);
//           sleep(2000);
//            servo.setPower(0);


            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setTargetPosition(2000);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

           motor.setPower(0.4);

           while (motor.isBusy()){
               filter.setReference(motor.getTargetPosition());
               filter.setInput(motor.getCurrentPosition());
               filteredPos = filter.calculate();
               telemetry.addData("filteredPos", filteredPos);
               telemetry.addData("123", motor.getCurrentPosition());
               telemetry.update();
           }
          motor.setPower(0);
            sleep(2000);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setTargetPosition(-2000);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
           motor.setPower(0.4);
            while (motor.isBusy()){
                filter.setReference(motor.getTargetPosition());
                filter.setInput(motor.getCurrentPosition());
                filteredPos = filter.calculate();
                telemetry.addData("filteredPos", filteredPos);
                telemetry.addData("123", motor.getCurrentPosition());
                telemetry.update();
            }
            motor.setPower(0);
            sleep(2000);

        }
    }
}
