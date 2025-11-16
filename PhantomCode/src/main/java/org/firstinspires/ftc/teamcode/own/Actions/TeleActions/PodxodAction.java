package org.firstinspires.ftc.teamcode.own.Actions.TeleActions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

@Configurable
@Config
public class PodxodAction extends Action {
    public static double leftPower = 0, rightPower = 0, shootLeftPower = 0.8;

    @Override
    public void execute() throws InterruptedException {
        CRServo left = Robot.get("left", CRServo.class), right = Robot.get("right", CRServo.class);
        while (Robot.opMode.opModeIsActive()) {
            if (Robot.gamepadDriver.dpad_right) {
                leftPower = shootLeftPower;
            } else {
                leftPower = 0;
            }
            if (Robot.gamepadDriver.dpad_left) {
                rightPower = -shootLeftPower;
            } else {
                rightPower = 0;
            }
            left.setPower(leftPower);
            right.setPower(rightPower);
        }
    }
}
