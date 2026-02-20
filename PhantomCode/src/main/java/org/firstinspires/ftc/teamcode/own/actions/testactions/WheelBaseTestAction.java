package org.firstinspires.ftc.teamcode.own.actions.testactions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;
@Config
@Configurable
public class WheelBaseTestAction implements Action {
    public static boolean center = false;
    double targetX, targetY, targetRot, denominator,robotAngle,rotX,rotY;
    double frontLeftPower,backLeftPower,frontRightPower,backRightPower;
    @Override
    public void execute() throws InterruptedException {
        SfMotor rb = Robot.INSTANCE.get(SfMotor.class, "rb");
        SfMotor lb = Robot.INSTANCE.get(SfMotor.class, "lb");
        SfMotor rf = Robot.INSTANCE.get(SfMotor.class, "rf");
        SfMotor lf = Robot.INSTANCE.get(SfMotor.class, "lf");
        while (Robot.INSTANCE.opMode.opModeIsActive()){
            targetX = PhantomMath.makeLinearToCubic(-Robot.INSTANCE.gamepadDriver.left_stick_x - Robot.INSTANCE.gamepadDriver.right_stick_x * 0.8);
            targetY = PhantomMath.makeLinearToCubic(-Robot.INSTANCE.gamepadDriver.left_stick_y - Robot.INSTANCE.gamepadDriver.right_stick_y * 0.8);
            targetRot = Robot.INSTANCE.gamepadDriver.right_trigger - Robot.INSTANCE.gamepadDriver.left_trigger;
            if (center){
                if (Robot.INSTANCE.gamepadDriver.options) {
                    Robot.INSTANCE.imu.resetYaw();
                }
                robotAngle = Robot.INSTANCE.rot;
                rotX = targetX * Math.cos(-robotAngle) - targetY * Math.sin(-robotAngle);
                rotY = targetX * Math.sin(-robotAngle) + targetY * Math.cos(-robotAngle);
                targetX = rotX;
                targetY = rotY;
            }
                denominator = Math.max(Math.abs(targetX) + Math.abs(targetY) + Math.abs(targetRot), 1);
                frontLeftPower = (targetY + targetX + targetRot) / denominator;
                backLeftPower = (targetY - targetX + targetRot) / denominator;
                frontRightPower = (targetY - targetX - targetRot) / denominator;
                backRightPower = (targetY + targetX - targetRot) / denominator;
                rb.setPower(backRightPower);
                lb.setPower(backLeftPower);
                rf.setPower(frontRightPower);
                lf.setPower(frontLeftPower);

        }
    }
}
