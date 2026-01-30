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
public class ReznaMechanism implements Mechanism {
    SfMotor podem;
    public static boolean reversed = false;
    @Override
    public void init() throws InterruptedException {
        podem = new SfMotor(Robot.INSTANCE.hw.get(DcMotorEx.class, "podem"), 28);
        podem.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        podem.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        podem.setZeroPowerBehaviour(DcMotor.ZeroPowerBehavior.BRAKE);

        if (reversed){
            podem.setDirection(DcMotorSimple.Direction.REVERSE);
        } else{
            podem.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        Robot.INSTANCE.addOrUpdate(podem, "rezna");
    }

    @Override
    public void read() {
        Mechanism.super.read();
        Robot.INSTANCE.addData("podem pos", podem.getCurrentPosition());
    }
}
