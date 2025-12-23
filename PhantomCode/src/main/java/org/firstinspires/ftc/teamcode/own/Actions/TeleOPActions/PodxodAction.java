package org.firstinspires.ftc.teamcode.own.Actions.TeleOPActions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

@Configurable
@Config
public class PodxodAction extends Action {
    public static double leftPower = -0.3, shootLeftPower = 0.9;

    @Override
    public void execute() throws InterruptedException {
        CRServo left = Robot.get("left", CRServo.class);
        while (Robot.opMode.opModeIsActive()) {
            if (Robot.gamepadOperator.dpad_right) {
                leftPower = shootLeftPower;
            } else {
                leftPower = -0.35;
            }
            left.setPower(leftPower);
        }
    }
}
