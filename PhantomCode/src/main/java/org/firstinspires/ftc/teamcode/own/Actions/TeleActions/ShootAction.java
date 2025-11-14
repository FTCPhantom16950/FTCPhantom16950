package org.firstinspires.ftc.teamcode.own.Actions.TeleActions;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.Utils.Regulators.PIDCofficients;
import org.firstinspires.ftc.teamcode.own.Utils.Regulators.PIDController;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;
@Configurable
public class ShootAction extends Action {
    public static double kP = 1, ki = 0, kd = 0, targetVelocity = 0;
    PIDCofficients pidCofficients = new PIDCofficients(kP,ki,kd);
    PIDController pidController = new PIDController(pidCofficients);

    @Override
    public void execute() {
        DcMotorEx shootMotor = Robot.get("shoot", DcMotorEx.class);
        pidController.start();
        while (Robot.opMode.opModeIsActive()){
            pidCofficients.setkP(kP);
            pidCofficients.setkP(kd);
            pidController.setPidCofficients(pidCofficients);
            pidController.setCurrentError(targetVelocity - shootMotor.getVelocity());
            if (Robot.gamepadDriver.y){
                targetVelocity += 1000;
                Robot.opMode.sleep(300);
            }
            else if (Robot.gamepadDriver.a){
                targetVelocity -= 1000;
                Robot.opMode.sleep(300);
            }
            shootMotor.setPower(pidController.getOutput());
            PhantomOpMode.addData("shootPower", shootMotor.getPower());
            PhantomOpMode.addData("shootVelocity", shootMotor.getVelocity());
        }
    }
}
