package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.jetbrains.annotations.NotNull;

public class FTCControllers {
    LinearOpMode OPMode;
    double currentVelocity;
    double referenceVelocity = 0;
    double integrative = 0;
    double limInteger = 30;
    double der = 0;
    double lastError = 0;
    public double error;
    public FTCControllers(LinearOpMode OPMode) {
        this.OPMode = OPMode;
    }

    public void originalPIDF(@NotNull DcMotorEx motor, double p, double i, double d, double f) {
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(p, i, d, f);
        motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        int errorThere = motor.getTargetPosition() - motor.getCurrentPosition();
        OPMode.telemetry.addData("P,I,D,F (modified)", "%.04f, %.04f, %.04f, %.04f",
                pidfCoefficients.p, pidfCoefficients.i, pidfCoefficients.d, pidfCoefficients.f);
        OPMode.telemetry.update();
        OPMode.telemetry.addData("Error: ", errorThere);
        OPMode.telemetry.update();
    }
    public double feedForward(@NotNull DcMotorEx motor, double a, double v, double g, double cos,double reference) {
        double accel = 15;
        currentVelocity = motor.getVelocity();
        double output = a * accel + v * referenceVelocity + Math.cos(reference) * cos + g;
        return output;
    }
    public double PIDController(@NotNull DcMotorEx motor, double p, double i, double d, double reference) {
        double output;
        double a = 0.8;
        ElapsedTime timer = new ElapsedTime();
        error = reference - motor.getCurrentPosition();
        der = (error - lastError) / timer.milliseconds();
        integrative = integrative + (error * timer.milliseconds());
        if (integrative >= limInteger){
            integrative = limInteger;
        } else if (integrative <= -limInteger){
            integrative = -limInteger;
        }
        output = p * error + integrative * i + der * d;
        timer.reset();
        lastError = error;
        return output;
    }
    public void fullControl(@NotNull DcMotorEx motor, double p, double i, double d, double a, double v, double g, double cos, double reference) {
        double output = PIDController(motor, p, i, d, reference) + feedForward(motor, a, v, g, cos, reference);
        motor.setPower(output);
    }





}