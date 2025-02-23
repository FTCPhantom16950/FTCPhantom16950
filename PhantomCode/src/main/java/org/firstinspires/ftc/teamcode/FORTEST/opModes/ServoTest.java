package org.firstinspires.ftc.teamcode.FORTEST.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class ServoTest extends LinearOpMode {
    CRServo servo, servo1, servo2, servo3, servo4;
//    LedControl ledControl = new LedControl(this);
//    ColorSensorClass colorSensorClass = new ColorSensorClass(this);
    @Override
    public void runOpMode() throws InterruptedException {

        servo = hardwareMap.get(CRServo.class, "vrash2");
        servo1 = hardwareMap.get(CRServo.class, "krut");
        servo2 = hardwareMap.get(CRServo.class, "zx");
        servo3 = hardwareMap.get(CRServo.class, "klesh");
//        ledControl.init();
//        colorSensorClass.init();
        //servo4 = hardwareMap.get(CRServo.class, "vrash");
        waitForStart();
//        ledControl.start();
//        colorSensorClass.start();
        while(opModeIsActive()){

            servo.setPower(1);
            servo1.setPower(1);
            servo2.setPower(1);
            servo3.setPower(1);
            telemetry.addData("Vrash2", servo.getPower());
            telemetry.addData("krut", servo1.getPower());
            telemetry.addData("zx", servo2.getPower());
            telemetry.addData("klesh", servo3.getPower());
        //    telemetry.addData("vrash", servo4.getPower());
            telemetry.update();
            sleep(1500);
            servo.setPower(-1);
            servo1.setPower(-1);
            servo2.setPower(-1);
            servo3.setPower(-1);
            sleep(1500);
        }
    }
}
