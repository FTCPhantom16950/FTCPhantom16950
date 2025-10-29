package org.firstinspires.ftc.teamcode.own.Utils;



import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import org.psilynx.psikit.core.Logger;
import org.psilynx.psikit.core.rlog.RLOGServer;
import org.psilynx.psikit.core.rlog.RLOGWriter;


import java.util.HashMap;
import java.util.Map;


public class PhantomLogger extends Thread{
    private PhantomOpMode opMode;

    public PhantomLogger(PhantomOpMode opMode) {
        this.opMode = opMode;
    }
    private static final Map<String, Object> data = new HashMap<>();
    public static MultipleTelemetry multipleTelemetry;
    public static boolean clearDataOnStart = true;
    @Override
    public void run() {
        super.run();
        long time = System.currentTimeMillis();
        @SuppressLint("DefaultLocale") String name = String.format("Unilogs %d.rlog", time);
        RLOGServer rlogServer = new RLOGServer();
        RLOGWriter rlogWriter = new RLOGWriter("storage/emulated/0/test", name);
        if (opMode.opModeInInit()){
            multipleTelemetry = new MultipleTelemetry(opMode.telemetry, FtcDashboard.getInstance().getTelemetry());
            rlogServer.start();
            rlogWriter.start();
            org.psilynx.psikit.core.Logger.addDataReceiver(rlogServer);
            org.psilynx.psikit.core.Logger.recordMetadata("sth", "sth2");
            org.psilynx.psikit.core.Logger.addDataReceiver(rlogWriter);
            Logger.start();
            Logger.periodicBeforeUser();
            multipleTelemetry.addData("Inited", true);
            multipleTelemetry.update();
            Logger.recordOutput("Inited", true);
            Logger.periodicAfterUser(0, 0);
            opMode.waitForStart();
        }
        if (clearDataOnStart){
            data.clear();
        }
        while (opMode.opModeIsActive()){
            for (String nameData : data.keySet()){
                Logger.recordOutput(nameData, String.valueOf(data.get(nameData)));
                if (multipleTelemetry != null) {
                    multipleTelemetry.addData(nameData, data.get(nameData));
                    multipleTelemetry.update();
                }
            }
        }
        Logger.end();
        rlogServer.end();
        rlogWriter.end();
    }
    public static <R> void  addData(String nameData, R dataInfo){
        if (nameData == null){
            throw new IllegalArgumentException("Data name cant be null");
        }
        if (dataInfo == null){
            throw new IllegalArgumentException("Data information cant be null");
        }
        data.put(nameData, dataInfo);
    }
}
