package org.firstinspires.ftc.teamcode.ftclib.SYS;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HorSliderSYS extends SubsystemBase {
    LinearOpMode opMode;
    public static CRServo sL, sR;
    HardwareMap hw;
    public double startLeftPower = 0, startRightPower = 0;
    public HorSliderSYS(LinearOpMode opMode) {
        this.opMode = opMode;
            hw = opMode.hardwareMap;
            sL = opMode.hardwareMap.get(CRServo.class, "horL");
            sR = opMode.hardwareMap.get(CRServo.class, "horR");
            sL.setPower(startLeftPower);
            sR.setPower(startRightPower);
    }
    public void vidvig(){
        sL.setPower(0.45);
        sR.setPower(-0.45);
    }
    public void zadvig(){
        sL.setPower(startLeftPower);
        sR.setPower(startRightPower);
    }
}
