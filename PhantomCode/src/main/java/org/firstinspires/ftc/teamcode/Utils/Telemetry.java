package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Camera.PhantomCamera;

@Disabled
public class Telemetry extends OpMode {
    boolean lp, rp;
    public void tIMU(){

    }
    public void tCamera(PhantomCamera pc){
        lp = pc.lp;
        rp = pc.rp;
        Thread telemetryCamera = new Thread(() -> {
            while (true){
                telemetry.addData("ValLeft", lp);
                telemetry.addData("ValRight", rp);
                telemetry.update();
            }
        });
    }


    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
