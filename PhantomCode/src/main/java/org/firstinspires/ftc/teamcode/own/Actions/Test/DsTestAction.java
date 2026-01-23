package org.firstinspires.ftc.teamcode.Own.Actions.Test;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomMath;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.Own.Utils.Robot;

public class DsTestAction extends Action {
    @Override
    public void execute() throws InterruptedException {
        Rev2mDistanceSensor distanceSensor = Robot.get("ds1", Rev2mDistanceSensor.class);
//        ModernRoboticsI2cRangeSensor disSensor = Robot.get("ds", ModernRoboticsI2cRangeSensor.class);
        while (Robot.opMode.opModeIsActive()){
            PhantomOpMode.addData("ds rev", distanceSensor.getDistance(DistanceUnit.CM));
//            PhantomOpMode.addData("ds mr", disSensor.getDistance(DistanceUnit.CM));
//            PhantomOpMode.addData("ds mr opt", disSensor.cmOptical());
//            PhantomOpMode.addData("ds mr us", disSensor.cmUltrasonic());
        }
    }
}
