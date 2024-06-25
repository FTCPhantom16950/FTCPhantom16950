package org.firstinspires.ftc.teamcode.OpModes.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Camera.EasyCam;
import org.firstinspires.ftc.teamcode.Camera.PhantomCamera;
import org.firstinspires.ftc.teamcode.Utils.PhantomIMU;
import org.firstinspires.ftc.teamcode.Utils.Robot;
import org.firstinspires.ftc.teamcode.Utils.Telemetry;

public class TeleOpTest  extends OpMode {
    Telemetry telemetryRobot = new Telemetry();
    PhantomCamera phantomCamera = new PhantomCamera(hardwareMap.get(WebcamName.class, "Camera"), true, true,true, true);
    @Override
    public void init() {
        phantomCamera.startCameraEasy(640, 480);
    }

    @Override
    public void init_loop() {
       telemetryRobot.tCamera(phantomCamera);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {
        super.stop();
    }
}
