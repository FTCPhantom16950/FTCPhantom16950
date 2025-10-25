//package org.firstinspires.ftc.teamcode.own.Test;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
////
////import org.psilynx.psikit.core.Logger;
////import org.psilynx.psikit.core.rlog.RLOGServer;
////import org.psilynx.psikit.core.rlog.RLOGWriter;
////import org.psilynx.psikit.core.wpi.Pose2d;
////import org.psilynx.psikit.core.wpi.Rotation2d;
////import org.psilynx.psikit.core.wpi.Translation2d;
//
//@Autonomous
//public class TestOpmode extends OpMode {
//    double i = 0, x, y, rot;
//    Pose2d pose2d = new Pose2d(new Translation2d(x,y),new Rotation2d(rot));
//    @Override
//    public void init() {
//        Logger.addDataReceiver(new RLOGServer());
//        Logger.recordMetadata("sth", "sth2");
//        Logger.addDataReceiver(new RLOGWriter("storage/emulated/0/test", "logs.rlog"));
//        Logger.start();
//    }
//
//    @Override
//    public void loop() {
//        Logger.periodicBeforeUser();
//        Logger.recordOutput("i", i++);
//        x = x + 1 + Math.pow(x, (double) 1 /2);
//        y = y + 1 + Math.pow(y, (double) 1 /2);
//        rot = rot + 1 + Math.pow(rot, (double) 1 /2);
//        Logger.recordOutput("Pose", pose2d);
//        Logger.periodicAfterUser(0, 0);
//    }
//
//    @Override
//    public void stop() {
//        super.stop();
//
//    }
//}
//
