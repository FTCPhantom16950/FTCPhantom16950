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
//    CRServo servo;
    @Override
    public void runOpMode() throws InterruptedException {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        Telemetry dhTelemetry = dashboard.getTelemetry();
        Config config = new Config();
//        servo = hardwareMap.get(CRServo.class, "test");
     DcMotorEx motor = hardwareMap.get(DcMotorEx.class, "test");
     Thread thread = new Thread(() -> {
         while (opModeIsActive()){
             filter.setReference(motor.getTargetPosition());
             dhTelemetry.addData("filteredPos", filter.calculate());
             dhTelemetry.addData("pos", motor.getCurrentPosition());
             telemetry.addData("filteredPos", filter.calculate());
             telemetry.addData("123", motor.getCurrentPosition());
             dhTelemetry.update();
             filter.update();
         }
     });

    motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      filter = new KalmanFilter(config.processNoiseVariance, config.measurementNoiseVariance, motor.getCurrentPosition(), motor.getTargetPosition(), config.gainK);
        time.reset();
        waitForStart();
        while(opModeIsActive()){

//            servo.setPower(0.1);
//            sleep(10000);
//            servo.setPower(0.5);
//            sleep(2000);
//            servo.setPower(1);
//            sleep(2000);
//            servo.setPower(0);
//            break;
          filteredPos = filter.calculate();
            thread.start();
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setTargetPosition(200);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
           motor.setPower(0.2);
           while (motor.isBusy()){

           }
          motor.setPower(0);
            sleep(5000);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setTargetPosition(-200);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
           motor.setPower(-0.2);
            while (motor.isBusy()){

            }
            motor.setPower(0);

        }
    }
}
