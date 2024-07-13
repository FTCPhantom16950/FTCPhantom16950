package org.firstinspires.ftc.teamcode.Utils;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class FTCcontroolers {
    LinearOpMode opMode;
    public double target;

    public FTCcontroolers(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    Config config = new Config();
    public PIDFController pidf = new PIDFController(config.k_p, config.k_i, config.k_d, config.k_f);
    double output = 0;
    Thread pidfTester = new Thread(() -> {
        while (opMode.opModeIsActive()){
            pidf.setPIDF(config.k_p, config.k_i, config.k_d, config.k_f);
        }
    });
    public void PIDFstarter(double target, DcMotorEx motor){
        pidfTester.start();
        this.target = target;
        pidf.setTolerance(25);
        pidf.setSetPoint(target);
        pidf.calculate(motor.getCurrentPosition());
        while(!pidf.atSetPoint()){
            output = pidf.calculate(motor.getCurrentPosition());
            motor.setVelocity(output);
        }
        motor.setPower(0);
    }

}