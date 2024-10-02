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
import org.firstinspires.ftc.teamcode.own.Utils.Controllers.PidControl;

@TeleOp
public class TEST extends LinearOpMode {
    ElapsedTime time = new ElapsedTime();
    KalmanFilter filter;
    double filteredPos;
    double output;
  CRServo servo;
    @Override
    public void runOpMode() throws InterruptedException {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        PidControl pidControl;
        Telemetry dsTelemetry = dashboard.getTelemetry();
       servo = hardwareMap.get(CRServo.class, "test1");
        DcMotorEx motor = hardwareMap.get(DcMotorEx.class, "test");
//        filter = new KalmanFilter(Config.processNoiseVariance, Config.measurementNoiseVariance, motor.getCurrentPosition(), motor.getTargetPosition(), 0);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pidControl = new PidControl(0,0,0, motor.getTargetPosition(), motor);
        Thread thread = new Thread(() -> {
            while (true){
                pidControl.setMotor(motor);
                pidControl.setP(Config.k_p);
                pidControl.setD(Config.k_d);
                pidControl.setI(Config.k_i);
                dsTelemetry.addData("error", motor.getTargetPosition() - motor.getCurrentPosition());
                dsTelemetry.update();
                telemetry.addData("output", output);
                telemetry.addData("error", motor.getTargetPosition() - motor.getCurrentPosition());
                telemetry.update();
                pidControl.setReference(motor.getTargetPosition());
                output = pidControl.calculate();
//                filter.setMeasurementNoiseVariance(Config.measurementNoiseVariance);
//                filter.setProcessNoiseVariance(Config.processNoiseVariance);
//                filter.setReference(motor.getTargetPosition());
//                filter.setInput(motor.getCurrentPosition());
//                filter.update();
//                filteredPos = filter.state;
//                dsTelemetry.addData("filteredPos", filteredPos);
//                dsTelemetry.addData("123", motor.getCurrentPosition());
//                dsTelemetry.update();
//                telemetry.addData("filteredPos", filteredPos);
//                telemetry.addData("123", motor.getCurrentPosition());
//                telemetry.update();
            }
        });

        time.reset();
        waitForStart();
        if (opModeIsActive()){
            thread.start();
//            filteredPos = 0;
        }
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
          //  filter.predict();
           motor.setPower(pidControl.calculate());
           while (motor.isBusy()){

           }
          motor.setPower(0);
            sleep(2000);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            sleep(1000);
            motor.setTargetPosition(-2000);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
           motor.setPower(pidControl.calculate());
           //filter.predict();
            while (motor.isBusy()){
//                dsTelemetry.addData("filteredPos", filteredPos);
//                dsTelemetry.addData("123", motor.getCurrentPosition());
//                dsTelemetry.update();
//                telemetry.addData("filteredPos", filteredPos);
//                telemetry.addData("123", motor.getCurrentPosition());
//                telemetry.update();

            }
            motor.setPower(0);
            sleep(2000);

        }
        if (!opModeIsActive()){
            thread = null;
        }
    }
}
