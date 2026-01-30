package org.firstinspires.ftc.teamcode.own.actions.testactions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.regulators.FullRegulator;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfCrServo;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;

@Configurable
@Config
public class ShootTestAction implements Action {
    public static double kV = 1 / 6000.0, kA = 0.04, kP = 0.0025, kD = 0, kI = 0, derivativeFilter = 0.5, output = 0, target = 5000, motorVelocity = 0;
    public static int servoDegree = 270;
    private final FullRegulator fullRegulator = new FullRegulator(kV, kA, kP, kD, kI, derivativeFilter, output, target, motorVelocity);
    SfCrServo rot;
    SfMotor rotation;
    private boolean shooting = false;
    private boolean podem = false;

    @Override
    public void execute() throws InterruptedException {
        if (Robot.INSTANCE.getData(Boolean.class,"spinMotorEnabled")) {
            rotation = Robot.INSTANCE.get(SfMotor.class, "rotation");
        } else {
            rot = Robot.INSTANCE.get(SfCrServo.class, "rot");
        }

        SfCrServo angel = Robot.INSTANCE.get(SfCrServo.class, "angelModify");
        SfMotor shoot = Robot.INSTANCE.get(SfMotor.class, "shooter");
        while (Robot.INSTANCE.opMode.opModeIsActive()) {
            if (Robot.INSTANCE.gamepadOperator.b) {
                shooting = !shooting;
                Robot.INSTANCE.addData("shooting", shooting);
                Robot.INSTANCE.opMode.sleep(300);
            }
            if (Robot.INSTANCE.gamepadOperator.dpad_up) {
                podem = !podem;
                Robot.INSTANCE.opMode.sleep(300);
            }
            if (shooting) {
                fullRegulator.setTarget(target);
            } else {
                fullRegulator.setTarget(0);
            }
            fullRegulator.setkA(kA);
            fullRegulator.setkV(kV);
            fullRegulator.setkD(kD);
            fullRegulator.setkI(kI);
            fullRegulator.setkP(kP);
            fullRegulator.setDerivativeFilter(derivativeFilter);
            motorVelocity = shoot.getVelocity();

            fullRegulator.setMotorVelocity(motorVelocity);
            output = fullRegulator.calculate();
            shoot.setPower(output);
            if (Robot.INSTANCE.getData(Boolean.class,"spinMotorEnabled")) {
                if (Robot.INSTANCE.gamepadOperator.dpad_left) {
                    rotation.setPower(1);
                } else if (Robot.INSTANCE.gamepadOperator.dpad_right) {
                    rotation.setPower(-1);
                } else {
                    rotation.setPower(0);
                }
            } else {
                if (Robot.INSTANCE.gamepadOperator.dpad_left) {
                    rot.setPower(1);
                } else if (Robot.INSTANCE.gamepadOperator.dpad_right) {
                    rot.setPower(-1);
                } else {
                    rot.setPower(0);
                }
            }
            if (podem) {
                angel.setPower(PhantomMath.servoCRPowerToDegrees(servoDegree, 270));
            } else {
                angel.setPower(PhantomMath.servoCRPowerToDegrees( Robot.INSTANCE.getData(Integer.class,"startAngelDegree"),270));
            }
        }
    }
}
