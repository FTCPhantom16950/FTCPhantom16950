package org.firstinspires.ftc.teamcode.own.actions.TestActions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.regulators.FullRegulator;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfCrServo;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

@Configurable
@Config
public class ShootAction implements Action {
    public static double kV = 1 / 6000.0, kA = 0.04, kP = 0.0025, kD = 0, kI = 0, derivativeFilter = 0.5, output = 0, target = 0, motorVelocity = 0;
    private static boolean shooting = false;
    private final FullRegulator fullRegulator = new FullRegulator(kV, kA, kP, kD, kI, derivativeFilter, output, target, motorVelocity);
    private static int servoDegree = 35;
    @Override
    public void execute() throws InterruptedException {
        SfCrServo servo = Robot.INSTANCE.get(SfCrServo.class,"servo");
        SfMotor shoot = Robot.INSTANCE.get(SfMotor.class, "shooter");
        while (Robot.INSTANCE.opMode.opModeIsActive()) {
            if (Robot.INSTANCE.gamepadDriver.b) {
                shooting = !shooting;
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
            if (Robot.INSTANCE.getData("Shooter velocity") != null) {
                motorVelocity = (double) Robot.INSTANCE.getData("Shooter velocity");
            }
            servo.setPower(PhantomMath.servoCRPowerToDegrees(servoDegree,270));
            fullRegulator.setMotorVelocity(motorVelocity);
            output = fullRegulator.calculate() * Robot.INSTANCE.voltageCompenser;
            shoot.setPower(output);
            Robot.addData("target", target);
            Robot.addData("error", target - motorVelocity);
            Robot.addData("shootState", shooting);
            if (Robot.INSTANCE.getData("Shooter velocity") != null) {
                Robot.addData("Shooter velocity", Robot.INSTANCE.getData("Shooter velocity"));
            }
            if (Robot.INSTANCE.getData("Shooter power") != null) {
                Robot.addData("Shooter power", Robot.INSTANCE.getData("Shooter power"));
            }

        }
    }
}
