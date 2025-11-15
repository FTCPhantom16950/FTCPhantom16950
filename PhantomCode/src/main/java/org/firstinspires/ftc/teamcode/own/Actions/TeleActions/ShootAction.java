package org.firstinspires.ftc.teamcode.own.Actions.TeleActions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.Utils.Regulators.PIDCofficients;
import org.firstinspires.ftc.teamcode.own.Utils.Regulators.PIDController;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

@Configurable
@Config
public class ShootAction extends Action {
    // 5200
    public static double kp = 0.0001, kd = 0.0001, target = 1000;
    PIDCofficients pidCofficients = new PIDCofficients(kp, kd);
    private final PIDController pidController = new PIDController(pidCofficients);

    @Override
    public void execute() throws InterruptedException {
        double spin = 135;
        DcMotorEx shootMotor = Robot.get("shoot", DcMotorEx.class);
        CRServo vrash = Robot.get("vrash", CRServo.class);
        pidController.setMaxPower(5200);
        pidController.start();
        while (Robot.opMode.opModeIsActive()) {
//            if (target - shootMotor.getVelocity() != 0){
//                synchronized (pidController){
//                    pidController.wait();
//                }
//            }else {
//                synchronized (pidController){
//                    pidController.notify();
//                }
//            }
            pidCofficients.setkD(kd);
            pidCofficients.setkP(kp);
            pidController.setPidCofficients(pidCofficients);

            pidController.setCurrentError(target - shootMotor.getVelocity());


            if (Robot.gamepadDriver.y) {
                target += 1000;
                Robot.opMode.sleep(300);
            } else if (Robot.gamepadDriver.a) {
                target -= 1000;
                Robot.opMode.sleep(300);
            }

            if (Robot.gamepadDriver.x) {
                spin += 10;
                Robot.opMode.sleep(300);
            } else if (Robot.gamepadDriver.b) {
                spin -= 10;
                Robot.opMode.sleep(300);
            }
            spin = Range.clip(spin, 0, 270);

            vrash.setPower(PhantomMath.servoCRPowerToDegrees(spin, 270));
            shootMotor.setPower(pidController.getOutput());

            PhantomOpMode.addData("shootPower", shootMotor.getPower());
            PhantomOpMode.addData("shootSpeed", shootMotor.getVelocity() / 28 * 60);
            PhantomOpMode.addData("servoPower", vrash.getPower());
            PhantomOpMode.addData("degree", spin);
        }
    }
}
