package org.firstinspires.ftc.teamcode.own.OpModes;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.Actions.PedroAction;
import org.firstinspires.ftc.teamcode.own.Utils.Actions;
@Autonomous()
public class PhantomOpMode extends OpMode {
    boolean inSleep = false;
    int lastSleep = 0;
    DcMotorEx motor;
    CRServo servo1, servo2;
    @Override
    public void init() {
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        servo1 = hardwareMap.get(CRServo.class, "ser");
        servo2 = hardwareMap.get(CRServo.class, "ser2");
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        servo2.setPower(0);
        servo1.setPower(0);
    }

    @Override
    public void start() {
        super.start();
        servo1.setPower(1);
        servo2.setPower(1);
    }
    public void sleep(int ms){
        try {
            inSleep = true;
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        inSleep = false;
        lastSleep = ms;
    }

    @Override
    public void stop() {
        super.stop();
        if(inSleep){
            sleep(lastSleep);
        }
        servo2.setPower(0);
        servo1.setPower(0);
        motor.setPower(0);

    }

    @Override
    public void loop() {
        motor.setPower(1);
        sleep(1000);
        motor.setPower(-1);
        servo2.setPower(-1);
        servo1.setPower(-1);
        sleep(1000);
        servo1.setPower(1);
        servo2.setPower(1);
    }
}
