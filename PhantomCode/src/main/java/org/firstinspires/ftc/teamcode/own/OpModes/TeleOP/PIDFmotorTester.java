package org.firstinspires.ftc.teamcode.own.OpModes.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;
@TeleOp
public class PIDFmotorTester extends LinearOpMode {
    WheelBase wheelBase = new WheelBase(new PIDFmotorTester());
    Robot robot = new Robot(new PIDFmotorTester());
    ElapsedTime time = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        wheelBase.initWheelBase(hardwareMap);
        robot.initAll(hardwareMap);
        waitForStart();
        time.reset();
        while (opModeIsActive()){
        }
    }
}
