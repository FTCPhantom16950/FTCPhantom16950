package org.firstinspires.ftc.teamcode.own.Mechanism;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

public class LynxModule {
    public List<com.qualcomm.hardware.lynx.LynxModule> allHubs;
    public void init_Lynx(HardwareMap hardwareMap){
        allHubs = hardwareMap.getAll(com.qualcomm.hardware.lynx.LynxModule.class);
        for (com.qualcomm.hardware.lynx.LynxModule hub : allHubs){
            hub.setBulkCachingMode(com.qualcomm.hardware.lynx.LynxModule.BulkCachingMode.AUTO);
        }

    }
}
