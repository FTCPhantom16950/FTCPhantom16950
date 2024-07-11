package org.firstinspires.ftc.teamcode.Utils;

import android.content.Context;
import android.widget.Toast;


import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.OpModes.TeleOP.PIDFmotorTester;

import java.util.List;

public class Robot {
    public Robot(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    LinearOpMode opMode;
    private final double RPC = 2000;
    private final double diameter = 48;
    WheelBase wheelBase = new WheelBase(opMode);
    PhantomIMU phantomIMU = new PhantomIMU();
    public List<LynxModule> allhubs;

    public void initLynx(HardwareMap hardwareMap){
        allhubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allhubs){
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
    }
    public void initAll(HardwareMap hardwareMap){
        wheelBase.initWheelBase(hardwareMap);
        phantomIMU.initIMU(hardwareMap);
    }


    public static class Position{
        public double[] coordinates = new double[]{0,0,0};
        public Position(double x, double y, double heading) {
            this.coordinates[0] = x;
            this.coordinates[1] = y;
            this.coordinates[2] = heading;
        }
        public double[] metersToRotations(){
            coordinates[1] = coordinates[1] * RPC / diameter;
            coordinates[2] = coordinates[2] * RPC / diameter;
            return coordinates;
        }
    }
}
