package org.firstinspires.ftc.teamcode.own.Actions.TeleActions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.Utils.Regulators.PidController;
import org.firstinspires.ftc.teamcode.own.Utils.Regulators.PIDCofficients;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

@Configurable
@Config
public class ShootAction extends Action {
    public static double kP = 0.01, kI = 0, kD = 0.01, targetVelocity = 0, spin = 115;
    PIDCofficients pidCofficients = new PIDCofficients(kP, kI, kD);
    PidController pidController = new PidController(pidCofficients);
    double output, prevOutput;
    boolean makeShoot = false;
    @Override
    public void execute() throws InterruptedException {
        boolean shootState = false;
        DcMotorEx shootMotor = Robot.get("shoot", DcMotorEx.class);
        CRServo vrash = Robot.get("vrash", CRServo.class);
        pidController.setTarget(targetVelocity);
        pidController.setDcMotorEx(shootMotor);
        pidController.start();
        while (Robot.opMode.opModeIsActive()) {
            pidController.setTarget(targetVelocity);
            pidCofficients.setkD(kD);
            pidCofficients.setkP(kP);
            pidController.setPidCofficients(pidCofficients);
            if (Robot.gamepadOperator.b){
                makeShoot = !makeShoot;
                Robot.opMode.sleep(300);
            }
            if (makeShoot){
                output = 1;
            }
            else {
                output = 0;
            }
//            else if (!Robot.gamepadDriver.y) {
//                targetVelocity = 0;
//                output = 0;
//            } else if (Robot.gamepadDriver.a){
//                targetVelocity = -500;
//                output = pidController.getOutput();
//            }else {
//                targetVelocity = 5200;
//                output = pidController.getOutput();
//            }
            shootMotor.setPower(output);
            if (Robot.gamepadOperator.left_stick_button){
                shootState = !shootState;
            }
            if (shootState){
                spin = 145;
            }
            else{
                spin = 110;
            }
            spin = Range.clip(spin, 0, 270);
            vrash.setPower(PhantomMath.servoCRPowerToDegrees(spin, 270));

            PhantomOpMode.addData("shootPower", shootMotor.getPower());
            PhantomOpMode.addData("shootSpeed", shootMotor.getVelocity() / 28 * 60);
            PhantomOpMode.addData("servoPower", vrash.getPower());
            PhantomOpMode.addData("degree", spin);
            prevOutput = output;
        }

    }
}
