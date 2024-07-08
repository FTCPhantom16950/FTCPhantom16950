package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Camera.PhantomCamera;

@Disabled
public class Telemetry extends OpMode {
    boolean lp, rp;
    PhantomMath phantomMath = new PhantomMath();
    PhantomIMU pmImu = new PhantomIMU();
    double x, y, heading, velocityX, velocityY;

    public void tIMU(HardwareMap hw){
        pmImu.valueGetter(hw);
        phantomMath.getCoordinatesByAccel(hw);
        x = phantomMath.x;
        y = phantomMath.y;
        velocityX = phantomMath.vCurrentX;
        velocityY = phantomMath.vCurrentY;
        heading = pmImu.heading;
        Thread IMUT = new Thread(() ->{
            telemetry.addData("Current x:", x);
            telemetry.addData("Current y:", y);
            telemetry.addData("Current Velocity X:", velocityX);
            telemetry.addData("Current Velocity Y:", velocityY);
            telemetry.addData("Heading ", heading);
            telemetry.update();
        });
        IMUT.start();
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
        telemetryCamera.start();
    }



    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
