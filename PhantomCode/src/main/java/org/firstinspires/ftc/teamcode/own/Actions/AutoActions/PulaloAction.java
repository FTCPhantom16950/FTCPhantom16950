package org.firstinspires.ftc.teamcode.own.Actions.AutoActions;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

public class PulaloAction extends Action {
    @Override
    public void execute() throws InterruptedException {
        CRServo lapka =Robot.get("left",CRServo.class);
        DcMotorEx pulalo = Robot.get("shoot",DcMotorEx.class);
        pulalo.setPower(1);
        while (PhantomMath.convertToRPM(pulalo.getVelocity(), 28) < 2900){

        }
        lapka.setPower(0.9);
        Robot.opMode.sleep(1000);
        pulalo.setPower(0);
        lapka.setPower(-0.35);
    }
}
