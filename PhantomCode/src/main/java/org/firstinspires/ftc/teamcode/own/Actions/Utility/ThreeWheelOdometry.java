package org.firstinspires.ftc.teamcode.own.Actions.Utility;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

// all numbers in mm or radians
public class ThreeWheelOdometry extends Action {
    public static DcMotorEx leftOdo, rightOdo, sideOdo;
    double currLeft, currRight, currSide, prevLeft, prevRight, prevSide;
    double wheelRadius = 48.0; //mm
    double yParallel = 170,
            xSide = 1;
    ElapsedTime timer = new ElapsedTime();
    double prevTime;
    double dx, dy = 0, dRot;
    double ticksPerResolution = 2000;

    @Override
    public void execute() {
        leftOdo = Robot.get("mkL", DcMotorEx.class);
        rightOdo = Robot.get("mkR", DcMotorEx.class);
//        sideOdo = Robot.get("rf", DcMotorEx.class);
        timer.reset();
        prevLeft = leftOdo.getCurrentPosition();
        prevRight = rightOdo.getCurrentPosition();
//        prevSide = sideOdo.getCurrentPosition();
        prevTime = timer.seconds();
        while (opMode.opModeIsActive()) {
            currLeft = leftOdo.getCurrentPosition() * 2.0 * Math.PI / ticksPerResolution;
            currRight = rightOdo.getCurrentPosition() * 2.0 * Math.PI / ticksPerResolution;
//            currSide = sideOdo.getCurrentPosition()* 2.0* Math.PI / ticksPerResolution;
            double dLeft = currLeft - prevLeft,
                    dRight = currRight - prevRight,
//            dSide = currSide -prevSide;
                    dx = (wheelRadius / 2.0) * (dLeft + dRight);
//            dy = wheelRadius * ((xSide /  (2 * yParallel))* (dLeft-dRight) + dSide);
            dRot = (wheelRadius / (2 * yParallel)) * (dRight - dLeft);
            double time = timer.seconds();
            double dt = time - prevTime;
            prevLeft = currLeft;
            prevRight = currRight;
            prevSide = currSide;
            prevTime = time;
            if (dt <= 0) {
                continue;
            }
            rot = dRot + imu.getRobotYawPitchRollAngles().getYaw() / 2;
            x += dx * Math.cos(rot) - dy * Math.sin(rot);
            y += dx * Math.sin(rot) + dy * Math.cos(rot);
            vx = dx / dt;
            vy = dy / dt;
            vRot = dRot / dt;
            PhantomOpMode.addData("vx", vx / 100);
        }
    }

    public DcMotorEx getSideOdo() {
        return sideOdo;
    }

    public void setSideOdo(DcMotorEx sideOdo) {
        this.sideOdo = sideOdo;
    }

    public DcMotorEx getRightOdo() {
        return rightOdo;
    }

    public void setRightOdo(DcMotorEx rightOdo) {
        this.rightOdo = rightOdo;
    }

    public DcMotorEx getLeftOdo() {
        return leftOdo;
    }

    public void setLeftOdo(DcMotorEx leftOdo) {
        this.leftOdo = leftOdo;
    }
}
