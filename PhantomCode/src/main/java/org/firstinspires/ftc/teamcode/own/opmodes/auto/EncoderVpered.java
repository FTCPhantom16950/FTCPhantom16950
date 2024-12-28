package org.firstinspires.ftc.teamcode.own.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;

@Autonomous
@Disabled
public class EncoderVpered extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        WheelBase wheelBase = new WheelBase(this);
        wheelBase.initWheelBase(hardwareMap);
        waitForStart();
        if(opModeIsActive()){
            wheelBase.vperedEncoder(1000, 0.3);
            wheelBase.nazadEncoder(2000, 0.3);
            wheelBase.razvarotEncoder(1000, 0.3);
        }
    }
}
