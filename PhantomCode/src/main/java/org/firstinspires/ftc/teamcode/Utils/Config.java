package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@com.acmerobotics.dashboard.config.Config
public class Config {
    public PIDFCoefficients pidfCoefficients = new PIDFCoefficients(0,0,0,0);
    public double power = 0;
    public double distance = 0;
}
