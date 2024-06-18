package org.firstinspires.ftc.teamcode.Mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class WheelBase {
    DcMotorEx rR, rF, lR, lF;
    public void initWheel(HardwareMap hardwareMap){
        rR = hardwareMap.get(DcMotorEx.class,"rr");
        lR = hardwareMap.get(DcMotorEx.class, "lr");
        rF = hardwareMap.get(DcMotorEx.class, "rf");
        lF = hardwareMap.get(DcMotorEx.class, "lf");
        rR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
