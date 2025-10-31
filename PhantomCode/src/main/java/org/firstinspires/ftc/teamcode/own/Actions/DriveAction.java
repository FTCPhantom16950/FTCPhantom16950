package org.firstinspires.ftc.teamcode.own.Actions;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;
import static org.firstinspires.ftc.teamcode.own.Utils.PhantomMath.makeLinearToCubic;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.lb;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.lf;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.rb;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.rf;


import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

import java.util.Arrays;


public class DriveAction extends Action {

    private static IMU imu;
    public DriveAction() {}
    double backRightPower, frontRightPower, backLeftPower, frontLeftPower, denominator;
    private static double x, y, rot;

    Thread motorPower = new Thread(() -> {
        while (opMode.opModeIsActive()) {
            lf.setPower(makeLinearToCubic(frontLeftPower));
            rf.setPower(makeLinearToCubic(frontRightPower));
            rb.setPower(makeLinearToCubic(backRightPower));
            lb.setPower(makeLinearToCubic(backLeftPower));
        }
    });
    Thread encoders = new Thread(()->{
        while (opMode.opModeIsActive()){
            PhantomOpMode.addData("x,y, rot", Arrays.toString(new double[]{2,1,imu.getRobotYawPitchRollAngles().getYaw()}));
            PhantomOpMode.addData("heading y", 1);
            PhantomOpMode.addData("heading x", 2);
            PhantomOpMode.addData("heading (deg)", imu.getRobotYawPitchRollAngles().getYaw());
//            PhantomOpMode.packet.put("Pose heading (deg)", imu.getRobotYawPitchRollAngles().getYaw());
//            PhantomOpMode.addData("x",x);
//            PhantomOpMode.addData("y",y);
            PhantomOpMode.addData("rot",rot);
        }
    });
    @Override
    public void execute() {
        imu = Robot.get("imu", IMU.class);
        while (opMode.opModeIsActive()) {
            x = 1.1 * gamepadDriver.left_stick_x + 1.1 * 0.8 * gamepadDriver.right_stick_x;
            y = -gamepadDriver.left_stick_y - 0.8 * gamepadDriver.right_stick_y;
            rot = gamepadDriver.left_trigger - gamepadDriver.right_trigger;
            x = makeLinearToCubic(x);
            y = makeLinearToCubic(y);
            rot = makeLinearToCubic(rot);
            if (gamepadDriver.right_bumper) {
                rot = 0.8;
            } else if (gamepadDriver.left_bumper) {
                rot = -0.8;
            }
            denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rot), 1);
            frontLeftPower = (y + x + rot) / denominator;
            backLeftPower = (y - x + rot) / denominator;
            frontRightPower = (y - x - rot) / denominator;
            backRightPower = (y + x - rot) / denominator;
            if (!motorPower.isAlive()) {
                motorPower.start();
            }
            if (!encoders.isAlive()) {
                encoders.start();
            }
        }
    }
}
