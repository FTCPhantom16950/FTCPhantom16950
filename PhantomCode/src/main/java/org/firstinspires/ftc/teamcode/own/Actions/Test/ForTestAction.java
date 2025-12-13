package org.firstinspires.ftc.teamcode.own.Actions.Test;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.Utils.Regulators.PIDCofficients;
import org.firstinspires.ftc.teamcode.own.Utils.Regulators.PidController;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

@Config
@Configurable
public class ForTestAction extends Action {
    public static double kp = 0.00005, kd = 0.0008, kS = 0.04, target = 0, motorPower = 0, output = 0;
    PIDCofficients pidCofficients = new PIDCofficients(kp,kd);
    PidController pidController = new PidController(pidCofficients);
    @Override
    public void execute() {
        DcMotorEx dcMotorEx = Robot.get("test", DcMotorEx.class);
        pidController.setTarget(target);
        pidController.setDcMotorEx(dcMotorEx);
        pidController.setA(0.1);
        pidController.start();
        while (!opMode.isStopRequested()){
            pidController.setTarget(target);
            pidCofficients.setkP(kp);
            pidCofficients.setkD(kd);
            pidController.setkS(kS);
            pidController.setPidCofficients(pidCofficients);
            output = pidController.getOutput();
            PhantomOpMode.addData("motor Power", output);
            PhantomOpMode.addData("motor Power vel", dcMotorEx.getVelocity());
            PhantomOpMode.addData("error", pidController.getError());
            dcMotorEx.setPower(output);
        }
    }
}
