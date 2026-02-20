package org.firstinspires.ftc.teamcode.own.actions.utilactions;

import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.regulators.FullRegulator;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfCrServo;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;

public class ShootPosSet implements Action {
    public static double kV = 1 / 6000.0, kA = 0.04, kP = 0.0025, kD = 0, kI = 0, derivativeFilter = 0.5, output = 0, target = 3500, motorVelocity = 0;
    public static int servoDegree = 270;
    private final FullRegulator fullRegulator = new FullRegulator(kV, kA, kP, kD, kI, derivativeFilter, output, target, motorVelocity);
    @Override
    public void execute() throws InterruptedException {
        SfCrServo angel = Robot.INSTANCE.get(SfCrServo.class, "angelModify");
        SfMotor shoot = Robot.INSTANCE.get(SfMotor.class, "shooter");
        while (Robot.INSTANCE.opMode.opModeIsActive()) {
            if (Robot.INSTANCE.getData(Boolean.class, "shooting")) {
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
            if (Robot.INSTANCE.getData(Boolean.class, "podem")) {
                angel.setPower(PhantomMath.servoCRPowerToDegrees(servoDegree, 270));
            } else {
                angel.setPower(PhantomMath.servoCRPowerToDegrees(Robot.INSTANCE.getData(Integer.class, "startAngelDegree"), 270));
            }
        }
    }
}
