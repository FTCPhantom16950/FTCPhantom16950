package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.TempUnit;
import org.firstinspires.ftc.teamcode.Camera.PhantomCamera;

import java.util.List;

@Disabled
public class Telemetry {
    org.firstinspires.ftc.robotcore.external.Telemetry telemetry;
    boolean lp, rp;
    Robot robot = new Robot();
    PhantomMath phantomMath = new PhantomMath();
    PhantomIMU pmImu = new PhantomIMU();
    double x, y, heading, velocityX, velocityY;
    List<LynxModule> allhubs;

    public void temperature(HardwareMap hw){
        Thread temperature = new Thread(() -> {
            robot.initLynx(hw);
            while (true){
                allhubs = robot.allhubs;
                for (LynxModule hub : allhubs){
                    String device = hub.getDeviceName();
                    double temp = hub.getTemperature(TempUnit.CELSIUS);
                    telemetry.addData("Device: ", device);
                    telemetry.addData("Temperature: ", temp);
                    telemetry.update();
                    try {
                        wait(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        temperature.start();
    }
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

}
