package org.firstinspires.ftc.teamcode.own.OpModes.TeleOP;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Utils.Controllers.KalmanFilter;

@TeleOp
public class TEST extends LinearOpMode {
    ElapsedTime time = new ElapsedTime();
    KalmanFilter filter;
    double filteredPos;
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotorEx motor = hardwareMap.get(DcMotorEx.class, "test");
        filter = new KalmanFilter(0.3, 0.3, motor.getCurrentPosition(), motor.getTargetPosition());
        time.reset();
        waitForStart();
        while(opModeIsActive()){
           filteredPos = filter.calculate();
            telemetry.addData("filteredPos", filteredPos);
            telemetry.addData("time", time.milliseconds());
            motor.setPower(0.1);
            sleep(1000);
            motor.setPower(0);
            sleep(1000);
            motor.setPower(-0.1);
            sleep(1000);
            motor.setPower(0);
            telemetry.update();
        }
    }
}
