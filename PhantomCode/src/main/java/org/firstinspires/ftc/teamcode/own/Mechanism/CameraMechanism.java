package org.firstinspires.ftc.teamcode.own.Mechanism;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;
import org.firstinspires.ftc.teamcode.own.camera.ArtifactProcessor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class CameraMechanism implements Mechanism {
    private static CRServo verticalCameraServo, horizontalCameraServo;
    private static ArtifactProcessor artifactProcessor;
    private static AprilTagProcessor aprilTagProcessor;
    public static ArtifactProcessor getArtifactProcessor() {
        return artifactProcessor;
    }

    private VisionPortal visionPortal;

    @Override
    public boolean init() {
        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        verticalCameraServo = hw.get(CRServo.class, "vcs");
        horizontalCameraServo = hw.get(CRServo.class, "hcs");
        horizontalCameraServo.setPower(-0.3);
        verticalCameraServo.setPower(0);
        artifactProcessor = ArtifactProcessor.newBuilder().createWithDefaults();
        if (visionPortal == null) {
            visionPortal = new VisionPortal.Builder()
                    .setCamera(hw.get(WebcamName.class, "Webcam 1"))
                    .addProcessors(artifactProcessor, aprilTagProcessor)
                    .build();
        }else{
            visionPortal.resumeLiveView();
        }
        Robot.addOrUpdate("vcs",verticalCameraServo);
        Robot.addOrUpdate("hcs",horizontalCameraServo);
//        FtcDashboard.getInstance().startCameraStream(visionPortal, 0);
        return true;
    }
}
