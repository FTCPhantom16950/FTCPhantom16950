package org.firstinspires.ftc.teamcode.own.actions.testactions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.regulators.FullRegulator;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfCrServo;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;

@Configurable
@Config
public class ShootTestAction implements Action {

    SfMotor rotation;
    private boolean shooting = false;
    private boolean podem = false;

    @Override
    public void execute() throws InterruptedException {
        Robot.INSTANCE.addData("shooting", shooting);
        Robot.INSTANCE.addData("podem", podem);
        rotation = Robot.INSTANCE.get(SfMotor.class, "rotation");

        while (Robot.INSTANCE.opMode.opModeIsActive()) {
            if (Robot.INSTANCE.gamepadOperator.b ) {
                shooting = !shooting;
                Robot.INSTANCE.addData("shooting", shooting);
                Robot.INSTANCE.opMode.sleep(300);
            }
            if (Robot.INSTANCE.gamepadOperator.dpad_up) {
                podem = !podem;
                Robot.INSTANCE.addData("podem", podem);
                Robot.INSTANCE.opMode.sleep(300);
            }


            if (Robot.INSTANCE.gamepadOperator.dpad_left && rotation.getCurrentPosition() >= -1030) {
                rotation.setPower(1);
            } else if (Robot.INSTANCE.gamepadOperator.dpad_right && rotation.getCurrentPosition() <= 1488) {
                rotation.setPower(-1);
            } else {
                rotation.setPower(0);
            }

        }
    }
}
