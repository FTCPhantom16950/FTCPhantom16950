package org.firstinspires.ftc.teamcode.own.mechanism;

import android.util.Size;

import com.acmerobotics.dashboard.FtcDashboard;
import com.bylazar.camerastream.PanelsCameraStream;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.own.camera.ColorDetectorProcessor;
import org.firstinspires.ftc.teamcode.own.utils.Mechanism;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.vision.VisionPortal;

public class CameraMechanism implements Mechanism {
    VisionPortal portal;
    ColorDetectorProcessor processor;
    WebcamName webcamName;
    HardwareMap hw;
    @Override
    public void init() throws InterruptedException {
        hw = Robot.INSTANCE.getRobotData("HardwareMap", HardwareMap.class);
        webcamName = hw.get(WebcamName.class, "Webcam 1");
        processor = new ColorDetectorProcessor.Builder().build();
        portal = new VisionPortal.Builder()
                .setCamera(webcamName)
                .addProcessor(processor)
                .enableLiveView(true)
                .setCameraResolution(new Size(320,176))
                .build();
        Robot.INSTANCE.addData("ColorDetectorProcessor", processor);
//        FtcDashboard.getInstance().startCameraStream(portal, 30);
//        PanelsCameraStream.INSTANCE.startStream(portal, 60);
        Robot.INSTANCE.addData("VisionPortal", portal);
    }
}
