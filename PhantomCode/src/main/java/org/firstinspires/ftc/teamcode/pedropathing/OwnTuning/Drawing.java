//package org.firstinspires.ftc.teamcode.pedropathing.OwnTuning;
//
//import static org.firstinspires.ftc.teamcode.own.Mechanism.FollowerMechanism.follower;
//
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
//import com.bylazar.field.Style;
//import com.pedropathing.paths.Path;
//import com.pedropathing.paths.PathChain;
//
////import org.firstinspires.ftc.teamcode.own.Utils.PhantomLogger;
//import org.opencv.core.Mat;
//import org.psilynx.psikit.core.Logger;
//import org.psilynx.psikit.core.wpi.Pose2d;
//import org.psilynx.psikit.core.wpi.Rotation2d;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class Drawing {
//    public static double width = 0, height = 0;
//
//    public static void drawRobotInLogger() {
//        PhantomLogger.addData("Robot position", new Pose2d(follower.getPose().getX(), follower.getPose().getY(), new Rotation2d(follower.getPose().getHeading())));
//    }
//
//    public static void drawRobotInDashboard() {
//        TelemetryPacket packet = new TelemetryPacket();
//        packet.fieldOverlay()
//                .setFill("blue")
//                .drawImage("res/raw/img.png", follower.getPose().getX() + Math.sqrt((Math.pow(width, 2) + Math.pow(height, 2))), follower.getPose().getY() + Math.sqrt((Math.pow(width, 2) + Math.pow(height, 2))), width, height, follower.getPose().getHeading(), 0, 0, false);
//        FtcDashboard dashboard = FtcDashboard.getInstance();
//        dashboard.sendTelemetryPacket(packet);
//    }
//    public static void drawNow(){
//        drawRobotInLogger();
//        drawRobotInDashboard();
//    }
//    public void drawTrajectoryInLogger(Path path) {
//
//        double[][] points = path.getPanelsDrawingPoints();
//
//        for (int i = 0; i < points[0].length; i++) {
//            for (int j = 0; j < points.length; j++) {
//                if (Double.isNaN(points[j][i])) {
//                    points[j][i] = 0;
//
//                }
//            }
//        }
//        PhantomLogger.addData("Trajectory", points);
//    }
//
//    public void drawTrajectoryInLogger(PathChain pathChain) {
//        for (int i = 0; i < pathChain.size(); i++) {
//            drawTrajectoryInLogger(pathChain.getPath(i));
//        }
//    }
//
//
//}
