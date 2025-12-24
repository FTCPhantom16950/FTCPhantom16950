package org.firstinspires.ftc.teamcode.own.Mechanism;

import android.util.Log;

import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.psilynx.psikit.core.Logger;
import org.psilynx.psikit.core.rlog.RLOGServer;
import org.psilynx.psikit.core.rlog.RLOGWriter;

public class LoggerTestMechanism implements Mechanism {
    @Override
    public boolean init() throws InterruptedException {
        Logger.addDataReceiver(new RLOGServer());
        Logger.addDataReceiver(new RLOGWriter(Long.toString(System.currentTimeMillis())));
        Logger.start();
        Logger.periodicAfterUser(0, 0);
        return true;
    }
}
