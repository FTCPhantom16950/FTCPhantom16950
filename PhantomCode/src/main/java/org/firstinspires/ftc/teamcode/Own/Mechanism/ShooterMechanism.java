package org.firstinspires.ftc.teamcode.Own.Mechanism;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomMath;
import org.firstinspires.ftc.teamcode.Own.Utils.Robot;
import org.firstinspires.ftc.teamcode.Own.Utils.SafeHardware.SfMotor;

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
        Robot.addData("Shooter power", shooterMotor.getPower());
        Robot.addData("Shooter velocity", shooterMotor.getVelocity());
        Robot.addTelemetryData("Shooter power", shooterMotor.getPower());
        Robot.addTelemetryData("Shooter velocity", PhantomMath.convertToRPM(shooterMotor.getVelocity(), 28));
        Robot.addTelemetryData("Shooter position", shooterMotor.getCurrentPosition());
    }
}
