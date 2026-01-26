package org.firstinspires.ftc.teamcode.own.actions.testactions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;
@Configurable
@Config
public class CaptureTestAction implements Action {
    public static double power = 1;
    private boolean capturing = false;
    SfMotor capture;
    @Override
    public void execute() throws InterruptedException {
        capture = Robot.INSTANCE.get(SfMotor.class, "capture");
        while (Robot.INSTANCE.opMode.opModeIsActive()){
            if (Robot.INSTANCE.gamepadOperator.x){
                capturing = !capturing;
                Robot.INSTANCE.opMode.sleep(300);
            }
            if (capturing){
                capture.setPower(power);
            } else {
                capture.setPower(0);
            }
        }
    }
}
