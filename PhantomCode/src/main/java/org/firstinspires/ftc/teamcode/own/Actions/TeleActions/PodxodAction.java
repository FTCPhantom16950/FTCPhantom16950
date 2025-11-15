package org.firstinspires.ftc.teamcode.own.Actions.TeleActions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;
@Configurable
@Config
public class PodxodAction extends Action {
    public static double leftPower = 0, rightPower = 0;
    @Override
    public void execute() throws InterruptedException {

        CRServo left = Robot.get("left", CRServo.class)
                , right = Robot.get("right", CRServo.class);
        while (Robot.opMode.opModeIsActive()){
            if (Robot.gamepadDriver.dpad_left){
                leftPower += 0.1;
                Robot.opMode.sleep(300);
            }
            if (Robot.gamepadDriver.dpad_right){
                leftPower -= 0.1;
                Robot.opMode.sleep(300);
            }
            if (Robot.gamepadDriver.left_stick_button){
                rightPower += 0.1;
                Robot.opMode.sleep(300);
            }
            if (Robot.gamepadDriver.right_stick_button){
                rightPower -= 0.1;
                Robot.opMode.sleep(300);
            }
            left.setPower(leftPower);
            right.setPower(rightPower);
        }
    }
}
