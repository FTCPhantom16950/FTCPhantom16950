package org.firstinspires.ftc.teamcode.own.Utils;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import org.firstinspires.ftc.teamcode.own.Actions.PidAction;
import org.firstinspires.ftc.teamcode.own.Mechanism.PidMororTest;

import java.util.logging.Logger;

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
        multipleTelemetry = new MultipleTelemetry(opMode.telemetry);
        return true;
    }

    public static boolean execute() {
        memo = Runtime.getRuntime().totalMemory();
        if (multipleTelemetry != null) {
            multipleTelemetry.addData("Ram Usage", memo);
            multipleTelemetry.addData("Motor Pos", PidMororTest.motorPid.getCurrentPosition());
            multipleTelemetry.addData("Measure", PidAction.Companion.getMeasured());
            multipleTelemetry.addData("Error", PidAction.Companion.getError());
            multipleTelemetry.addData("Out", PidAction.Companion.getOut());
            multipleTelemetry.update();
        }
        return true;
    }
}
