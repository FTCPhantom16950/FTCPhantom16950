package org.firstinspires.ftc.teamcode.own.mechanism;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.states.StandingState;

@Config
@Configurable
public class StandingMechanism implements Mechanism {
    DcMotorEx rezna;
//    RevColorSensorV3 ground;
    StandingState standingState;
    HardwareMap hw;
    public static boolean reversed = false;

    @Override
    public void init() throws InterruptedException {
        hw = Robot.INSTANCE.getRobotData("HardwareMap" , HardwareMap.class);
        rezna = hw.get(DcMotorEx.class, "rezna");
//        ground = hw.get(RevColorSensorV3.class, "colorNiz");

        rezna.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rezna.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rezna.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        if (reversed){
            rezna.setDirection(DcMotor.Direction.REVERSE);
        } else {
            rezna.setDirection(DcMotor.Direction.FORWARD);
        }

        standingState = StandingState.DOWN;
        rezna.setPower(0);

        Robot.INSTANCE.addData("StandingState", standingState);

        Robot.INSTANCE.addRobotDevice("rezna", rezna);
//        Robot.INSTANCE.addRobotDevice("ground", ground);

    }
}
