package org.firstinspires.ftc.teamcode.FORTEST.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
@Disabled
@Autonomous(name = "1")
public class TESTAUTO extends LinearOpMode {
    DcMotorEx motor1,motor2,motor3,motor4;
    @Override
    public void runOpMode() throws InterruptedException {
        motor1 = hardwareMap.get(DcMotorEx.class, "1");
        motor2 = hardwareMap .get(DcMotorEx.class, "2");
        motor3 = hardwareMap .get(DcMotorEx.class, "3");
        motor4 = hardwareMap .get(DcMotorEx.class, "4");

        waitForStart();
        while(opModeIsActive()){
            motor1.setPower(0.2);
            motor2.setPower(0.2);
            motor3.setPower(0.2);
            motor4.setPower(0.2);
        }
    }
}
