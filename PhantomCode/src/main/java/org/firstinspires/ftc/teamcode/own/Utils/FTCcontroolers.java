package org.firstinspires.ftc.teamcode.own.Utils;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class FTCcontroolers {
    LinearOpMode opMode;
    DcMotorEx motor;
    public FTCcontroolers(LinearOpMode opMode) {
        this.opMode = opMode;
    }
    ElapsedTime elapsedTime = new ElapsedTime();
    double reference, k_P, k_D, k_I, previousReference, gain, curr_est, prev_est;
    double tolerance = 100;
    double error, previousError;
    boolean setPointIsReached = false;

    public double p, i, d, i_limit, out;
    Thread errorCheck = new Thread(() -> {
        while (true){
            error = reference - motor.getCurrentPosition();
            previousError = error;
            previousReference = reference;
        }
    });
    public void pidfMovementSingle(DcMotorEx motor){
        gain = 0.8;
        i_limit = 0.25;
        this.motor = motor;
        elapsedTime.reset();
        reference = motor.getTargetPosition();
        errorCheck.start();
        elapsedTime.startTime();
        while (!setPointIsReached){
            if (motor.getCurrentPosition() <= reference + tolerance && motor.getCurrentPosition() >= reference - tolerance){
                setPointIsReached = true;
            }
            p = k_P * error;
            i = i + (error * elapsedTime.time());
            curr_est = (gain * prev_est) + (1 - gain) * error - previousError;
            prev_est = curr_est;
            d = curr_est / elapsedTime.time();
            if (reference != previousReference){
                i = 0;
            }
            if (i >= i_limit){
                i = i_limit;
            } else if (i < -i_limit) {
                i = -i_limit;
            }

            out = p + k_I * i + d * k_D;

            motor.setPower(Range.clip(out, -1,1));
            elapsedTime.reset();
        }

    }
}