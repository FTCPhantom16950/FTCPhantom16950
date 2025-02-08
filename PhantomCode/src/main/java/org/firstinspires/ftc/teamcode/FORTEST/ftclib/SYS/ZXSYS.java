package org.firstinspires.ftc.teamcode.FORTEST.ftclib.SYS;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ZXSYS extends SubsystemBase {
    static LinearOpMode opMode;
    public static CRServo zx, krut, krut2;
    public static final double krut_start_power = -0.3;
    public static final double krut2_start_power = -0.3;
    private static final double zx_start_power = 0;
    HardwareMap hw;
    public ZXSYS(LinearOpMode opMode){
        ZXSYS.opMode = opMode;
        hw = opMode.hardwareMap;
        zx = opMode.hardwareMap.get(CRServo.class, "zx");
        krut= opMode.hardwareMap.get(CRServo.class, "krut");
        krut2 = opMode.hardwareMap.get(CRServo.class, "vrash2");
        krut2.setDirection(DcMotorSimple.Direction.REVERSE);
        zx.setPower(zx_start_power);
        krut.setPower(krut_start_power);
        krut2.setPower(krut2_start_power);
    }
    public void zxZaxvat(){
        zx.setPower(0.23);
    }
    public void zxOtpusk(){
        zx.setPower(-0.33);
    }
    public void krutZaxvat(){
        krut.setPower(0.67);
        krut2.setPower(0.42);
    }
    public void krutOtpusk(){
        krut.setPower(-0.4);
        krut2.setPower(-0.4);
    }

}
