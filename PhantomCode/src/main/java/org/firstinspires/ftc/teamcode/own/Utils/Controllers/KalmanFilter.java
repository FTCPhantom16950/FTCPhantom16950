package org.firstinspires.ftc.teamcode.own.Utils.Controllers;

public class KalmanFilter {
    public double state;
    private double processNoiseVariance;

    public void setMeasurementNoiseVariance(double measurementNoiseVariance) {
        this.measurementNoiseVariance = measurementNoiseVariance;
    }

    public void setProcessNoiseVariance(double processNoiseVariance) {
        this.processNoiseVariance = processNoiseVariance;
    }

    private double measurementNoiseVariance;
    private double estimateCovariance;
    private double gainK;
    private double input;
    private double reference;
    private double previousEstimatedPosition, previousEstimationCovariance;

    public double getInput() {
        return input;
    }

    public void setInput(double input) {
        this.input = input;
    }

    public KalmanFilter(double processNoiseVariance, double measurementNoiseVariance, double input, double reference, double gainK) {
        this.processNoiseVariance = processNoiseVariance;
        this.measurementNoiseVariance = measurementNoiseVariance;
        this.input = input;
        this.reference = reference;
        state = 0;
        estimateCovariance = 0;
        this.gainK = gainK;
        previousEstimatedPosition = state;
        previousEstimationCovariance = estimateCovariance;
    }
    public void predict(){
        state = previousEstimatedPosition + input;
        estimateCovariance = previousEstimationCovariance + processNoiseVariance;
    }
    public void update(){
        gainK = estimateCovariance / (estimateCovariance + measurementNoiseVariance);
        state = previousEstimatedPosition + gainK *  (reference - state);
        estimateCovariance = (1 - gainK) * estimateCovariance;
        previousEstimatedPosition = state;
        previousEstimationCovariance = estimateCovariance;
    }

    public double calculate(){
        predict();
        update();
        return state;
    }

    public double getReference() {
        return reference;
    }

    public void setReference(double reference) {
        this.reference = reference;
    }
}
