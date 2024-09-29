package org.firstinspires.ftc.teamcode.own.Utils.Controllers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class FullControl{
    double referenceAcceleration, referenceVelocity;
    FullStateControl stateControl;
    FeedForwardControl feedForwardControl;
    PidControl pidController;
    DcMotorEx motorEx;
    LinearOpMode OPMode;
    double output;
    private final double tolerance = 100;
    private double referencePos;
    /**
     *
     * @param motorEx DcMotorEx мотор в для которого будет настроен регулятор
     * @param p p коэффициент
     * @param i i коэффициент
     * @param d d коэффициент
     * @param a a a коэффициент (для ускорения)
     * @param v v коэффициент (для скорости)
     * @param g g коэффициент (для противостояния гравитации)
     * @param cos cos коэффициент (для угла, под которым нужно держать механизм)
     * @param reference число необходимых оборотов моторов
     * @param degrees значение угла на котором нужно держать мотор
     */
    public FullControl(double referencePosition, double referenceVelocity, double referenceAcceleration, double k1, double k2, double p, double i, double d, double reference, double a, double v, double g, double cos, double degrees, DcMotorEx motorEx, LinearOpMode opMode) {
        this.OPMode = opMode;
        stateControl = new FullStateControl(referencePosition, referenceVelocity, k1, k2, motorEx);
        pidController = new PidControl(p, i, d, reference, motorEx);
        feedForwardControl = new FeedForwardControl(a, v, g, cos, degrees, motorEx);
        this.motorEx = motorEx;
        this.referenceAcceleration = referenceAcceleration;
        this.referencePos = referencePosition;
    }

    public double calculate(){
        output = 0;
        while (OPMode.opModeIsActive() && motorEx.isBusy() && Math.abs(motorEx.getCurrentPosition() - referencePos) >= tolerance){
            output = stateControl.stateControl() + pidController.calculate() + feedForwardControl.feedForward(referenceAcceleration,referenceVelocity);
            output = Math.max(Math.min(output, 1), -1);
            OPMode.sleep(100);
        }
        output = 0;
        return output;
    }
}