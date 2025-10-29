package org.firstinspires.ftc.teamcode.own.Actions;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

public class PodemAction extends Action {
    private static DcMotorEx podem;
    @Override
    public void execute() {
        podem = Robot.get("podem", DcMotorEx.class);
        while(opMode.opModeIsActive()){
            if (gamepadDriver.dpad_up){
                podem.setPower(1);
            }
            else if(gamepadDriver.dpad_down){
                podem.setPower(-1);
            }else{
                podem.setPower(0.01);
            }
        }
    }
}
