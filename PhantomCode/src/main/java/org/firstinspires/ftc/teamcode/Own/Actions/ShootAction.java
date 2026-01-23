package org.firstinspires.ftc.teamcode.Own.Actions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.Own.Utils.Action.Groups.Action;
import org.firstinspires.ftc.teamcode.Own.Utils.Regulators.FullRegulator;
import org.firstinspires.ftc.teamcode.Own.Utils.Robot;
import org.firstinspires.ftc.teamcode.Own.Utils.SafeHardware.SfMotor;
@Configurable
@Config
public class ShootAction implements Action {
    public static double kV = 0, kA = 0, kP = 0 , kD = 0 , kI = 0, derivativeFilter = 0.5, output = 0, target = 0, motorVelocity = 0;
    private final FullRegulator fullRegulator = new FullRegulator(kV,kA,kP,kD,kI,derivativeFilter,output,target,motorVelocity);
    private static boolean shooting = false;
    @Override
    public void execute() throws InterruptedException {
        SfMotor shoot = Robot.INSTANCE.get(SfMotor.class, "shooter");
        while(Robot.INSTANCE.opMode.opModeIsActive()){
            if (Robot.INSTANCE.gamepadDriver.b){
                shooting = !shooting;
            }
            if (shooting){
                target = 3700;
            } else{
                target = 0;
            }
            fullRegulator.setkA(kA);
            fullRegulator.setkV(kV);
            fullRegulator.setkD(kD);
            fullRegulator.setkI(kI);
            fullRegulator.setkP(kP);
            fullRegulator.setTarget(target);
            fullRegulator.setDerivativeFilter(derivativeFilter);
            fullRegulator.setMotorVelocity((double) Robot.INSTANCE.getData("Shooter velocity"));
            output = fullRegulator.calculate();
            shoot.setPower(output);
            Robot.addTelemetryData("Shooter velocity",Robot.INSTANCE.getData("Shooter velocity"));
            Robot.addTelemetryData("Shooter power",Robot.INSTANCE.getData("Shooter power"));
        }
    }
}
