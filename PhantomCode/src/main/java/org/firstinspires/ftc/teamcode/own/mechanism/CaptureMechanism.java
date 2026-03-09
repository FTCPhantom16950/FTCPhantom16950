package org.firstinspires.ftc.teamcode.own.mechanism;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.states.ArtifactColor;
import org.firstinspires.ftc.teamcode.own.utils.states.CapturingState;
import org.firstinspires.ftc.teamcode.own.utils.states.RevolverStates;
import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
@Config
public class CaptureMechanism implements Mechanism {
    DcMotorEx capture;
    CRServo revolver;
    HardwareMap hw;
    RevolverStates state;
    CapturingState capturingState;
    public static int centerDegree = 135;
    public static boolean reversed = false;


    @Override
    public void init() throws InterruptedException {
        hw = Robot.INSTANCE.getRobotData("HardwareMap", HardwareMap.class);
        capture = hw.get(DcMotorEx.class, "capture");
        revolver = hw.get(CRServo.class, "spin");
//        colorSensor = hw.get(RevColorSensorV3.class, "colorSensor");
//        webcam = hw.get(WebcamName.class, "Webcam");

        capture.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        capture.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        capture.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        if (reversed){
            capture.setDirection(DcMotor.Direction.REVERSE);
        } else {
            capture.setDirection(DcMotor.Direction.FORWARD);
        }

        revolver.setPower(PhantomMath.servoCRPowerToDegrees(centerDegree, 300));
        state = RevolverStates.CENTER;
        capture.setPower(0);
        capturingState = CapturingState.STOP;

        Robot.INSTANCE.addData("centerDegree", centerDegree);
        Robot.INSTANCE.addData("CapturingState", capturingState);
        Robot.INSTANCE.addData("RevolverState", state);
//        Robot.INSTANCE.addRobotDevice("colorSensor", colorSensor);
        Robot.INSTANCE.addRobotDevice("capture", capture);
        Robot.INSTANCE.addRobotDevice("rotator", revolver);
//        Robot.INSTANCE.addRobotDevice("webcam", webcam);


    }
}
