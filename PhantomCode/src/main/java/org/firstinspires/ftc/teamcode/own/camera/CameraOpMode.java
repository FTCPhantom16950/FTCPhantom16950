package org.firstinspires.ftc.teamcode.own.camera;
import io.github.ftcphantom16950.phantomlib.camera.ArtifactProcessor;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.opencv.core.Size;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous
public class CameraOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ArtifactProcessor artifactProcessor1 = ArtifactProcessor.newBuilder()
                .setCameraMatrix(3.58F, 2.02F)
                .setRazmer(49f)
                .setF(4f)
                .setBlurSize(new Size(3,3))
                .setPixelCameraHeight(960)
                .build();
        VisionPortal visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .addProcessors(artifactProcessor1)
                .build();
        FtcDashboard.getInstance().startCameraStream(visionPortal,0);
        waitForStart();
    }
}
