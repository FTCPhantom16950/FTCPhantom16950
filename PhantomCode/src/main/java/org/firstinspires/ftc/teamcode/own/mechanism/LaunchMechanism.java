package org.firstinspires.ftc.teamcode.own.mechanism;

import com.acmerobotics.dashboard.config.Config;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.states.AngleState;
import org.firstinspires.ftc.teamcode.own.utils.states.LauncherState;
import org.firstinspires.ftc.teamcode.own.utils.states.RotateState;
import org.firstinspires.ftc.teamcode.own.utils.states.UpperState;

@Config
public class LaunchMechanism implements Mechanism {
    DcMotorEx rotate, launcher;
    CRServo angle, upper;
    HardwareMap hw;
    AngleState angleState;
    UpperState upperState;
    public static boolean reversedLauncher = true, reversedRotate = false;
    public static int angleStartDegree = 0, upperStartDegree = 135;
    RotateState rotateState;
    LauncherState launcherState;


    @Override
    public void init() throws InterruptedException {
        hw = Robot.INSTANCE.getRobotData("HardwareMap" , HardwareMap.class);

        rotate = hw.get(DcMotorEx.class, "rotate");
        launcher = hw.get(DcMotorEx.class, "shoot");
        angle = hw.get(CRServo.class, "angel");
        upper = hw.get(CRServo.class, "pal");
//        distanceLeft = hw.get(Rev2mDistanceSensor.class, "distanceLeft");
//        distanceRight = hw.get(Rev2mDistanceSensor.class, "distanceRight");

        rotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launcher.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        rotate.setPower(0);
        rotateState = RotateState.STOP;
        launcher.setPower(0);
        launcherState = LauncherState.STOP;

        angleState = AngleState.DOWN;
        angle.setPower(PhantomMath.servoCRPowerToDegrees(angleStartDegree,270));
        upperState = UpperState.DOWN;
        upper.setPower(PhantomMath.servoCRPowerToDegrees(upperStartDegree,270));

        if (reversedLauncher){
            launcher.setDirection(DcMotorSimple.Direction.REVERSE);
        } else {
            launcher.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        if (reversedRotate){
            rotate.setDirection(DcMotorSimple.Direction.REVERSE);
        } else {
            rotate.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        Robot.INSTANCE.addData("RotateState", rotateState);
        Robot.INSTANCE.addData("LauncherState", launcherState);
        Robot.INSTANCE.addData("AngleState", angleState);
        Robot.INSTANCE.addData("UpperState", upperState);
        Robot.INSTANCE.addData("angleStartDegree", angleStartDegree);
        Robot.INSTANCE.addData("upperStartDegree", upperStartDegree);
        Robot.INSTANCE.addData("offsetBLUE", 0.0);
        Robot.INSTANCE.addData("offsetRED", 0.0);
        Robot.INSTANCE.addData("RotateInUse", false);
        Robot.INSTANCE.addData("velocityShooter",  PhantomMath.convertToRPM(launcher.getVelocity(), 28));

        Robot.INSTANCE.addRobotDevice("rotate", rotate);
        Robot.INSTANCE.addRobotDevice("launcher", launcher);
        Robot.INSTANCE.addRobotDevice("angle", angle);
        Robot.INSTANCE.addRobotDevice("upper", upper);
        Robot.INSTANCE.addData("AutoLaunch", false);
//        Robot.INSTANCE.addRobotDevice("distanceLeft", distanceLeft);
//        Robot.INSTANCE.addRobotDevice("distanceRight", distanceRight);

    }
}
