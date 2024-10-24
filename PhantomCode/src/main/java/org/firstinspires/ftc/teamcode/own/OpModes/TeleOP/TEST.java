package org.firstinspires.ftc.teamcode.own.OpModes.TeleOP;



import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.Utils.Controllers.KalmanFilter;
import org.firstinspires.ftc.teamcode.own.Utils.Controllers.PidControl;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomIMU;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

@TeleOp
public class TEST extends LinearOpMode {
    ElapsedTime time = new ElapsedTime();
    PhantomIMU phantomIMU = new PhantomIMU();
    Robot robot = new Robot(this);
    WheelBase wheelBase = new WheelBase(this);
    @Override
    public void runOpMode() throws InterruptedException {
        robot.initLynx(hardwareMap);
        wheelBase.initWheelBase(hardwareMap);
        waitForStart();
        time.reset();
        while(opModeIsActive()){
            wheelBase.driveFieldCentric(gamepad1);
        }
    }
}
