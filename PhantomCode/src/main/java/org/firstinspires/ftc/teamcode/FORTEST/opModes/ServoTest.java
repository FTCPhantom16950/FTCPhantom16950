package org.firstinspires.ftc.teamcode.FORTEST.opModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.own.Utils.PhMath;

@TeleOp
@Config
public class ServoTest extends LinearOpMode {
    public static double pZx = 13, pKrut = 0, pVrash2 = 50, pVrash3 = 155, pklesh = 110, pVrash = 60, prP = 67.5, plP = 67.5;
    @Override
    public void runOpMode() throws InterruptedException {
        CRServo servo1 = hardwareMap.get(CRServo.class,"zx"),
        servo2 = hardwareMap.get(CRServo.class,"krut"),
        servo3 = hardwareMap.get(CRServo.class,"vrash2"),
        servo4 = hardwareMap.get(CRServo.class,"vrash3"),
        servo5 = hardwareMap.get(CRServo.class, "klesh"),
        servo6 = hardwareMap.get(CRServo.class, "vrash"),
        servo7 = hardwareMap.get(CRServo.class, "lP"),
        servo8 = hardwareMap.get(CRServo.class, "rP");
        MultipleTelemetry telemetry1 = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        waitForStart();
        while(opModeIsActive()){
            servo1.setPower(PhMath.fromDegreesToPower(pZx,270));
            servo2.setPower(PhMath.fromDegreesToPower(pKrut,270));
            servo3.setPower(PhMath.fromDegreesToPower(pVrash2,270));
            servo4.setPower(PhMath.fromDegreesToPower(pVrash3,270));
            servo5.setPower(PhMath.fromDegreesToPower(pklesh, 270));
            servo6.setPower(PhMath.fromDegreesToPower(pVrash, 270));
            servo7.setPower(PhMath.fromDegreesToPower(plP, 270));
            servo8.setPower(PhMath.fromDegreesToPower(prP, 270));
            telemetry1.addData("p1", servo1.getPower());
            telemetry1.addData("p2", servo2.getPower());
            telemetry1.addData("p3", servo3.getPower());
            telemetry1.addData("p4", servo4.getPower());
            telemetry1.addData("p5", servo5.getPower());
            telemetry1.addData("p6", servo6.getPower());
            telemetry1.addData("p7", servo7.getPower());
            telemetry1.addData("p8", servo8.getPower());
            telemetry1.update();
        }
    }
}
