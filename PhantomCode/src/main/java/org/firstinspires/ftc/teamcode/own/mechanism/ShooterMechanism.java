package org.firstinspires.ftc.teamcode.own.mechanism;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfCrServo;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;

@Config
@Configurable
public class ShooterMechanism implements Mechanism {
    public static boolean reversed = false, spinMotorEnabled = false;
    public static int startAngelDegree = 135;
    private SfMotor shooterMotor, spinMotor;
    private SfCrServo crServo;
    private SfCrServo servo;


    @Override
    public void init() throws InterruptedException {
        if (spinMotorEnabled) {
            spinMotor = new SfMotor(Robot.INSTANCE.hw.get(DcMotorEx.class, "rotate"), 28);
            spinMotor.setZeroPowerBehaviour(DcMotor.ZeroPowerBehavior.BRAKE);
            spinMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            spinMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            Robot.INSTANCE.addOrUpdate(servo, "rotation");
        } else {
            servo = new SfCrServo(Robot.INSTANCE.hw.get(CRServo.class, "rot"));
            servo.setPower(0);
            Robot.INSTANCE.addOrUpdate(servo, "rot");
        }

        crServo = new SfCrServo(Robot.INSTANCE.hw.get(CRServo.class, "angel"));
        shooterMotor = new SfMotor(Robot.INSTANCE.hw.get(DcMotorEx.class, "shoot"), 28);
        if (reversed) {
            shooterMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        } else {
            shooterMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        crServo.setPower(PhantomMath.servoCRPowerToDegrees(startAngelDegree, 270));
        shooterMotor.setZeroPowerBehaviour(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Robot.INSTANCE.addOrUpdate(crServo, "angelModify");
        Robot.INSTANCE.addOrUpdate(shooterMotor, "shooter");
    }

    @Override
    public void read() {
        Mechanism.super.read();
        Robot.addData("Shooter power", shooterMotor.getPower(), true);
        Robot.addData("Shooter velocity", shooterMotor.getVelocity(), true);
        Robot.addData("Shooter position", shooterMotor.getCurrentPosition(), true);
        Robot.addData("startAngelDegree", startAngelDegree, false);
        Robot.addData("spinMotorEnabled", spinMotorEnabled, false);
    }
}
