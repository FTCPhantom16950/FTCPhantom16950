package org.firstinspires.ftc.teamcode.own.Actions.TeleActions;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

public class SosaloAction extends Action {
    @Override
    public void execute() throws InterruptedException {
        DcMotorEx zasos = Robot.get("zasos", DcMotorEx.class);
        boolean state = false;
        while (Robot.opMode.opModeIsActive()){
            if (Robot.gamepadOperator.x){
                state = !state;
                Robot.opMode.sleep(300);
            }

            if (state){
                zasos.setPower(1);
            }
            else if (Robot.gamepadOperator.y){
              zasos.setPower(-1);
              Robot.opMode.sleep(300);
            }
            else{
                zasos.setPower(0);
            }
        }
    }
}
