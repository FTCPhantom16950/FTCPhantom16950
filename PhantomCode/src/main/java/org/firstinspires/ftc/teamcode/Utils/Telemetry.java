package org.firstinspires.ftc.teamcode.Utils;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.TempUnit;
import org.firstinspires.ftc.teamcode.Camera.PhantomCamera;

import java.util.List;

@Disabled
public class Telemetry {
    LinearOpMode opMode;
    Config config = new Config();
    public Telemetry(LinearOpMode opMode) {
        this.opMode = opMode;
    }
    double error, position, target;

    FTCcontroolers ftCcontroolers = new FTCcontroolers(opMode);
    PIDFController pidf = ftCcontroolers.pidf;
    org.firstinspires.ftc.robotcore.external.Telemetry telemetry = opMode.telemetry;
    boolean lp, rp;
    Robot robot = new Robot(opMode);
    PhantomMath phantomMath = new PhantomMath(opMode);
    PhantomIMU pmImu = new PhantomIMU();
    double x, y, heading, velocityX, velocityY;
    List<LynxModule> allhubs;

    public void temperature(HardwareMap hw){
        Thread temperature = new Thread(() -> {
            robot.initLynx(hw);
            while (true){
                allhubs = robot.allHubs;
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

    public void pidfTelemetry(){
        Thread tPIDF = new Thread(() -> {
            while(opMode.opModeIsActive()){
                error = pidf.getPositionError();
                position = ftCcontroolers.target - error;
                telemetry.addData("Error", error);
                telemetry.addData("Position", position);
                telemetry.addData("targert", pidf.getSetPoint());
            }
        }, "fun");
        tPIDF.start();
    }

}
