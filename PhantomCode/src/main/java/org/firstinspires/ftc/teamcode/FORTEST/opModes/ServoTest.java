package org.firstinspires.ftc.teamcode.FORTEST.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew;

@TeleOp
public class ServoTest extends LinearOpMode {
    Zxnew zxnew = new Zxnew(this);
//    LedControl ledControl = new LedControl(this);
//    ColorSensorClass colorSensorClass = new ColorSensorClass(this);
    @Override
    public void runOpMode() throws InterruptedException {
zxnew.init();
//        ledControl.init();
//        colorSensorClass.init();
        //servo4 = hardwareMap.get(CRServo.class, "vrash");
        waitForStart();
//        ledControl.start();
//        colorSensorClass.start();
        while(opModeIsActive()){
            zxnew.run();
        }
    }
}
