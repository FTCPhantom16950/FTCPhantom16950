package org.firstinspires.ftc.teamcode.own.mechanism;

import com.acmerobotics.dashboard.config.Config;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.states.StandingState;

@Config
public class StandingMechanism implements Mechanism {
    DcMotorEx motor;

    HardwareMap hw;
    public static boolean reversed = false;

    @Override
    public void init() throws InterruptedException {
        hw = Robot.INSTANCE.getRobotData("HardwareMap", HardwareMap.class);
        motor = hw.get(DcMotorEx.class, "rezna");

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor.setPower(0);

        StandingState standingState = StandingState.STOP;
        Robot.INSTANCE.addData("StandingState", standingState);
        Robot.INSTANCE.addTelemetryData("stand", motor);
    }
}
