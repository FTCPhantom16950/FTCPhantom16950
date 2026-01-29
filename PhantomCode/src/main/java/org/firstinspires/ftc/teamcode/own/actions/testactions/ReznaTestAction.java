package org.firstinspires.ftc.teamcode.own.actions.testactions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;
@Config
@Configurable
public class ReznaTestAction implements Action {
    public static double power = 1;
    @Override
    public void execute() throws InterruptedException {
        SfMotor rezna = Robot.INSTANCE.get(SfMotor.class, "rezna");
        while (Robot.INSTANCE.opMode.opModeIsActive()) {
            if (Robot.INSTANCE.gamepadDriver.y) {
                rezna.setPower(power);
            } else if (Robot.INSTANCE.gamepadDriver.a) {
                rezna.setPower(-power);
            } else {
                rezna.setPower(0);
            }
        }
    }
}
