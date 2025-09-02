package org.firstinspires.ftc.teamcode.own.Utils;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

public class UnitedTelemetry {
    private static long memo = 0;
    public static MultipleTelemetry multipleTelemetry;
    private static PhantomOpMode opMode;

    public PhantomOpMode getOpMode() {
        return opMode;
    }

    public static void setOpMode(PhantomOpMode opMode) {
        UnitedTelemetry.opMode = opMode;
    }

    public static boolean init() {
        multipleTelemetry = (FtcDashboard.getInstance() != null)
                ? new MultipleTelemetry(opMode.telemetry, FtcDashboard.getInstance().getTelemetry())
                : new MultipleTelemetry(opMode.telemetry);
        return true;
    }
    public static boolean execute(){
        memo = Runtime.getRuntime().totalMemory();
        if (multipleTelemetry != null){
           multipleTelemetry.addData("Ram Usage", memo);
           multipleTelemetry.update();
        }
        return true;
    }
}
