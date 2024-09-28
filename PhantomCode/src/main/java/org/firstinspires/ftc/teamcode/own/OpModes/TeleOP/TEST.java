package org.firstinspires.ftc.teamcode.own.OpModes.TeleOP;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class TEST extends LinearOpMode {
    ElapsedTime time = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotorEx motor = hardwareMap.get(DcMotorEx.class, "test");
        time.reset();
        waitForStart();
        while(opModeIsActive()){
            motor.setPower(0.1);
            wait(1000);
            motor.setPower(0);
        }
    }
}
