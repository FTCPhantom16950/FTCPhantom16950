package org.firstinspires.ftc.teamcode.own.mechanism;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;

@Config
@Configurable
public class ShooterMechanism implements Mechanism {
    public static boolean reversed = false;
    private SfMotor shooterMotor;

    @Override
    public void init() throws InterruptedException {
        shooterMotor = new SfMotor(Robot.INSTANCE.hw.get(DcMotorEx.class, "shoot"),28);
        if (reversed) {
            shooterMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        } else {
            shooterMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        shooterMotor.setZeroPowerBehaviour(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Robot.INSTANCE.addOrUpdate(shooterMotor, "shooter");
    }

    @Override
    public void read() {
        Mechanism.super.read();
        Robot.addData("Shooter power", shooterMotor.getPower(), true);
        Robot.addData("Shooter velocity", shooterMotor.getVelocity(), true);
        Robot.addData("Shooter position", shooterMotor.getCurrentPosition(), true);
    }
}
