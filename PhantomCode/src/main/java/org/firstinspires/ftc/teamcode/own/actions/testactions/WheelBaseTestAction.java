package org.firstinspires.ftc.teamcode.own.actions.testactions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;
@Config
@Configurable
public class WheelBaseTestAction implements Action {
    public static boolean center = false;

    @Override
    public void execute() throws InterruptedException {
        SfMotor rb = Robot.INSTANCE.get(SfMotor.class, "rb");
        SfMotor lb = Robot.INSTANCE.get(SfMotor.class, "lb");
        SfMotor rf = Robot.INSTANCE.get(SfMotor.class, "rf");
        SfMotor lf = Robot.INSTANCE.get(SfMotor.class, "lf");
        while (Robot.INSTANCE.opMode.opModeIsActive()){
            double targetX = (double) Robot.INSTANCE.getData("Gx");
            double targetY = (double) Robot.INSTANCE.getData("Gy");
            double targetRot = (double) Robot.INSTANCE.getData("Gr");
            double denominator;
            if (center){
                if (Robot.INSTANCE.gamepadDriver.options) {
                    Robot.INSTANCE.imu.resetYaw();
                }
                double robotAngle = Robot.INSTANCE.rot;
                double rotX = targetX * Math.cos(-robotAngle) - targetY * Math.sin(-robotAngle);
                double rotY = targetX * Math.sin(-robotAngle) + targetY * Math.cos(-robotAngle);
                rotX = rotX * 1.1;
                targetX = rotX;
                targetY = rotY;
            }
                denominator = Math.max(Math.abs(targetX) + Math.abs(targetY) + Math.abs(targetRot), 1);
                double frontLeftPower = (targetY + targetX + targetRot) / denominator;
                double backLeftPower = (targetY - targetX + targetRot) / denominator;
                double frontRightPower = (targetY - targetX - targetRot) / denominator;
                double backRightPower = (targetY + targetX - targetRot) / denominator;
                rb.setPower(backRightPower);
                lb.setPower(backLeftPower);
                rf.setPower(frontRightPower);
                lf.setPower(frontLeftPower);

        }
    }
}
