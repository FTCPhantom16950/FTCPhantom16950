package org.firstinspires.ftc.teamcode.Own.Mechanism;

import android.util.Log;

import org.firstinspires.ftc.teamcode.Own.Utils.Mechanism;
import org.psilynx.psikit.core.Logger;
import org.psilynx.psikit.core.rlog.RLOGServer;
import org.psilynx.psikit.core.rlog.RLOGWriter;

public class LoggerTestMechanism implements Mechanism {
    @Override
    public boolean init() throws InterruptedException {
        var server = new RLOGServer();
        var writer = new RLOGWriter("log.rlog");

        Logger.addDataReceiver(server);
        Logger.addDataReceiver(writer);
        Logger.recordMetadata("some metadata", "string value");
        server.start();
        writer.start();
        Logger.start();
        Logger.periodicAfterUser(0, 0);

        return true;
    }
}
