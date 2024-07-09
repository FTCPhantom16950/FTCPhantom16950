package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.lynx.commands.LynxMessage;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.TempUnit;

import java.util.List;

public class Poteshniy_autonom extends LinearOpMode {
    ElapsedTime timer = new ElapsedTime();
    double temperature;
    @Override
    public void runOpMode() throws InterruptedException {
        timer.startTime();
        List<LynxModule> allhubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allhubs){
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
            temperature = hub.getTemperature(TempUnit.CELSIUS);
        }
        timer.reset();
        waitForStart();
        while (opModeIsActive()){
            telemetry.addData("Температура", temperature);
        }
    }
}
