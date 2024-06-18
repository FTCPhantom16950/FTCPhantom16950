package org.firstinspires.ftc.teamcode.OpModes.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Camera.EasyCam;
import org.firstinspires.ftc.teamcode.Utils.PhantomIMU;
import org.firstinspires.ftc.teamcode.Utils.Robot;

public class TeleOpTest  extends OpMode {
    Robot robot = new Robot();
    PhantomIMU phantomIMU = new PhantomIMU();
    EasyCam easyCam = new EasyCam(hardwareMap.get(WebcamName.class, "camera"), true,true,true,true);
    int valLeft, valRight;
    @Override
    public void init() {
        easyCam.startEasyCam();
        valLeft = easyCam.valLeft;
        valRight = easyCam.valRight;
        robot.initAll(hardwareMap);
    }

    @Override
    public void init_loop() {
        valLeft = easyCam.valLeft;
        valRight = easyCam.valRight;
        telemetry.addData("ValLeft", valLeft);
        telemetry.addData("ValRight", valRight);
        telemetry.update();
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
