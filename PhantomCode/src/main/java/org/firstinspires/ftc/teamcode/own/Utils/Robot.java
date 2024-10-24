package org.firstinspires.ftc.teamcode.own.Utils;


import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Mechanism.Odometry;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;

import java.util.List;

public class Robot {
    public Robot(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    LinearOpMode opMode;
    Odometry odometry = new Odometry(opMode);
   // WheelBase wheelBase = new WheelBase(opMode.gamepad1,opMode.gamepad2, opMode);
    PhantomIMU phantomIMU = new PhantomIMU();
    public List<LynxModule> allHubs;

    public void initLynx(HardwareMap hardwareMap){
        allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs){
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
    }
    public void initAll(HardwareMap hardwareMap){
        //wheelBase.initWheelBase(hardwareMap);
        phantomIMU.initIMU(hardwareMap);
        initLynx(hardwareMap);


    }



    public static class Position{
        public double[] coordinates = new double[]{0,0,0};
        public Position(double x, double y, double heading) {
            this.coordinates[0] = x;
            this.coordinates[1] = y;
            this.coordinates[2] = heading;
        }

    }
}
