package org.firstinspires.ftc.teamcode.own.mechanism;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;
@Configurable
@Config
public class CaptureMechanism implements Mechanism {
    private SfMotor capture;
    public static boolean reversed = false;
    @Override
    public void init() throws InterruptedException {
        capture = new SfMotor(Robot.INSTANCE.hw.get(DcMotorEx.class, "capture"), 28);
        if (reversed){
            capture.setDirection(DcMotorSimple.Direction.REVERSE);
        } else {
            capture.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        capture.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        capture.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        capture.setZeroPowerBehaviour(DcMotor.ZeroPowerBehavior.BRAKE);
        Robot.INSTANCE.addOrUpdate(capture, "capture");
    }

    @Override
    public void read() {
        Mechanism.super.read();
    }
}
