package org.firstinspires.ftc.teamcode.Utils;

import android.content.Context;
import android.widget.Toast;


import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Mechanism.WheelBase;

import java.util.List;

public class Robot {
    WheelBase wheelBase = new WheelBase();
    PhantomIMU phantomIMU = new PhantomIMU();
    Telemetry telemetry;
    Context context = new FtcRobotControllerActivity();
    double strafeSpeed, forwardSpeed, turnSpeed;
    public List<LynxModule> allhubs;

    public void initLynx(HardwareMap hardwareMap){
        allhubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allhubs){
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
    }
    public void initAll(HardwareMap hardwareMap){
        initLynx(hardwareMap);
        Thread thread = new Thread(() -> {

        });
        thread.start();
        wheelBase.initWheelBase(hardwareMap);
        phantomIMU.initIMU(hardwareMap);
    }



}
