package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Mechanism.WheelBase;

public class Robot {
    WheelBase wheelBase = new WheelBase();
    PhantomIMU phantomIMU = new PhantomIMU();
    public void initAll(HardwareMap hardwareMap){
        wheelBase.initWheel(hardwareMap);
        phantomIMU.initIMU(hardwareMap);
    }
}
