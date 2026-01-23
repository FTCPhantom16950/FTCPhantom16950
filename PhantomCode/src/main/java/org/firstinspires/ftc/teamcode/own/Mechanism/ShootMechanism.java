package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;
@Config
@Configurable
public class ShootMechanism implements Mechanism {
    public static boolean reversed = true;
    @Override
    public boolean init() {

        DcMotorEx shootMotor = Robot.hw.get(DcMotorEx.class, "shoot");
        shootMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shootMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        if (reversed){
            shootMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        }
        else{
            shootMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        shootMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shootMotor.setPower(0);
        Robot.addOrUpdate("shoot", shootMotor);
        return true;
    }
}
