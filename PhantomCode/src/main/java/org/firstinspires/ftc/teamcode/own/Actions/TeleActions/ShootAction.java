package org.firstinspires.ftc.teamcode.own.Actions.TeleActions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

public class ShootAction extends Action {
    @Override
    public void execute() {
        double power = 1;
        DcMotorEx shootMotor = Robot.get("shoot", DcMotorEx.class);
        while (Robot.opMode.opModeIsActive()){
            if (Robot.gamepadDriver.y){
                power += 0.1;
                Robot.opMode.sleep(300);
            }
            else if (Robot.gamepadDriver.a){
                power += 0.1;
                Robot.opMode.sleep(300);
            }
            shootMotor.setPower(power);
            PhantomOpMode.addData("shootPower", power);
        }
    }
}
