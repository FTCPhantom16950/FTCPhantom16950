package org.firstinspires.ftc.teamcode.own.Utils;

import java.util.HashMap;
import java.util.Map;

public class Robot {
    private static PhantomOpMode opMode;

    public static PhantomOpMode getOpMode() {
        return opMode;
    }

    public static void setOpMode(PhantomOpMode opMode) {
        Robot.opMode = opMode;
    }

    private final static Map<String, Class> ownHardwareMap = new HashMap<String, Class>();
    protected static void initAll(){
        for (String name : ownHardwareMap.keySet()){
            opMode.hardwareMap.get(ownHardwareMap.get(name), name);
        }
    }
    public static void addMechanism(String name, Class mechanismClass){
        ownHardwareMap.put(name, mechanismClass);
    }
    public static void removeMechanism(String name, Class mechanismClass){
        ownHardwareMap.remove(name,mechanismClass);
    }
}
